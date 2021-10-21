package com.bank.account.model;

import lombok.*;

import java.util.*;

@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Bank {
    Map<UUID, BankAccount> accounts;

    public static Bank initBank() {
        return new Bank(new HashMap<>());
    }

    public void addAccount(final BankAccount account) {
        accounts.put(UUID.randomUUID(), account);
    }

    public void getAccount(final UUID accountId) {
        accounts.get(accountId);
    }
}
