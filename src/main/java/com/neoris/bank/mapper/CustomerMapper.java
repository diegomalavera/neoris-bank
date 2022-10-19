package com.neoris.bank.mapper;

import com.neoris.bank.entity.Customer;
import com.neoris.bank.model.CustomerEntity;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public CustomerEntity convert(Customer source) {
        return CustomerEntity.builder()
                .name(source.getName())
                .gender(source.getGender())
                .age(source.getAge())
                .idNumber(source.getIdNumber())
                .address(source.getAddress())
                .phone(source.getPhone())
                .password(source.getPassword())
                .status(source.getStatus())
                .build();
    }

    public Customer convert(CustomerEntity source) {
        return Customer.builder()
                .customerId(source.getPersonId())
                .name(source.getName())
                .gender(source.getGender())
                .age(source.getAge())
                .idNumber(source.getIdNumber())
                .address(source.getAddress())
                .phone(source.getPhone())
                .password(source.getPassword())
                .status(source.getStatus())
                .build();
    }

    public void update(Customer source, CustomerEntity target) {
        if (source.getName() != null) {
            target.setName(source.getName());
        }
        if (source.getGender() != null) {
            target.setGender(source.getGender());
        }
        if (source.getAge() != null) {
            target.setAge(source.getAge());
        }
        if (source.getIdNumber() != null) {
            target.setIdNumber(source.getIdNumber());
        }
        if (source.getAddress() != null) {
            target.setAddress(source.getAddress());
        }
        if (source.getPhone() != null) {
            target.setPhone(source.getPhone());
        }
        if (source.getPassword() != null) {
            target.setPassword(source.getPassword());
        }
        if (source.getStatus() != null) {
            target.setStatus(source.getStatus());
        }
    }
}
