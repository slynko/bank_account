package com.bank.model;

import com.bank.account.model.*;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static com.bank.account.model.TransactionType.CREDIT;
import static com.bank.account.model.TransactionType.DEBIT;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

public class BankAccountTest {

    @Test
    public void should_init_bank_account() {
        //given//when
        final var account = BankAccount.initAccount();

        //then
        assertThat(account.getBalance(), Matchers.comparesEqualTo(BigDecimal.ZERO));
        assertEquals(account.getTransactions(), Collections.emptyList());
    }

    @Test
    public void should_accept_credit() {
        //given
        final var account = BankAccount.initAccount();
        final var transactionCredit = Transaction.from(BigDecimal.valueOf(1000), CREDIT, "salary");

        //when
        account.acceptTransaction(transactionCredit);

        //then
        assertThat(account.getBalance(), Matchers.comparesEqualTo(BigDecimal.valueOf(1000)));
        assertEquals(account.getTransactions(), List.of(transactionCredit));
    }

    @Test
    public void should_accept_debit() {
        //given
        final var account = BankAccount.initAccount();
        final var transactionDebit = Transaction.from(BigDecimal.valueOf(5), DEBIT, "coffee");

        //when
        account.acceptTransaction(transactionDebit);

        //then
        assertThat(account.getBalance(), Matchers.comparesEqualTo(BigDecimal.valueOf(-5)));
        assertEquals(account.getTransactions(), List.of(transactionDebit));
    }


}
