package com.bank.account;

import com.bank.account.model.BankAccount;
import com.bank.account.model.Transaction;

public interface BankAccountOperator {

    void executeTransaction(final BankAccount account, final Transaction transaction);

}
