package com.neoris.bank.mapper;

import com.neoris.bank.entity.Movement;
import com.neoris.bank.model.AccountEntity;
import com.neoris.bank.model.MovementEntity;
import com.neoris.bank.repository.AccountEntityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;

@AllArgsConstructor
@Component
public class MovementMapper {

    private AccountEntityRepository accountEntityRepository;

    public MovementEntity convert(Movement source) throws EntityNotFoundException {
        AccountEntity accountEntity = accountEntityRepository.findById(source.getAccountId())
                .orElseThrow(EntityNotFoundException::new);
        return MovementEntity.builder()
                .date(source.getDate())
                .type(source.getType())
                .description(source.getDescription())
                .amount(source.getAmount())
                .balance(source.getBalance())
                .account(accountEntity)
                .build();
    }

    public Movement convert(MovementEntity source) {
        return Movement.builder()
                .movementId(source.getMovementId())
                .date(source.getDate())
                .type(source.getType())
                .description(source.getDescription())
                .amount(source.getAmount())
                .balance(source.getBalance())
                .build();
    }

    public void update(Movement source, MovementEntity target) throws EntityNotFoundException {
        if (source.getDate() != null) {
            target.setDate(source.getDate());
        }
        if (source.getType() != null) {
            target.setType(source.getType());
        }
        if (source.getDescription() != null) {
            target.setDescription(source.getDescription());
        }
        if (source.getAmount() != null) {
            target.setAmount(source.getAmount());
        }
        if (source.getBalance() != null) {
            target.setBalance(source.getBalance());
        }
        if (source.getAccountId() != null) {
            AccountEntity accountEntity = accountEntityRepository.findById(source.getAccountId())
                    .orElseThrow(EntityNotFoundException::new);
            target.setAccount(accountEntity);
        }
    }
}
