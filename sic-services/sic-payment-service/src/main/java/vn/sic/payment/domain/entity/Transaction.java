package vn.sic.payment.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import vn.sic.payment.api.form.CreateTransactionRequest;
import vn.sic.payment.domain.dto.DetailTransactionDTO;
import vn.sic.payment.domain.dto.TempTransactionInquireDTO;
import vn.sic.payment.domain.dto.StatusTransactionDTO;
import vn.sic.payment.domain.dto.TransactionDTO;
import vn.sic.payment.infrastructure.constant.TransactionStatusCodeEnum;
import vn.sic.payment.infrastructure.constant.TransactionType;
import vn.sic.payment.infrastructure.constant.UpdateType;
import vn.sic.payment.infrastructure.constant.Updater;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Data
@Table(name = "tbl_transaction")
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "merchant_id", nullable = false)
    private String merchantId;

    @Column(name = "customer_id", nullable = false)
    private String customerId;

    @Column(name = "ref_trace", nullable = false)
    private String refTrace;

    @Column(name = "invoice_no", nullable = false)
    private String invoiceNo;

    @Column(name = "order_no", nullable = false)
    private String orderNo;

    @Column(name = "debit_amount", nullable = false)
    private Long debitAmount;

    @Column(name = "amount", nullable = false)
    private Long amount;

    @Column(name = "fee", nullable = false)
    private Long fee;

    @Column(name = "currency", nullable = false)
    private String currency;

    @Column(name = "hash_key", nullable = false)
    private String hashKey;

    @Column(name = "ref_timestamp", nullable = false)
    private Timestamp refTimestamp;

    @Column(name = "callback_url", nullable = false)
    private String callbackURL;

    @Column(name = "description")
    private String description;

    @Column(name = "req_domain", nullable = false)
    private String reqDomain;

    @Column(name = "payment_method")
    private String paymentMethod;

    @Column(name = "created_date", nullable = false)
    @CreationTimestamp
    private Date createdDate;

    @Column(name = "updated_date")
    @UpdateTimestamp
    private Date updatedDate;

    @Column(name = "transaction_type", nullable = false)
    private String transactionType;

    @Column(name = "update_type", nullable = false)
    private String updateType;

    @Column(name = "note")
    private String note;

    @Column(name = "updater", nullable = false)
    private String updater;

    @Column(name = "transaction_status", nullable = false)
    private String transactionStatus;

    public Transaction(CreateTransactionRequest r) {
        this(null, r.getMerchantId(), r.getCustomerId(), r.getRefTrace(),null, r.getOrderNo(),
                r.getDebitAmount(), r.getAmount(), r.getFee(), r.getCurrency(), r.getHashKey(),
                null, r.getCallbackURL(), r.getDescription(), r.getReqDomain(),
                r.getPaymentMethod(), null, null, null, null,
                null, null, null);
        if (paymentMethod == null || paymentMethod.isEmpty())
            paymentMethod = "DEFAULT";
        if (description == null || description.isEmpty())
            description = "DEPOSIT ACCOUNT";
        
        // format refTimestamp with timestamp in request
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        refTimestamp = Timestamp.valueOf(LocalDateTime.from(formatter.parse(r.getTimestamp())));
        transactionStatus = TransactionStatusCodeEnum.INIT.name();
        transactionType = TransactionType.DEFAULT.name();
        updateType = UpdateType.DEFAULT.name();
        updater = Updater.MERCHANT.name();
    }

    public TransactionDTO toDTO() {
        return new TransactionDTO(
                this.merchantId, this.customerId,
                this.refTrace, this.invoiceNo,this.orderNo,
                this.debitAmount, this.amount,
                this.fee, this.currency, this.hashKey,
                this.createdDate.toString(), this.callbackURL, this.description,
                this.reqDomain, this.paymentMethod
        );
    }

    public StatusTransactionDTO toPendingDTO() {
        return new StatusTransactionDTO(
                this.merchantId, this.customerId,
                this.refTrace, this.invoiceNo,
                this.debitAmount, this.amount,
                this.fee, this.currency, this.hashKey,
                this.createdDate.toString(), this.callbackURL, this.description,
                this.reqDomain, this.paymentMethod, this.transactionStatus
        );
    }

    public DetailTransactionDTO toDetailTransactionDTO() {
        return new DetailTransactionDTO(
                this.merchantId, this.customerId, this.refTrace,
                this.invoiceNo, this.debitAmount, this.amount,
                this.fee, this.currency, this.hashKey,
                this.refTimestamp.toString(), this.callbackURL, this.description,
                this.reqDomain, this.paymentMethod, this.createdDate.toString(),
                this.updatedDate.toString(), this.transactionType, this.updateType,
                this.note, this.updater, this.transactionStatus
        );
    }

    public TempTransactionInquireDTO toTempTransactionInquireDTO() {
        return new TempTransactionInquireDTO(
                null, this.invoiceNo, this.currency,
                this.amount.intValue(), this.description,
                this.paymentMethod, null, this.transactionStatus,
                null, this.refTimestamp.toString()
        );
    }
}
