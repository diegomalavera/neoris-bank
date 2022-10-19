package com.neoris.bank.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "movement", schema = "public")
public class MovementEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movementId", nullable = false)
    private Long movementId;
    private Date date;
    private String type;
    private String description;
    private BigDecimal amount;
    private BigDecimal balance;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "accountId", nullable = false)
    private AccountEntity account;

    public MovementEntity() {
    }
}
