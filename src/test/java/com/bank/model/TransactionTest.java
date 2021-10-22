package com.bank.model;

import com.bank.account.exception.InvalidAmountException;
import com.bank.account.model.Transaction;
import org.junit.Test;

import java.math.BigDecimal;

import static com.bank.account.model.TransactionType.CREDIT;
import static com.bank.account.model.TransactionType.DEBIT;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TransactionTest {

    @Test
    public void should_create_credit_transaction() {
        //given//when
        final var transactionCredit = Transaction.from(BigDecimal.ONE, CREDIT, "revenue");

        //then
        assertEquals(transactionCredit.getType(), CREDIT);
        assertEquals(transactionCredit.getAmount(), BigDecimal.ONE);
        assertEquals(transactionCredit.getComment(), "revenue");
        assertNotNull(transactionCredit.getId());
        assertNotNull(transactionCredit.getDate());
    }

    @Test
    public void should_create_debit_transaction() {
        //given//when
        final var transactionCredit = Transaction.from(BigDecimal.ONE, DEBIT, "coffee");

        //then
        assertEquals(transactionCredit.getType(), DEBIT);
        assertEquals(transactionCredit.getAmount(), BigDecimal.ONE);
        assertEquals(transactionCredit.getComment(), "coffee");
        assertNotNull(transactionCredit.getId());
        assertNotNull(transactionCredit.getDate());
    }

    @Test(expected = InvalidAmountException.class)
    public void should_throw_an_exception_when_amount_is_null() {
        //given//when
        Transaction.from(null, DEBIT, "coffee");
    }

    @Test(expected = InvalidAmountException.class)
    public void should_throw_an_exception_when_amount_is_negative() {
        //given//when
        Transaction.from(BigDecimal.valueOf(-100), DEBIT, "coffee");
    }

    @Test(expected = NullPointerException.class)
    public void should_throw_an_exception_when_type_is_null() {
        //given//when
        Transaction.from(BigDecimal.valueOf(100), null, "coffee");
    }
}
