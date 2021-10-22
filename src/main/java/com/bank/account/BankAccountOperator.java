package com.bank.account;

import com.bank.account.model.BankAccount;
import com.bank.account.model.Transaction;

import java.math.BigDecimal;

public interface BankAccountOperator {

    void executeTransaction(final BankAccount account, final Transaction transaction);

    String getHistory(final BankAccount account);

    BigDecimal getBalance(final BankAccount account);

}
