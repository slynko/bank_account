package com.bank;

import com.bank.account.BankAccountOperator;
import com.bank.account.BankService;
import com.bank.account.model.BankAccount;
import com.bank.account.model.Transaction;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static com.bank.account.model.TransactionType.CREDIT;
import static org.junit.Assert.assertEquals;

public class BankServiceTest {

    private BankAccount account;
    private BankAccountOperator accountOperator;

    @Before
    public void setUp() {
        account = BankAccount.initAccount();
        accountOperator = new BankService();
    }

    @Test
    public void should_credit_account() {
        //given
        final var transactionCredit = Transaction.from(BigDecimal.valueOf(123.45), CREDIT, "vacation");

        //when
        accountOperator.executeTransaction(account, transactionCredit);

        //then
        assertEquals(account.getBalance(), BigDecimal.valueOf(123.45));
    }

    @Test
    public void should_credit_account_multiple_times() {
        //given
        final var transactionCredit = Transaction.from(BigDecimal.valueOf(123.45), CREDIT, "salary");
        final var transactionCredit1 = Transaction.from(BigDecimal.valueOf(321.10), CREDIT, "revenue");

        //when
        accountOperator.executeTransaction(account, transactionCredit);
        accountOperator.executeTransaction(account, transactionCredit1);

        //then
        assertEquals(account.getBalance(), BigDecimal.valueOf(444.55));
    }
}
