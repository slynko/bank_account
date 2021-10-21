package com.bank.account.model;

import lombok.*;

import java.math.BigDecimal;
import java.util.*;

import static com.bank.account.model.TransactionType.CREDIT;

@Data
@Setter(AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankAccount that = (BankAccount) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
