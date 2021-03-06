package com.bank.account.model;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.*;

import static com.bank.account.model.TransactionType.CREDIT;
import static com.bank.account.validation.ValidationUtils.warnIfBelow0;

@Data
@Setter(AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public class BankAccount {
    private UUID id;
    private BigDecimal balance;
    private final List<Transaction> transactions;

    public static BankAccount initAccount() {
        return new BankAccount(UUID.randomUUID(), BigDecimal.ZERO, new ArrayList<>());
    }

    public void acceptTransaction(final Transaction transaction) {
        transactions.add(transaction);
        balance = calculateBalance();
        warnIfBelow0(balance);
    }

    private BigDecimal calculateBalance() {
        return transactions.stream()
                .reduce(BigDecimal.ZERO,
                        (amount, transaction) -> {
                            if (transaction.getType() == CREDIT) {
                                return amount.add(transaction.getAmount());
                            } else {
                                return amount.subtract(transaction.getAmount());
                            }
                        },
                        BigDecimal::add);
    }
}
