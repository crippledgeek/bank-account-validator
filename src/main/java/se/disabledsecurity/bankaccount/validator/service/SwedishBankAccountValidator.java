package se.disabledsecurity.bankaccount.validator.service;

import io.vavr.control.Either;
import org.springframework.stereotype.Service;
import se.disabledsecurity.bankaccount.validator.exception.ModulusException;
import se.disabledsecurity.bankaccount.validator.model.internal.BankAccount;
import se.disabledsecurity.bankaccount.validator.model.internal.BankAccountType;
import se.disabledsecurity.bankaccount.validator.model.internal.CommentType;
import se.disabledsecurity.bankaccount.validator.model.internal.ModulusCheck;

@Service
public class SwedishBankAccountValidator implements BankAccountValidator {

    public Either<ModulusException, BankAccount> validate(BankAccount account) {
        if (account.getBank().getType() == BankAccountType.TYPE_ONE) {
            return validateType1(account);
        } else {
            return validateType2(account);
        }
    }

    private Either<ModulusException, BankAccount> validateType1(BankAccount account) {
        String fullNumber = account.getSortingCode() + account.getAccountNumber();
        String numberToCheck = account.getBank().getComment() == CommentType.TYPE_ONE
                               ? fullNumber.substring(1, 10)
                               : fullNumber.substring(0, 10);

        return ModulusCheck.mod11.apply(numberToCheck)
                                 .flatMap(isValid -> isValid ? Either.right(account) : Either.left(new ModulusException("Invalid Type 1 account: check digit mismatch")));
    }

    private Either<ModulusException, BankAccount> validateType2(BankAccount account) {
        String accountNumber = account.getAccountNumber();
        CommentType comment = account.getBank().getComment();

        return switch (comment) {
            case TYPE_ONE, TYPE_THREE -> ModulusCheck.mod10
                    .apply(accountNumber)
                    .flatMap(isValid -> isValid ?
                                        Either.right(account) :
                                        Either.left(new ModulusException(
                                                "Invalid Type 2 account (Comment 1 or 3): modulus 10 check failed")));
            case TYPE_TWO -> ModulusCheck.mod11
                    .apply(accountNumber)
                    .flatMap(isValid -> isValid ?
                                        Either.right(account) :
                                        Either.left(new ModulusException(
                                                "Invalid Type 2 account (Comment 2): modulus 11 check failed")));
        };
    }
}