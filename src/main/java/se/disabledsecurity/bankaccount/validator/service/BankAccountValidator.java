package se.disabledsecurity.bankaccount.validator.service;

import io.vavr.control.Either;
import se.disabledsecurity.bankaccount.validator.exception.ModulusException;
import se.disabledsecurity.bankaccount.validator.model.internal.BankAccount;

public interface BankAccountValidator {
    Either<ModulusException, BankAccount> validate(BankAccount account);
}
