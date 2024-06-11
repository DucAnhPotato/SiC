CREATE TABLE TBL_TRANSACTION (
    id BIGSERIAL not null PRIMARY KEY,
	merchant_id VARCHAR(60) not null,
	customer_id VARCHAR(60) not null,
	ref_trace VARCHAR(60) not null,
	invoice_no VARCHAR(60) not null,
	debit_amount DECIMAL(18, 2) not null,
	amount DECIMAL(18,2) not null,
	fee DECIMAL(18,2) not null,
	currency VARCHAR(60) not null,
	hash_key VARCHAR(60) not null,
	time_call_api timestamp without time zone not null,
	callback_url VARCHAR(500) not null,
	description VARCHAR(100),
	req_domain VARCHAR(60) not null,
	payment_method VARCHAR(60),
	created_date timestamp without time zone not null default current_timestamp,
	updated_date timestamp without time zone,
	transaction_type VARCHAR(60) not null,
	update_type VARCHAR(60) not null,
	note VARCHAR(100),
	updater VARCHAR(60) not null,
	transaction_status VARCHAR(60) not null
);

COMMENT ON COLUMN TBL_TRANSACTION.id IS 'Table ID, primary key, auto increment';
COMMENT ON COLUMN TBL_TRANSACTION.merchant_id IS 'Merchant Id, issued by SiCPay';
COMMENT ON COLUMN TBL_TRANSACTION.customer_id IS 'Customer Id in merchant system';
COMMENT ON COLUMN TBL_TRANSACTION.ref_trace IS 'Transaction Id (Unique) in the Merchant’s system';
COMMENT ON COLUMN TBL_TRANSACTION.invoice_no IS 'Order code';
COMMENT ON COLUMN TBL_TRANSACTION.debit_amount IS 'Total amount including fee';
COMMENT ON COLUMN TBL_TRANSACTION.amount IS 'Deposit amount';
COMMENT ON COLUMN TBL_TRANSACTION.fee IS 'Transaction fee';
COMMENT ON COLUMN TBL_TRANSACTION.currency IS '	Currency of each country Only allow VND';
COMMENT ON COLUMN TBL_TRANSACTION.hash_key IS 'HashKey = SHA256(MerchantId + debitAmount + invoiceNo + refTrace + secretKey)SecretKey issued by SiCPay';
COMMENT ON COLUMN TBL_TRANSACTION.time_call_api IS 'Timestamp when calling API Format yyyyMMddHHmmss';
COMMENT ON COLUMN TBL_TRANSACTION.callback_url IS 'This URL will receive the payment result from SiCPay';
COMMENT ON COLUMN TBL_TRANSACTION.description IS 'Note information';
COMMENT ON COLUMN TBL_TRANSACTION.req_domain IS 'Request domain';
COMMENT ON COLUMN TBL_TRANSACTION.payment_method IS '‘’ or null: Customer will select payment method on SiCPay Portal,‘QR_PAY’: ATM card, ‘ATM_CARD’: ATM card, ‘DEPOSIT_TRANSFER_IB’: Bank transfer';
COMMENT ON COLUMN TBL_TRANSACTION.created_date IS 'transaction initiation date';
COMMENT ON COLUMN TBL_TRANSACTION.updated_date IS 'transaction update date';
COMMENT ON COLUMN TBL_TRANSACTION.transaction_type IS 'type of transaction';
COMMENT ON COLUMN TBL_TRANSACTION.update_type IS 'type of update';
COMMENT ON COLUMN TBL_TRANSACTION.note IS 'note for transaction update case';
COMMENT ON COLUMN TBL_TRANSACTION.updater IS 'updater';
COMMENT ON COLUMN TBL_TRANSACTION.transaction_status IS 'status of transaction';
