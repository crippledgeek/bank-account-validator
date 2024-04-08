package se.disabledsecurity.bankaccount.validator.exception;

public class ModulusException extends RuntimeException {
    public ModulusException() {}

    public ModulusException(String message) {
        super(message);
    }

    public ModulusException(String message, Throwable cause) {
        super(message, cause);
    }
}
