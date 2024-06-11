package vn.sic.payment.application.service.impl;

import io.micrometer.common.util.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.sic.core.common.exception.ServiceException;
import vn.sic.core.common.util.JsonUtils;
import vn.sic.core.common.util.UrlUtils;
import vn.sic.payment.api.form.CreateTransactionRequest;
import vn.sic.payment.api.reponse.Translator;
import vn.sic.payment.application.service.CommonService;
import vn.sic.payment.application.service.TransactionService;
import vn.sic.payment.domain.dto.*;
import vn.sic.payment.domain.entity.Transaction;
import vn.sic.payment.domain.repository.TransactionRepository;
import vn.sic.payment.infrastructure.constant.MerchantConfigProperty;
import vn.sic.payment.infrastructure.constant.MessageCode;
import vn.sic.payment.infrastructure.constant.TransactionStatus9PayEnum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.Normalizer;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.Random;
@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private CommonService commonService;
    @Autowired
    private MerchantConfigProperty merchantConfigProperty;
    @Autowired
    private TransactionRepository transactionRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public TransactionInquireDTO transactionInquire(String invoiceNo) {
        String time = String.valueOf(System.currentTimeMillis() / 1000);
        String endpoint = "/v2/payments/" + invoiceNo + "/inquire";
        String signature = commonService.signature(null, "GET", endpoint, time);

        HttpURLConnection http = null;
        StringBuilder responseBuilder;
        BufferedReader readHttp = null;

        try {
            URL url = new URL(merchantConfigProperty.getEndPoint() + endpoint);
            http = (HttpURLConnection) url.openConnection();
            http.setRequestProperty("Date", time);
            http.setRequestProperty("Authorization",
                    "Signature Algorithm=HS256,Credential=" + merchantConfigProperty.getMerchantKey() + ",SignedHeaders=,Signature=" + signature);

            readHttp = new BufferedReader(new InputStreamReader(http.getInputStream()));
            responseBuilder = new StringBuilder();
            String line;

            while ((line = readHttp.readLine()) != null) {
                responseBuilder.append(line);
            }

            String status = responseBuilder.substring(responseBuilder.indexOf("\"status\":") + 9, responseBuilder.indexOf(",\"failure_reason\":"));
            responseBuilder.replace(responseBuilder.indexOf("\"status\":") + 9,
                    responseBuilder.indexOf(",\"failure_reason\":"),
                    "\"" + TransactionStatus9PayEnum.getDescriptionByStatus(Integer.parseInt(status)) + "\"");

        } catch (IOException e) {
            Transaction transaction = transactionRepository.findByRefTraceOrInvoiceNo(null, invoiceNo);
            if (transaction != null) {
                TempTransactionInquireDTO tempTransactionInquire = transaction.toTempTransactionInquireDTO();
                responseBuilder = new StringBuilder(JsonUtils.serialize(tempTransactionInquire));
            } else {
                throw new ServiceException(MessageCode.NOT_FOUND.getCode(), Translator.toLocale(MessageCode.NOT_FOUND.getMessage()));
            }

        } finally {
            try {
                if (readHttp != null) {
                    readHttp.close();
                }
                if (http != null) {
                    http.disconnect();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return JsonUtils.deserialize(responseBuilder.toString(), TransactionInquireDTO.class);
    }

    /**
     * {@inheritDoc}
     */
    public String createTransaction(CreateTransactionRequest r) {
        try {
            if (r.getDescription() != null) {
                if (!Normalizer.isNormalized(r.getDescription(), Normalizer.Form.NFD))
                    return null;
            }
            if (transactionRepository.findByRefTrace(r.getRefTrace()) != null) {
                return null;
            }
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMddHHmmssSSS");

            Map<String, String> map = new TreeMap<>();

            LocalDateTime localDateTime = LocalDateTime.now();
            ZonedDateTime zdt = ZonedDateTime.of(localDateTime, ZoneId.systemDefault());
            long currentMillis = zdt.toInstant().toEpochMilli();
            String time = String.valueOf(currentMillis / 1000);

            Transaction transaction = new Transaction(r);

            int leftLimit = 65; // letter 'A'
            int rightLimit = 90; // letter 'Z'
            int targetStringLength = 3;
            Random random = new Random();
            String generatedString = random.ints(leftLimit, rightLimit + 1)
                    .limit(3)
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();
            // invoiceNo have the format sic_{merchantid}_{timestamp}_{3 random char}
            String invoiceNo = "SIC_" + transaction.getMerchantId() + "_" + localDateTime.format(formatter) + "_" + generatedString;
            transaction.setInvoiceNo(invoiceNo);

            map.put("merchantKey", merchantConfigProperty.getMerchantKey());
            map.put("time", time);
            map.put("invoice_no", transaction.getInvoiceNo());
            map.put("amount", transaction.getAmount().toString());
            map.put("description", transaction.getDescription());
            map.put("return_url", transaction.getCallbackURL());
            map.put("save_token", r.getSaveToken().toString());
            if (!StringUtils.isEmpty(r.getCardToken()))
                map.put("card_token", r.getCardToken());
            String method = transaction.getPaymentMethod();
            if (!method.equals("DEFAULT"))
                map.put("method", method);

            String signature = commonService.signature(map, "POST", "/payments/create", time);
            String baseEncode = Base64.getEncoder().encodeToString(Objects.requireNonNull(JsonUtils.serialize(map)).getBytes());

            Map<String, String> queryBuild = new HashMap<>();
            queryBuild.put("baseEncode", baseEncode);
            queryBuild.put("signature", signature);

            String redirectUrl = merchantConfigProperty.getEndPoint() + "/portal?" + UrlUtils.urlEncode(queryBuild);

            transactionRepository.save(transaction);
            return redirectUrl;
        } catch(Exception e){
            throw new ServiceException(MessageCode.INTERNAL_SERVER_ERROR.getCode(), Translator.toLocale(MessageCode.INTERNAL_SERVER_ERROR.getMessage()));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<StatusTransactionDTO> getTransactionsByStatus(List<String> status) {
        return transactionRepository
                .getTransactionsByTransactionStatusIsIn(status)
                .stream()
                .map(Transaction::toPendingDTO)
                .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DetailTransactionDTO getTransactionById(Long id) {
        Optional<Transaction> optionalTransaction = transactionRepository.findById(id);
        if (optionalTransaction.isPresent()) {
            return optionalTransaction.get().toDetailTransactionDTO();
        } else {
            throw new ServiceException(MessageCode.NOT_FOUND.getCode(), Translator.toLocale(MessageCode.NOT_FOUND.getMessage()));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RefundTransactionInquireDTO refundTransactionInquire(String requestId) {
        String time = String.valueOf(System.currentTimeMillis() / 1000);
        String endPoint = "/v2/refunds/" + requestId + "/inquire";
        String signature = commonService.signature(null, "GET", endPoint, time);

        HttpURLConnection http = null;
        StringBuilder responseBuilder;
        BufferedReader readHttp = null;

        try {
            URL url = new URL(merchantConfigProperty.getEndPoint() + endPoint);
            http = (HttpURLConnection) url.openConnection();
            http.setRequestProperty("Date", time);
            http.setRequestProperty("Authorization",
                    "Signature Algorithm=HS256,Credential=" + merchantConfigProperty.getMerchantKey() + ",SignedHeaders=,Signature=" + signature);

            readHttp = new BufferedReader(new InputStreamReader(http.getInputStream()));
            responseBuilder = new StringBuilder();
            String line;

            while ((line = readHttp.readLine()) != null) {
                responseBuilder.append(line);
            }

        } catch (IOException e) {

            throw new ServiceException(MessageCode.INTERNAL_SERVER_ERROR.getCode(), Translator.toLocale(MessageCode.INTERNAL_SERVER_ERROR.getMessage()));

        } finally {
            try {
                if (readHttp != null) {
                    readHttp.close();
                }
                if (http != null) {
                    http.disconnect();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return JsonUtils.deserialize(responseBuilder.toString(), RefundTransactionInquireDTO.class);
    }
}
