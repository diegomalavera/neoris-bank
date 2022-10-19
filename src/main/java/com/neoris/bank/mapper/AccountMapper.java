package com.neoris.bank.mapper;

import com.neoris.bank.entity.Account;
import com.neoris.bank.model.AccountEntity;
import com.neoris.bank.model.CustomerEntity;
import com.neoris.bank.repository.CustomerEntityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;

@Component
@AllArgsConstructor
public class AccountMapper {

    private CustomerEntityRepository customerRepository;

    public AccountEntity convert(Account source) throws EntityNotFoundException {
        CustomerEntity customerEntity = customerRepository.findById(source.getCustomerId())
                .orElseThrow(() -> new EntityNotFoundException("CUSTOMER_ERROR_NOT_FOUND"));
        return AccountEntity.builder()
                .number(source.getNumber())
                .type(source.getType())
                .balance(source.getBalance())
                .status(source.getStatus())
                .customer(customerEntity)
                .build();
    }

    public Account convert(AccountEntity source) {
        return Account.builder()
                .accountId(source.getAccountId())
                .number(source.getNumber())
                .type(source.getType())
                .balance(source.getBalance())
                .status(source.getStatus())
                .customerId(source.getCustomer().getPersonId())
                .build();
    }

    public void update(Account source, AccountEntity target) throws EntityNotFoundException {
        if (source.getNumber() != null) {
            target.setNumber(source.getNumber());
        }
        if (source.getType() != null) {
            target.setType(source.getType());
        }
        if (source.getBalance() != null) {
            target.setBalance(source.getBalance());
        }
        if (source.getStatus() != null) {
            target.setStatus(source.getStatus());
        }
        if (source.getCustomerId() != null) {
            CustomerEntity customerEntity = customerRepository.findById(source.getCustomerId())
                    .orElseThrow(EntityNotFoundException::new);
            target.setCustomer(customerEntity);
        }
    }
}
