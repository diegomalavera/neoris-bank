package com.neoris.bank.entity;

import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;

public interface Report {

    @Value("#{target.name}")
    String getName();

    @Value("#{target.type}")
    String getType();

    @Value("#{target.balance}")
    BigDecimal getBalance();

    @Value("#{target.debits}")
    BigDecimal getDebits();

    @Value("#{target.credits}")
    BigDecimal getCredits();
}
