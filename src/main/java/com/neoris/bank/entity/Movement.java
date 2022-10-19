package com.neoris.bank.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Builder
public class Movement {

    private Long movementId;
    private Date date;
    private String type;
    private String description;
    private BigDecimal amount;
    private BigDecimal balance;
    private Long accountId;

    public Movement() {
    }

    public Movement(Long movementId, Date date, String type, String description, BigDecimal amount, BigDecimal balance, Long accountId) {
        this.movementId = movementId;
        this.date = date;
        this.type = type;
        this.description = description;
        this.amount = amount;
        this.balance = balance;
        this.accountId = accountId;
    }
}
