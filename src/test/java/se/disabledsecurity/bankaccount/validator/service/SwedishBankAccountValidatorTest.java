package se.disabledsecurity.bankaccount.validator.service;

import io.vavr.control.Either;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import se.disabledsecurity.bankaccount.validator.exception.ModulusException;
import se.disabledsecurity.bankaccount.validator.model.internal.BankAccount;

import static org.junit.jupiter.api.Assertions.*;

class SwedishBankAccountValidatorTest {

    @DisplayName("Should validate Multitude Bank account")
    @Test
    void shouldValidateMultitudeBankAccount() {
        var multitudeBankAccount = BankAccount.parse("9071-4172383").get();

        assertEquals("Multitude Bank", multitudeBankAccount.getBank().getBankName(),
                     "Multitude Bank bank account number returns correct bank name");
        assertEquals("9071", multitudeBankAccount.getSortingCode(),
                     "Multitude Bank bank account number returns correct clearing number");
        assertEquals("4172383", multitudeBankAccount.getAccountNumber(),
                     "Multitude Bank bank account number returns correct account number");
    }

    @DisplayName("Should validate Handelsbanken account")
    @Test
    public void shouldValidateHandelsbankenAccount() {
        var handelsbanken = BankAccount.parse("6789123456789").get();

        assertEquals("Handelsbanken", handelsbanken.getBank().getBankName(),
                     "Handelsbanken bank account number returns correct bank name");
        assertEquals("6789", handelsbanken.getSortingCode(),
                     "Handelsbanken bank account number returns correct clearing number");
        assertEquals("123456789", handelsbanken.getAccountNumber(),
                     "Handelsbanken bank account number returns correct account number");
    }

    @DisplayName("Should verify Swedbank account with five digit sorting code")
    @Test
    void shouldVerifySwedbankAccountWithFiveDigitSortingCode() {
        var swedbank5 = BankAccount.parse("8424-4,983 189 224-6").get();

        assertEquals("Swedbank", swedbank5.getBank().getBankName(),
                     "Swedbank account number with five digit clearing number returns correct bank name");
        assertEquals("84244", swedbank5.getSortingCode(),
                     "Swedbank account number with five digit clearing number returns correct clearing number");
        assertEquals("9831892246", swedbank5.getAccountNumber(),
                     "Swedbank account number with five digit clearing number returns correct account number");
    }

    @DisplayName("Should verify Sparbanken Tanum account")
    @Test
    void shouldVerifySparbankenTanumAccount() {
        Either<ModulusException, BankAccount> result = BankAccount.parse("8351-9,392 242 224-5");

        assertTrue(result.isRight(), "Parsing should succeed");

        result.peek(sparbankenTanum -> {
            assertEquals("Swedbank", sparbankenTanum.getBank().getBankName(),
                         "Sparbanken Tanum account number returns correct bank name");
            assertEquals("83519", sparbankenTanum.getSortingCode(),
                         "Sparbanken Tanum account number returns correct clearing number");
            assertEquals("3922422245", sparbankenTanum.getAccountNumber(),
                         "Sparbanken Tanum account number returns correct account number");
        }).peekLeft(error -> {
            fail("Parsing failed with error: " + error.getMessage());
        });
    }

    @DisplayName("Should verify Sparbanken Hudiksvall account")
    @Test
    void shouldVerifySparbankenHudiksvallAccount() {
        var hudik = BankAccount.parse("8129-9,043 386 711-6").get();

        assertEquals("Swedbank", hudik.getBank().getBankName(),
                     "Sparbank i Hudiksvall account number returns correct bank name");
        assertEquals("81299", hudik.getSortingCode(),
                     "Sparbank i Hudiksvall account number returns correct clearing number");
        assertEquals("0433867116", hudik.getAccountNumber(),
                     "Sparbank i Hudiksvall account number returns correct account number");
    }

    @DisplayName("Should verify Nordea Personkonto")
    @Test
    void shouldVerifyNordeaPersonkonto() {
        var nordeaPersonnumber = BankAccount.parse("3300 000620-5124").get();

        assertEquals("Nordea", nordeaPersonnumber.getBank().getBankName(),
                     "Nordea personnumber account number returns correct bank name");
        assertEquals("3300", nordeaPersonnumber.getSortingCode(),
                     "Nordea personnumber account number returns correct clearing number");
        assertEquals("0006205124", nordeaPersonnumber.getAccountNumber(),
                     "Nordea personnumber account number returns correct account number");
    }

    @DisplayName("Should verify Klarna account number")
    @Test
    void shouldVerifyKlarnaAccountNumber() {
        var klarna = BankAccount.parse("97891111113").get();

        assertEquals("Klarna Bank", klarna.getBank().getBankName(),
                     "Klarna Bank account number returns correct bank name");
        assertEquals("9789", klarna.getSortingCode(),
                     "Klarna Bank account number returns correct clearing number");
        assertEquals("1111113", klarna.getAccountNumber(),
                     "Klarna Bank account number returns correct account number");
    }

    @DisplayName("Should throw for invalid account number")
    @Test
    void shouldThrowForInvalidAccountNumber() {
        assertThrows(IllegalArgumentException.class, () -> BankAccount.parse("123456789").get(),
                     "Should throw IllegalArgumentException for invalid account number");
    }

    @DisplayName("Should throw if check digit is invalid")
    @Test
    void shouldThrowIfCheckDigitIsInvalid() {
        assertThrows(IllegalArgumentException.class, () -> BankAccount.parse("6789123456788").peekLeft(e -> e.getClass()),
                     "Should throw IllegalArgumentException if check digit is invalid");
    }

    @DisplayName("Should throw if check digit on five digit sorting code is invalid")
    @Test
    void shouldThrowIfCheckDigitOnFiveDigitSortingCodeIsInvalid() {
        assertThrows(IllegalArgumentException.class, () -> BankAccount
                             .parse("8424-1,983 189 224-6").get(),
                     "Should throw IllegalArgumentException if check digit on five digit sorting code is invalid");
    }
}