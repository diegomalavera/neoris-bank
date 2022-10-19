package com.neoris.bank.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class Account {

    private Long accountId;
    private BigDecimal number;
    private String type;
    private BigDecimal balance;
    private Boolean status;
    private Long customerId;

    public Account() {
    }

    public Account(Long accountId, BigDecimal number, String type, BigDecimal balance, Boolean status, Long customerId) {
        this.accountId = accountId;
        this.number = number;
        this.type = type;
        this.balance = balance;
        this.status = status;
        this.customerId = customerId;
    }
}
