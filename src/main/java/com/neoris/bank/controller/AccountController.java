package com.neoris.bank.controller;

import com.neoris.bank.entity.Account;
import com.neoris.bank.exception.BankException;
import com.neoris.bank.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/cuentas")
public class AccountController {

    private AccountService accountService;

    @GetMapping
    public ResponseEntity<List<Account>> list() throws BankException {
        return new ResponseEntity<>(accountService.list(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Account> create(@Valid @RequestBody Account account) throws BankException {
        return new ResponseEntity<>(accountService.create(account), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Account> update(@PathVariable("id") Long id, @Valid @RequestBody Account account) throws BankException {
        return new ResponseEntity<>(accountService.update(id, account), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) throws BankException {
        accountService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
