package com.neoris.bank.repository;

import com.neoris.bank.entity.Report;
import com.neoris.bank.model.AccountEntity;
import com.neoris.bank.model.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Repository
public interface AccountEntityRepository extends JpaRepository<AccountEntity, Long> {

    public boolean existsByNumber(BigDecimal number);

    @Query("select " +
            "a.customer.name as name," +
            "a.type as type," +
            "a.balance as balance, " +
            "a.number as number, " +
            "(select sum(m.amount) from MovementEntity m where m.type = 'Retiro' and m.account.accountId = a.accountId and m.date BETWEEN :startDate AND :endDate) as debits, " +
            "(select sum(m.amount) from MovementEntity m where m.type = 'Deposito' and m.account.accountId = a.accountId and m.date BETWEEN :startDate AND :endDate) as credits " +
            "from AccountEntity a where a.customer = :customer")
    public List<Report> getReport(CustomerEntity customer, Date startDate, Date endDate);
}
