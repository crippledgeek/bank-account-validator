package se.disabledsecurity.bankaccount.validator.functions;

import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.IntStream;

import io.vavr.control.Either;
import io.vavr.control.Try;
import se.disabledsecurity.bankaccount.validator.exception.ModulusException;
import se.disabledsecurity.bankaccount.validator.model.internal.BankAccountType;


public class ModulusCheck {
    protected static final int[] modulusCalculationArray = {0, 2, 4, 6, 8, 1, 3, 5, 7, 9}; // Explained numbers with comment in real scenario

    public static final Function<String, Either<ModulusException, Boolean>> mod10 = number -> Try
            .of(() -> calculateModulus(number))
            .toEither()
            .mapLeft(Throwable::getCause) // extract the cause from Throwabl
            .mapLeft(ex -> new ModulusException("Unexpected exception", ex));

    private static boolean calculateModulus(String number) {
        AtomicInteger isEvenLetter = new AtomicInteger(1);
        AtomicInteger accumulatedSum = new AtomicInteger(0);

        IntStream
                .rangeClosed(0, number.length() - 1)
                .map(i -> number.length() - 1 - i)
                .forEach(i -> performModulusCheckOnDigit(number, i, isEvenLetter, accumulatedSum));

        return accumulatedSum.get() % 10 == 0;
    }

    private static void performModulusCheckOnDigit(String number, int i, AtomicInteger isEvenDigit, AtomicInteger accumulatedSum) {
        char currentDigit = number.charAt(i);
        if (!Character.isDigit(currentDigit))
            throw new ModulusException("Invalid number format: Character " + currentDigit + " is not a digit in mod10");
        int currentNumber = Character.getNumericValue(currentDigit);
        isEvenDigit.getAndIncrement();
        accumulatedSum.addAndGet((isEvenDigit.get() % 2 != 0) ? modulusCalculationArray[currentNumber] : currentNumber);
    }

    public static final Function<String, Either<ModulusException, Boolean>> mod11 = number -> Try
            .of(() -> {
                int[] weights = {1, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
                int len = number.length();
                int[] arr = Arrays.copyOfRange(weights, weights.length - len, weights.length);
                int sum = 0;
                int val;

                while (len > 0) {
                    val = Integer.parseInt(String.valueOf(number.charAt(--len)));
                    sum += arr[len] * val;
                }

                return sum != 0 && sum % 11 == 0;
            })
            .toEither()
            .mapLeft(Throwable::getCause)
            .mapLeft(e -> new ModulusException("Failed to parse character to number in mod11"));

    public static final Function<String, Either<ModulusException, Boolean>> sortByCommentAndType = input -> Try
            .of(() -> {
                String[] splitInputs = input.split(",");
                BankAccountType type = BankAccountType.getTypeByValue(splitInputs[0]);
                String comment = splitInputs[1];
                String sortingCode = splitInputs[2];

                StringBuilder accountNumberBuilder = Optional
                        .ofNullable(splitInputs[3])
                        .map(StringBuilder::new)
                        .orElse(null);
                while (accountNumberBuilder.length() < 7) accountNumberBuilder.insert(0, "0");
                String accountNumber = String.valueOf(accountNumberBuilder);

                switch (type) {
                    case TYPE_ONE: // "1"
                        return switch (comment) {
                            case "1" -> mod11
                                    .apply(sortingCode.substring(1) + accountNumber)
                                    .get();
                            case "2" -> mod11
                                    .apply(sortingCode + accountNumber)
                                    .get();
                            default -> throw new ModulusException("Unhandled comment for type 1: " + comment);
                        };
                    case TYPE_TWO: // "2"
                        if ("2".equals(comment)) {
                            return mod11
                                    .apply(accountNumber)
                                    .get();
                        } else {
                            return mod10
                                    .apply(accountNumber)
                                    .get();
                        }
                    default:
                        throw new ModulusException("Unhandled type: " + type);
                }

            })
            .toEither()
            .mapLeft(Throwable::getCause)
            .mapLeft(ModulusCheck::handleModulusException);

    private static ModulusException handleModulusException(Throwable e) {
        return new ModulusException(e.getMessage());
    }
}
