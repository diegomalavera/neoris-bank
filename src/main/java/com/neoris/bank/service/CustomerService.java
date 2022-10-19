package com.neoris.bank.service;

import com.neoris.bank.entity.Customer;
import com.neoris.bank.exception.BankException;
import com.neoris.bank.mapper.CustomerMapper;
import com.neoris.bank.model.CustomerEntity;
import com.neoris.bank.repository.CustomerEntityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class CustomerService {

    private CustomerEntityRepository customerRepository;
    private CustomerMapper customerMapper;

    public List<Customer> list() throws BankException {
        try {
            return customerRepository.findAll()
                    .stream()
                    .map(customerMapper::convert)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new BankException("CUSTOMER_ERROR_LIST");
        }
    }

    public Customer create(Customer customer) throws BankException {
        try {
            if (customerRepository.existsByIdNumber(customer.getIdNumber())) {
                throw new EntityExistsException();
            }
            return customerMapper.convert(customerRepository.save(customerMapper.convert(customer)));
        } catch (EntityExistsException e) {
            throw new BankException("CUSTOMER_ERROR_EXIST");
        } catch (Exception e) {
            throw new BankException("CUSTOMER_ERROR_CREATE");
        }
    }

    public Customer update(Long id, Customer customer) throws BankException {
        try {
            CustomerEntity customerEntity = customerRepository.findById(id)
                    .orElseThrow(EntityNotFoundException::new);
            customerMapper.update(customer, customerEntity);
            customerRepository.save(customerEntity);
            return customerMapper.convert(customerEntity);
        } catch (EntityNotFoundException e) {
            throw new BankException("CUSTOMER_ERROR_NOT_FOUND");
        } catch (Exception e) {
            throw new BankException("CUSTOMER_ERROR_UPDATE");
        }
    }

    public void delete(Long id) throws BankException {
        try {
            CustomerEntity customerEntity = customerRepository.findById(id)
                    .orElseThrow(EntityNotFoundException::new);
            customerRepository.delete(customerEntity);
        } catch (EntityNotFoundException e) {
            throw new BankException("CUSTOMER_ERROR_NOT_FOUND");
        } catch (Exception e) {
            throw new BankException("CUSTOMER_ERROR_DELETE");
        }
    }
}
