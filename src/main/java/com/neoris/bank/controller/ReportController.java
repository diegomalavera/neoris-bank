package com.neoris.bank.controller;

import com.neoris.bank.entity.Report;
import com.neoris.bank.exception.BankException;
import com.neoris.bank.service.ReportService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/reporte")
public class ReportController {

    private ReportService reportService;

    @GetMapping
    public ResponseEntity<List<Report>> list(
            @RequestParam Long customerId,
            @RequestParam String dateStart,
            @RequestParam String dateEnd) throws BankException {
        return new ResponseEntity<>(reportService.report(customerId, dateStart, dateEnd), HttpStatus.OK);
    }
}
