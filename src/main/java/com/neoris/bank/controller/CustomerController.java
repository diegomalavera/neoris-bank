package com.neoris.bank.controller;

import com.neoris.bank.entity.Customer;
import com.neoris.bank.exception.BankException;
import com.neoris.bank.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/clientes")
public class CustomerController {

    private CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<Customer>> list() throws BankException {
        return new ResponseEntity<>(customerService.list(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Customer> create(@Valid @RequestBody Customer customer) throws BankException {
        return new ResponseEntity<>(customerService.create(customer), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> update(@PathVariable("id") Long id, @Valid @RequestBody Customer customer) throws BankException {
        return new ResponseEntity<>(customerService.update(id, customer), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) throws BankException {
        customerService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
