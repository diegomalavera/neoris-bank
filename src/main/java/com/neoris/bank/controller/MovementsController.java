package com.neoris.bank.controller;

import com.neoris.bank.entity.Movement;
import com.neoris.bank.exception.BankException;
import com.neoris.bank.service.MovementService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/movimientos")
public class MovementsController {

    private MovementService movementService;

    @GetMapping
    public ResponseEntity<List<Movement>> list() throws BankException {
        return new ResponseEntity<>(movementService.list(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Movement> create(@Valid @RequestBody Movement movement) throws BankException {
        return new ResponseEntity<>(movementService.create(movement), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movement> update(@PathVariable("id") Long id, @Valid @RequestBody Movement movement) throws BankException {
        return new ResponseEntity<>(movementService.update(id, movement), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) throws BankException {
        movementService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
