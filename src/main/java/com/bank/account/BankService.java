package com.bank.account;

import com.bank.account.model.BankAccount;
import com.bank.account.model.Transaction;
import lombok.extern.slf4j.Slf4j;

import java.util.stream.Collectors;

@Slf4j
public class BankService implements BankAccountOperator {

    @Override
    public void executeTransaction(final BankAccount account, final Transaction transaction) {
        account.acceptTransaction(transaction);
    }

    @Override
    public String getHistory(final BankAccount account) {
        final var history = account.getTransactions().stream()
                .map(Transaction::toString)
                .collect(Collectors.joining(", "));
        log.info(history);
        return history;
    }

}
