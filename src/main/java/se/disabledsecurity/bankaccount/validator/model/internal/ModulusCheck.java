package se.disabledsecurity.bankaccount.validator.model.internal;

import io.vavr.control.Either;
import io.vavr.control.Try;
import se.disabledsecurity.bankaccount.validator.exception.ModulusException;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.IntStream;

public class ModulusCheck {
    private static final int[] modulusCalculationArray = {0, 2, 4, 6, 8, 1, 3, 5, 7, 9};

    public static final Function<String, Either<ModulusException, Boolean>> mod10 = number -> Try
            .of(() -> calculateModulus10(number))
            .toEither()
            .mapLeft(Throwable::getCause)
            .mapLeft(ex -> new ModulusException("Unexpected exception in mod10", ex));

    private static boolean calculateModulus10(String number) {
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
            .mapLeft(e -> new ModulusException("Failed to parse character to number in mod11", e));
}
