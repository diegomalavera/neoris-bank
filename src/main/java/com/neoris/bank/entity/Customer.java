package com.neoris.bank.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Customer {

    private Long customerId;
    private String name;
    private String gender;
    private Long age;
    private Long idNumber;
    private String address;
    private String phone;
    private String password;
    private Boolean status;

    public Customer() {
    }

    public Customer(Long customerId, String name, String gender, Long age, Long idNumber, String address, String phone, String password, Boolean status) {
        this.customerId = customerId;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.idNumber = idNumber;
        this.address = address;
        this.phone = phone;
        this.password = password;
        this.status = status;
    }
}
