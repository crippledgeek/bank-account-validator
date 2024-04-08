package se.disabledsecurity.bankaccount.validator.model.internal;

import java.util.Arrays;

public enum BankAccountType {
    TYPE_ONE("1"),
    TYPE_TWO("2");

    private final String type;

    BankAccountType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    public static BankAccountType getTypeByValue(String value) {
        BankAccountType[] values = BankAccountType.values();
        return Arrays
                .stream(values, 0, values.length)
                .filter(type -> type
                        .getType()
                        .equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No enum constant found for " + value));
    }
}
