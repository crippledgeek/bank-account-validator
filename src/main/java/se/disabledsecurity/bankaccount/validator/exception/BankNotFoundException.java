package se.disabledsecurity.bankaccount.validator.exception;

public class BankNotFoundException extends RuntimeException {
    public BankNotFoundException() {}

    public BankNotFoundException(String message) {
        super(message);
    }

    public BankNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
