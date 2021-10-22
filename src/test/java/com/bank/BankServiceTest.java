package com.bank;

import com.bank.account.BankAccountOperator;
import com.bank.account.BankService;
import com.bank.account.model.BankAccount;
import com.bank.account.model.Transaction;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.bank.account.model.TransactionType.CREDIT;
import static com.bank.account.model.TransactionType.DEBIT;
import static org.hamcrest.MatcherAssert.assertThat;
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
        assertThat(account.getBalance(), Matchers.comparesEqualTo(BigDecimal.valueOf(123.45)));
    }

    @Test
    public void should_credit_account_multiple_times() {
        //given
        final var transactionCredit = Transaction.from(BigDecimal.valueOf(123.45), CREDIT, "salary");
        final var transactionCreditAnother = Transaction.from(BigDecimal.valueOf(321.10), CREDIT, "revenue");

        //when
        accountOperator.executeTransaction(account, transactionCredit);
        accountOperator.executeTransaction(account, transactionCreditAnother);

        //then
        assertThat(account.getBalance(), Matchers.comparesEqualTo(BigDecimal.valueOf(444.55)));
    }

    @Test
    public void should_debit_account() {
        //given
        prepareAccountWithSum(account, BigDecimal.valueOf(1000.0));
        final var transactionDebit = Transaction.from(BigDecimal.valueOf(555.55), DEBIT, "vacation");

        //when
        accountOperator.executeTransaction(account, transactionDebit);

        //then
        assertThat(account.getBalance(), Matchers.comparesEqualTo(BigDecimal.valueOf(444.45)));
    }

    @Test
    public void should_debit_account_multiple_times() {
        //given
        prepareAccountWithSum(account, BigDecimal.valueOf(1000.00));
        final var transactionDebit = Transaction.from(BigDecimal.valueOf(555.55), DEBIT, "vacation");
        final var transactionDebitAnother = Transaction.from(BigDecimal.valueOf(123.45), DEBIT, "shopping");

        //when
        accountOperator.executeTransaction(account, transactionDebit);
        accountOperator.executeTransaction(account, transactionDebitAnother);

        //then
        assertThat(account.getBalance(), Matchers.comparesEqualTo(BigDecimal.valueOf(321.00)));
    }

    @Test
    public void should_debit_and_credit_account_multiple_times() {
        //given
        final var transactionCredit = Transaction.from(BigDecimal.valueOf(5000.00), CREDIT, "salary");
        final var transactionDebit = Transaction.from(BigDecimal.valueOf(555.55), DEBIT, "vacation");
        final var transactionCreditAnother = Transaction.from(BigDecimal.valueOf(100.00), CREDIT, "revenue");
        final var transactionDebitAnother = Transaction.from(BigDecimal.valueOf(123.45), DEBIT, "shopping");

        //when
        accountOperator.executeTransaction(account, transactionCredit);
        accountOperator.executeTransaction(account, transactionDebit);
        accountOperator.executeTransaction(account, transactionCreditAnother);
        accountOperator.executeTransaction(account, transactionDebitAnother);

        //then
        assertThat(account.getBalance(), Matchers.comparesEqualTo(BigDecimal.valueOf(4421.0)));
    }

    @Test
    public void should_get_history() {
        //given
        final var transactionCredit = Transaction.from(BigDecimal.valueOf(5000.00), CREDIT, "salary");
        final var transactionDebit = Transaction.from(BigDecimal.valueOf(555.55), DEBIT, "vacation");
        final var transactionCreditAnother = Transaction.from(BigDecimal.valueOf(100.00), CREDIT, "revenue");
        final var transactionDebitAnother = Transaction.from(BigDecimal.valueOf(123111.45), DEBIT, "shopping");
        final var expectedHistory = Stream.of(transactionCredit, transactionDebit, transactionCreditAnother, transactionDebitAnother)
                .map(Transaction::toString)
                .collect(Collectors.joining(", "));
        //when
        accountOperator.executeTransaction(account, transactionCredit);
        accountOperator.executeTransaction(account, transactionDebit);
        accountOperator.executeTransaction(account, transactionCreditAnother);
        accountOperator.executeTransaction(account, transactionDebitAnother);

        final String resultHistory = accountOperator.getHistory(account);

        //then
        assertEquals(expectedHistory, resultHistory);
    }


    private void prepareAccountWithSum(final BankAccount account, final BigDecimal amount) {
        final var transactionCredit = Transaction.from(amount, CREDIT, "preparing amount");
        accountOperator.executeTransaction(account, transactionCredit);
    }
}
