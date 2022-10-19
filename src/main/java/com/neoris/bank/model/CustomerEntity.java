package com.neoris.bank.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "customer", schema = "public")
@PrimaryKeyJoinColumn(name = "customerId")
public class CustomerEntity extends PersonEntity {

    private String password;
    private Boolean status;

    public CustomerEntity() {
    }

    @Builder
    public CustomerEntity(Long customerId, String name, String gender, Long age, Long idNumber, String address, String phone, String password, Boolean status) {
        super(customerId, name, gender, age, idNumber, address, phone);
        this.password = password;
        this.status = status;
    }
}
