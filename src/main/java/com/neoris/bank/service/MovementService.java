package com.neoris.bank.service;

import com.neoris.bank.entity.Movement;
import com.neoris.bank.exception.AccountWithoutFundsException;
import com.neoris.bank.exception.BankException;
import com.neoris.bank.mapper.MovementMapper;
import com.neoris.bank.model.AccountEntity;
import com.neoris.bank.model.MovementEntity;
import com.neoris.bank.repository.AccountEntityRepository;
import com.neoris.bank.repository.MovementEntityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class MovementService {

    private AccountEntityRepository accountRepository;
    private MovementEntityRepository movementRepository;
    private MovementMapper movementMapper;

    public List<Movement> list() throws BankException {
        try {
            return movementRepository.findAll()
                    .stream()
                    .map(movementMapper::convert)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new BankException("MOVEMENT_ERROR_LIST");
        }
    }

    public Movement create(Movement movement) throws BankException {
        try {
            AccountEntity accountEntity = accountRepository.findById(movement.getAccountId())
                    .orElseThrow(() -> new EntityNotFoundException("ACCOUNT_ERROR_NOT_FOUND"));
            BigDecimal balance = accountEntity.getBalance();
            BigDecimal amount = movement.getAmount();
            movement.setBalance(accountEntity.getBalance());
            if (amount.compareTo(BigDecimal.ZERO) < 0) {
                if (amount.abs().compareTo(balance) > 0) {
                    throw new AccountWithoutFundsException("ACCOUNT_ERROR_WITHOUT_FUNDS");
                }
                movement.setType("Retiro");
                movement.setDescription("Retiro de " + amount.abs().toString());
            } else {
                movement.setType("Deposito");
                movement.setDescription("Deposito de " + amount.abs().toString());
            }
            movement.setDate(new Date());
            balance = balance.add(amount);
            accountEntity.setBalance(balance);
            accountRepository.save(accountEntity);
            MovementEntity movementEntity = movementMapper.convert(movement);
            movementRepository.save(movementEntity);
            return movement;
        } catch (EntityNotFoundException e) {
            throw new BankException(e.getMessage());
        } catch (AccountWithoutFundsException e) {
            throw new BankException(e.getCode());
        } catch (Exception e) {
            e.printStackTrace();
            throw new BankException("MOVEMENT_ERROR_CREATE");
        }
    }

    public Movement update(Long id, Movement movement) throws BankException {
        try {
            MovementEntity movementEntity = movementRepository.findById(id)
                    .orElseThrow(EntityNotFoundException::new);
            movementMapper.update(movement, movementEntity);
            movementRepository.save(movementEntity);
            return movementMapper.convert(movementEntity);
        } catch (EntityNotFoundException e) {
            throw new BankException("MOVEMENT_ERROR_NOT_FOUND");
        } catch (Exception e) {
            throw new BankException("MOVEMENT_ERROR_UPDATE");
        }
    }

    public void delete(Long id) throws BankException {
        try {
            MovementEntity movementEntity = movementRepository.findById(id)
                    .orElseThrow(EntityNotFoundException::new);
            movementRepository.delete(movementEntity);
        } catch (EntityNotFoundException e) {
            throw new BankException("MOVEMENT_ERROR_NOT_FOUND");
        } catch (Exception e) {
            throw new BankException("MOVEMENT_ERROR_DELETE");
        }
    }
}
