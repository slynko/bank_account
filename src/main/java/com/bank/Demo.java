package com.bank;

import com.bank.account.BankService;
import com.bank.account.model.BankAccount;
import com.bank.account.model.Transaction;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

import static com.bank.account.model.TransactionType.CREDIT;
import static com.bank.account.model.TransactionType.DEBIT;

@Slf4j
public class Demo {
    public static void main(String[] args) {
        var account = BankAccount.initAccount();
        var accountOperator = new BankService();

        final var transactionCreditSalary = Transaction.from(BigDecimal.valueOf(5000.00), CREDIT, "salary");
        final var transactionDebitVacation = Transaction.from(BigDecimal.valueOf(555.55), DEBIT, "vacation");
        final var transactionCreditRevenue = Transaction.from(BigDecimal.valueOf(100.00), CREDIT, "revenue");
        final var transactionDebitShopping = Transaction.from(BigDecimal.valueOf(123.45), DEBIT, "shopping");

        accountOperator.executeTransaction(account, transactionCreditSalary);
        accountOperator.executeTransaction(account, transactionDebitVacation);
        accountOperator.executeTransaction(account, transactionCreditRevenue);
        accountOperator.executeTransaction(account, transactionDebitShopping);

        accountOperator.getHistory(account);
        log.info(accountOperator.getBalance(account).toString());
    }
}
