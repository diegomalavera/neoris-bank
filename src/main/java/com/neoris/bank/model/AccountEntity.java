package com.neoris.bank.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "account", schema = "public")
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "accountId", nullable = false)
    private Long accountId;
    private BigDecimal number;
    private String type;
    private BigDecimal balance;
    private Boolean status;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "customerId", nullable = false)
    private CustomerEntity customer;

    public AccountEntity() {
    }
}
