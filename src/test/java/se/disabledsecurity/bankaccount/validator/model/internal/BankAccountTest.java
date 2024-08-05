package se.disabledsecurity.bankaccount.validator.model.internal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import io.vavr.control.Either;
import se.disabledsecurity.bankaccount.validator.exception.ModulusException;

class BankAccountTest {

    @DisplayName("Should validate Multitude Bank account")
    @Test
    void shouldValidateMultitudeBankAccount() {
        Either<ModulusException, BankAccount> result = BankAccount.parse("9071-4172383").flatMap(BankAccount::validate);

        result.peek(multitudeBankAccount -> {
            assertEquals("Multitude Bank", multitudeBankAccount.getBank().getBankName(),
                         "Multitude Bank bank account number returns correct bank name");
            assertEquals("90714", multitudeBankAccount.getSortingCode(),
                         "Multitude Bank bank account number returns correct clearing number");
            assertEquals("172383", multitudeBankAccount.getAccountNumber(),
                         "Multitude Bank bank account number returns correct account number");
        });
    }

    @DisplayName("Should validate Handelsbanken account")
    @Test
    void shouldValidateHandelsbankenAccount() {
        Either<ModulusException, BankAccount> result = BankAccount.parse("6789123456789").flatMap(BankAccount::validate);

        result.peek(handelsbanken -> {
            assertEquals("Handelsbanken", handelsbanken.getBank().getBankName(),
                         "Handelsbanken bank account number returns correct bank name");
            assertEquals("67891", handelsbanken.getSortingCode(),
                         "Handelsbanken bank account number returns correct clearing number");
            assertEquals("23456789", handelsbanken.getAccountNumber(),
                         "Handelsbanken bank account number returns correct account number");
        });
    }

    @DisplayName("Should verify Swedbank account with five digit sorting code")
    @Test
    void shouldVerifySwedbankAccountWithFiveDigitSortingCode() {
        Either<ModulusException, BankAccount> result = BankAccount.parse("8424-4,983 189 224-6").flatMap(BankAccount::validate);

        result.peek(swedbank5 -> {
            assertEquals("Swedbank", swedbank5.getBank().getBankName(),
                         "Swedbank account number with five digit clearing number returns correct bank name");
            assertEquals("84244", swedbank5.getSortingCode(),
                         "Swedbank account number with five digit clearing number returns correct clearing number");
            assertEquals("9831892246", swedbank5.getAccountNumber(),
                         "Swedbank account number with five digit clearing number returns correct account number");
        });
    }

    @DisplayName("Should verify Sparbanken Tanum account")
    @Test
    void shouldVerifySparbankenTanumAccount() {
        Either<ModulusException, BankAccount> result = BankAccount.parse("8351-9,392 242 224-5").flatMap(BankAccount::validate);

        result.peek(sparbankenTanum -> {
            assertEquals("Swedbank", sparbankenTanum.getBank().getBankName(),
                         "Sparbanken Tanum account number returns correct bank name");
            assertEquals("83519", sparbankenTanum.getSortingCode(),
                         "Sparbanken Tanum account number returns correct clearing number");
            assertEquals("3922422245", sparbankenTanum.getAccountNumber(),
                         "Sparbanken Tanum account number returns correct account number");
        });
    }

    @DisplayName("Should verify Sparbanken Hudiksvall account")
    @Test
    void shouldVerifySparbankenHudiksvallAccount() {
        Either<ModulusException, BankAccount> result = BankAccount.parse("8129-9,043 386 711-6").flatMap(BankAccount::validate);

        result.peek(hudik -> {
            assertEquals("Swedbank", hudik.getBank().getBankName(),
                         "Sparbank i Hudiksvall account number returns correct bank name");
            assertEquals("81299", hudik.getSortingCode(),
                         "Sparbank i Hudiksvall account number returns correct clearing number");
            assertEquals("0433867116", hudik.getAccountNumber(),
                         "Sparbank i Hudiksvall account number returns correct account number");
        });
    }

    @DisplayName("Should verify Nordea Personkonto")
    @Test
    void shouldVerifyNordeaPersonkonto() {
        Either<ModulusException, BankAccount> result = BankAccount.parse("3300 000620-5124").flatMap(BankAccount::validate);

        result.peek(nordeaPersonnumber -> {
            assertEquals("Nordea", nordeaPersonnumber.getBank().getBankName(),
                         "Nordea personnumber account number returns correct bank name");
            assertEquals("3300", nordeaPersonnumber.getSortingCode(),
                         "Nordea personnumber account number returns correct clearing number");
            assertEquals("0006205124", nordeaPersonnumber.getAccountNumber(),
                         "Nordea personnumber account number returns correct account number");
        });
    }

    @DisplayName("Should verify Klarna account number")
    @Test
    void shouldVerifyKlarnaAccountNumber() {
        Either<ModulusException, BankAccount> result = BankAccount.parse("97891111113").flatMap(BankAccount::validate);

        result.peek(klarna -> {
            assertEquals("Klarna Bank", klarna.getBank().getBankName(),
                         "Klarna Bank account number returns correct bank name");
            assertEquals("97891", klarna.getSortingCode(),
                         "Klarna Bank account number returns correct clearing number");
            assertEquals("111113", klarna.getAccountNumber(),
                         "Klarna Bank account number returns correct account number");
        });
    }

    @DisplayName("Should fail for invalid account number")
    @Test
    void shouldFailForInvalidAccountNumber() {
        Either<ModulusException, BankAccount> result = BankAccount.parse("123456789");
        assertTrue(result.isLeft(), "Parsing should fail for invalid account number");
    }

    @DisplayName("Should fail if check digit is invalid")
    @Test
    void shouldFailIfCheckDigitIsInvalid() {
        Either<ModulusException, BankAccount> result = BankAccount.parse("6789123456788").flatMap(BankAccount::validate);
        assertTrue(result.isLeft(), "Validation should fail if check digit is invalid");
    }

    @DisplayName("Should fail if check digit on five digit sorting code is invalid")
    @Test
    void shouldFailIfCheckDigitOnFiveDigitSortingCodeIsInvalid() {
        Either<ModulusException, BankAccount> result = BankAccount.parse("8424-1,983 189 224-6").flatMap(BankAccount::validate);
        assertTrue(result.isLeft(), "Validation should fail if check digit on five digit sorting code is invalid");
    }
}