package com.neoris.bank.service;

import com.neoris.bank.entity.Report;
import com.neoris.bank.exception.BankException;
import com.neoris.bank.model.CustomerEntity;
import com.neoris.bank.repository.AccountEntityRepository;
import com.neoris.bank.repository.CustomerEntityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Service
public class ReportService {

    private CustomerEntityRepository customerRepository;
    private AccountEntityRepository accountRepository;

    public List<Report> report(Long customerId, String start, String end) throws BankException {
        try {
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date dateStart = simpleDateFormat.parse(start);
            calendar.setTime(dateStart);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            dateStart = calendar.getTime();

            Date dateEnd = simpleDateFormat.parse(end);
            calendar.setTime(dateEnd);
            calendar.set(Calendar.HOUR_OF_DAY, 23);
            calendar.set(Calendar.MINUTE, 59);
            calendar.set(Calendar.SECOND, 59);
            dateEnd = calendar.getTime();

            System.out.println(dateStart);
            System.out.println(dateEnd);

            CustomerEntity customerEntity = customerRepository.findById(customerId)
                    .orElseThrow(EntityNotFoundException::new);
            return accountRepository.getReport(customerEntity, dateStart, dateEnd);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BankException("REPORT_ERROR_GENERATE");
        }
    }
}
