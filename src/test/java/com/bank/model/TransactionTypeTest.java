package com.bank.model;

import com.bank.account.model.TransactionType;
import org.junit.Test;

import java.util.List;

import static com.bank.account.model.TransactionType.CREDIT;
import static com.bank.account.model.TransactionType.DEBIT;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class TransactionTypeTest {

    @Test
    public void should_contain_credit_and_debit_types() {
        //given//when
        final var types = TransactionType.values();

        //then
        assertEquals(2, types.length);
        assertArrayEquals(List.of(CREDIT, DEBIT).toArray(), types);
    }
}
