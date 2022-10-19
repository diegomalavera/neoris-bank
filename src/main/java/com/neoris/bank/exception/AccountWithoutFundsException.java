package com.neoris.bank.exception;

public class AccountWithoutFundsException extends BankException {

    public AccountWithoutFundsException(String code) {
        super(code);
    }
}
