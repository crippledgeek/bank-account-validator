package se.disabledsecurity.bankaccount.validator.model.internal;

import io.vavr.control.Either;
import io.vavr.control.Try;
import lombok.Getter;
import se.disabledsecurity.bankaccount.validator.exception.ModulusException;

@Getter
public class BankAccount {
    private final String sortingCode;
    private final String accountNumber;
    private final Bank bank;

    private BankAccount(String sortingCode, String accountNumber, Bank bank) {
        this.sortingCode = sortingCode;
        this.accountNumber = accountNumber;
        this.bank = bank;
    }

    public static Either<ModulusException, BankAccount> parse(String bankAccountNumber) {
        return Try
                .of(() -> {
                    String cleanedAccountString = bankAccountNumber.replaceAll("[^0-9]", "");
                    if (cleanedAccountString.length() < 5) {
                        throw new IllegalArgumentException("Invalid account string: too short");
                    }

                    String sortingCode = cleanedAccountString.substring(0, 4);
                    String accountNumber = cleanedAccountString.substring(4);

                    Bank bank = Bank.getBankBySortingCode(Integer.parseInt(sortingCode));
                    int expectedLength = bank.getType() == BankAccountType.TYPE_ONE ?
                                         bank.getAccountMaxLength() :
                                         4 + bank.getAccountMaxLength();

                    if (cleanedAccountString.length() != expectedLength) {
                        throw new IllegalArgumentException("Invalid account string: incorrect length");
                    }

                    return new BankAccount(sortingCode, accountNumber, bank);
                }).toEither().mapLeft(e -> new ModulusException("Failed to parse account string", e));
    }

}

