package com.example.Student.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties
public class Response {

    private int status;
    private String msg;
    private TransactionDetails transaction_details;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public TransactionDetails getTransaction_details() {
        return transaction_details;
    }

    public void setTransaction_details(TransactionDetails transaction_details) {
        this.transaction_details = transaction_details;
    }
}

