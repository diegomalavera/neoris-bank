package com.neoris.bank.repository;

import com.neoris.bank.model.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerEntityRepository extends JpaRepository<CustomerEntity, Long> {

    public boolean existsByIdNumber(Long idNumber);
}
