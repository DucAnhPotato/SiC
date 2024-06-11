package vn.sic.payment.api.controller;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vn.sic.core.common.reponse.ApiResponse;
import vn.sic.core.common.reponse.SuccessResponse;
import vn.sic.log.infrastructure.annotation.LoggableRequest;
import vn.sic.payment.api.form.CreateTransactionRequest;
import vn.sic.payment.api.reponse.Translator;
import vn.sic.payment.application.service.TransactionService;
import vn.sic.payment.infrastructure.constant.MessageCode;

import java.util.List;

@RestController
@RequestMapping(value = "/transactions")
@LoggableRequest
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    /**
     * Create a transaction
     *
     * @param request Create transaction request
     * @return SuccessResponse
     */
    @PostMapping()
    public ApiResponse store(@Valid @RequestBody CreateTransactionRequest request) {
        return new SuccessResponse(Translator.toLocale(MessageCode.OK.getMessage()), transactionService.createTransaction(request));

    }

    /**
     * Transaction inquire
     *
     * @param invoiceNo invoice number
     * @return SuccessResponse
     */
    @GetMapping("/inquire")
    public ApiResponse inquire(@RequestParam String invoiceNo) {
        return new SuccessResponse(Translator.toLocale(MessageCode.OK.getMessage()), transactionService.transactionInquire(invoiceNo));
    }

    /**
     * Get transaction status
     *
     * @param status list of status
     * @return SuccessResponse
     */
    @GetMapping("/by-status")
    public ApiResponse getTransactionsByStatus(@RequestParam List<String> status) {
        return new SuccessResponse(Translator.toLocale(MessageCode.OK.getMessage()), transactionService.getTransactionsByStatus(status));
    }

    /**
     * Get transaction detail
     *
     * @param id id number
     * @return SuccessResponse
     */
    @GetMapping("/{id}")
    public ApiResponse show(@PathVariable("id") Long id) {
        return new SuccessResponse(Translator.toLocale(MessageCode.OK.getMessage()), transactionService.getTransactionById(id));
    }

    /**
     * Refund transaction inquire
     *
     * @param requestId requestId number
     * @return SuccessResponse
     */
    @GetMapping("/refunds/inquire")
    public ApiResponse inquireRefund(@RequestParam String requestId) {
        return new SuccessResponse(Translator.toLocale(MessageCode.OK.getMessage()), transactionService.refundTransactionInquire(requestId));
    }

}
