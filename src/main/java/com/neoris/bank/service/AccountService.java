package com.neoris.bank.service;

import com.neoris.bank.entity.Account;
import com.neoris.bank.exception.BankException;
import com.neoris.bank.mapper.AccountMapper;
import com.neoris.bank.model.AccountEntity;
import com.neoris.bank.repository.AccountEntityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class AccountService {

    private AccountEntityRepository accountRepository;
    private AccountMapper accountMapper;

    public List<Account> list() throws BankException {
        try {
            return accountRepository.findAll()
                    .stream()
                    .map(accountMapper::convert)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new BankException("ACCOUNT_ERROR_LIST");
        }
    }

    public Account create(Account account) throws BankException {
        try {
            if (accountRepository.existsByNumber(account.getNumber())) {
                throw new EntityExistsException("ACCOUNT_ERROR_EXIST");
            }
            AccountEntity accountEntity = accountMapper.convert(account);
            accountRepository.save(accountEntity);
            return accountMapper.convert(accountEntity);
        } catch (EntityNotFoundException e) {
            throw new BankException(e.getMessage());
        } catch (EntityExistsException e) {
            throw new BankException(e.getMessage());
        } catch (Exception e) {
            throw new BankException("ACCOUNT_ERROR_CREATE");
        }
    }

    public Account update(Long id, Account account) throws BankException {
        try {
            AccountEntity accountEntity = accountRepository.findById(id)
                    .orElseThrow(EntityNotFoundException::new);
            accountMapper.update(account, accountEntity);
            accountRepository.save(accountEntity);
            return accountMapper.convert(accountEntity);
        } catch (EntityNotFoundException e) {
            throw new BankException("ACCOUNT_ERROR_NOT_FOUND");
        } catch (Exception e) {
            throw new BankException("ACCOUNT_ERROR_UPDATE");
        }
    }

    public void delete(Long id) throws BankException {
        try {
            AccountEntity accountEntity = accountRepository.findById(id)
                    .orElseThrow(EntityNotFoundException::new);
            accountRepository.delete(accountEntity);
        } catch (EntityNotFoundException e) {
            throw new BankException("ACCOUNT_ERROR_NOT_FOUND");
        } catch (Exception e) {
            throw new BankException("ACCOUNT_ERROR_DELETE");
        }
    }
}
