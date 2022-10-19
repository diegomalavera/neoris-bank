package com.neoris.bank.controller;

import com.neoris.bank.entity.Account;
import com.neoris.bank.service.AccountService;
import com.neoris.bank.util.TestUtil;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class AccountControllerTest {

    @Mock
    private AccountService accountService;
    private MockMvc mockMvc;

    @BeforeEach
    public void beforeEach() {
        AccountController accountController = new AccountController(accountService);
        mockMvc = MockMvcBuilders.standaloneSetup(accountController).build();
    }

    @Test
    void getAccountList_shouldReturnOk() throws Exception {
        List<Account> accountList = new ArrayList<>();
        accountList.add(Account.builder()
                .accountId(1L)
                .number(new BigDecimal(123456))
                .type("Cuenta de ahorros")
                .balance(new BigDecimal(100000))
                .status(true)
                .customerId(1L)
                .build());
        accountList.add(Account.builder()
                .accountId(2L)
                .number(new BigDecimal(654321))
                .type("Cuenta corriente")
                .balance(new BigDecimal(5000))
                .status(true)
                .customerId(2L)
                .build());

        Mockito.when(accountService.list()).thenReturn(accountList);

        mockMvc.perform(MockMvcRequestBuilders.get("/cuentas")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].number", Matchers.is(123456)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].number", Matchers.is(654321)))
                .andReturn();
    }

    @Test
    void createAccount_shouldReturnOk() throws Exception {
        Account account = Account.builder()
                .accountId(1L)
                .number(new BigDecimal(123456))
                .type("Cuenta de ahorros")
                .balance(new BigDecimal(100000))
                .status(true)
                .customerId(1L)
                .build();

        Mockito.when(accountService.create(ArgumentMatchers.any())).thenReturn(account);

        mockMvc.perform(MockMvcRequestBuilders.post("/cuentas")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(TestUtil.toJson(account)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.number", Matchers.is(123456)))
                .andReturn();
    }
}
