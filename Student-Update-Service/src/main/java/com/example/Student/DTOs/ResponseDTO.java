package com.example.Student.DTOs;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ResponseDTO {

    private String mihpayid;
    private String request_id;
    private String bank_ref_num;
    private String amt;
    private String transaction_amount;
    private String txnid;

    public ResponseDTO(String mihpayid, String request_id, String bank_ref_num, String amt, String transaction_amount, String txnid) {
        this.mihpayid = mihpayid;
        this.request_id = request_id;
        this.bank_ref_num = bank_ref_num;
        this.amt = amt;
        this.transaction_amount = transaction_amount;
        this.txnid = txnid;
    }
}
