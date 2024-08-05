package se.disabledsecurity.bankaccount.validator.exception;

public class BankAccountTypeNotFoundException extends RuntimeException {
    public BankAccountTypeNotFoundException() {}

    public BankAccountTypeNotFoundException(String message) {
        super(message);
    }

    public BankAccountTypeNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
