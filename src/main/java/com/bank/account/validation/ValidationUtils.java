package com.bank.account.validation;

import com.bank.account.exception.InvalidAmountException;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;

import static java.lang.String.format;

@Slf4j
public class ValidationUtils {

    public static void requireValidAmount(final BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidAmountException(format("Amount could not be less than 0, amount: %s", amount));
        }
    }

    public static void warnIfBelow0(final BigDecimal balance) {
        if (balance.compareTo(BigDecimal.ZERO) < 0) {
            log.warn(format("Your account balance is less than 0, current balance: %s", balance));
        }
    }
}
