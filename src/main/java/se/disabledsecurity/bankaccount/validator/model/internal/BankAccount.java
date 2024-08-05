package se.disabledsecurity.bankaccount.validator.model.internal;

import io.vavr.control.Either;
import io.vavr.control.Try;
import se.disabledsecurity.bankaccount.validator.exception.ModulusException;

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
                    Bank bank = Bank.getBankBySortingCode(Integer.parseInt(sortingCode));

                    int sortingCodeLength = bank.getType() == BankAccountType.TYPE_ONE ? 4 : 5;
                    sortingCode = cleanedAccountString.substring(0, sortingCodeLength);
                    String accountNumber = cleanedAccountString.substring(sortingCodeLength);

                    int expectedLength = sortingCodeLength + bank.getAccountMaxLength();
                    if (cleanedAccountString.length() != expectedLength) {
                        throw new IllegalArgumentException("Invalid account string: incorrect length");
                    }

                    return new BankAccount(sortingCode, accountNumber, bank);
                }).toEither().mapLeft(e -> new ModulusException("Failed to parse account string", e));
    }

    public Either<ModulusException, BankAccount> validate() {
        return bank.getType() == BankAccountType.TYPE_ONE ? validateType1() : validateType2();
    }

    private Either<ModulusException, BankAccount> validateType1() {
        String fullNumber = sortingCode + accountNumber;
        String numberToCheck = bank.getComment() == CommentType.TYPE_ONE
                               ? fullNumber.substring(1, 11)  // Last 3 digits of sorting code + 7 digits of account number
                               : fullNumber.substring(0, 11); // Whole sorting code + 7 digits of account number

        return ModulusCheck.mod11.apply(numberToCheck)
                                 .flatMap(isValid -> isValid ? Either.right(this) : Either.left(new ModulusException("Invalid Type 1 account: check digit mismatch")));
    }

    private Either<ModulusException, BankAccount> validateType2() {
        return switch (bank.getComment()) {
            case TYPE_ONE, TYPE_THREE -> ModulusCheck.mod10
                    .apply(accountNumber)
                    .flatMap(isValid -> isValid ?
                                        Either.right(this) :
                                        Either.left(new ModulusException(
                                                "Invalid Type 2 account (Comment 1 or 3): modulus 10 check failed")));
            case TYPE_TWO -> ModulusCheck.mod11
                    .apply(accountNumber)
                    .flatMap(isValid -> isValid ?
                                        Either.right(this) :
                                        Either.left(new ModulusException(
                                                "Invalid Type 2 account (Comment 2): modulus 11 check failed")));
        };
    }

    public String getFormattedAccountNumber() {
        if (bank == Bank.SWEDBANK_TYPE1) {
            return String.format("%s-%s-%s", sortingCode, accountNumber.substring(0, 2), accountNumber.substring(2));
        } else if (bank.getType() == BankAccountType.TYPE_ONE) {
            return String.format("%s %s %s %s", sortingCode, accountNumber.substring(0, 2), accountNumber.substring(2, 5), accountNumber.substring(5));
        } else {
            // For Type 2 accounts, you might want to implement specific formatting rules based on the bank
            return sortingCode + " " + accountNumber;
        }
    }

    public String getSortingCode() {
        return sortingCode;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public Bank getBank() {
        return bank;
    }
}