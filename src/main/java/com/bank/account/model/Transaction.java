package com.bank.account.model;

import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

import static com.bank.account.validation.ValidationUtils.requireValidAmount;
import static java.util.Objects.requireNonNull;

@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Transaction {
    @ToString.Exclude
    UUID id;
    BigDecimal amount;
    TransactionType type;
    OffsetDateTime date;
    String comment;

    public static Transaction from(final BigDecimal amount, final TransactionType type, final String comment) {
        requireValidAmount(amount);
        requireNonNull(type);
        return new Transaction(UUID.randomUUID(), amount, type, OffsetDateTime.now(), comment);
    }
}
