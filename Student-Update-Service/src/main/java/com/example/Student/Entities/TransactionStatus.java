package com.example.Student.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties
public class TransactionStatus {

    private String mihpayid;
    private String request_id;
    private String bank_ref_num;
    private String amt;
    private String transaction_amount;
    private String txnid;
    public String additional_charges;
    public String productinfo;
    public String firstname;
    public String bankcode;
    public String udf1;
    public String udf3;
    public String udf4;
    public String udf5;
    public String field2;
    public String field9;
    public String error_code;
    public String addedon;
    public String payment_source;
    public Object card_type;
    public String error_Message;
    public int net_amount_debit;
    public String disc;
    public String mode;
    @JsonProperty("PG_TYPE")
    public String pG_TYPE;
    public String card_no;
    public String udf2;
    public String status;
    public String unmappedstatus;
    @JsonProperty("Merchant_UTR")
    public Object merchant_UTR;
    @JsonProperty("Settled_At")
    public String settled_At;
    @JsonProperty("App_Name")
    public String app_Name;

    public String getMihpayid() {
        return mihpayid;
    }

    public void setMihpayid(String mihpayid) {
        this.mihpayid = mihpayid;
    }

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    public String getBank_ref_num() {
        return bank_ref_num;
    }

    public void setBank_ref_num(String bank_ref_num) {
        this.bank_ref_num = bank_ref_num;
    }

    public String getAmt() {
        return amt;
    }

    public void setAmt(String amt) {
        this.amt = amt;
    }

    public String getTransaction_amount() {
        return transaction_amount;
    }

    public void setTransaction_amount(String transaction_amount) {
        this.transaction_amount = transaction_amount;
    }

    public String getTxnid() {
        return txnid;
    }

    public void setTxnid(String txnid) {
        this.txnid = txnid;
    }
}
