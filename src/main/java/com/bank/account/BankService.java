package com.bank.account;

import com.bank.account.model.BankAccount;
import com.bank.account.model.Transaction;

public class BankService implements BankAccountOperator {

    @Override
    public void executeTransaction(BankAccount account, Transaction transaction) {
        account.acceptTransaction(transaction);
    }

}
