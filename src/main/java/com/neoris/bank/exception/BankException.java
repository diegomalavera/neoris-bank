package com.neoris.bank.exception;

public class BankException extends Exception {

    private final String code;

    public BankException(String code) {
        super(code);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
