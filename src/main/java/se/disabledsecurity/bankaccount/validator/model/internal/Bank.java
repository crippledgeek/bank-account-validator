package se.disabledsecurity.bankaccount.validator.model.internal;



import org.apache.commons.lang3.Range;
import se.disabledsecurity.bankaccount.validator.exception.BankNotFoundException;

import java.util.Arrays;
import java.util.List;

public enum Bank {
        AION_BANK("Aion Bank", BankAccountType.TYPE_ONE, CommentType.TYPE_ONE, List.of(Range.of(9580, 9589)), 11, 11),
        AVANZA_BANK("Avanza Bank", BankAccountType.TYPE_ONE, CommentType.TYPE_TWO, List.of(Range.of(9550, 9569)), 11, 11),
        BLUESTEP_FINANS("BlueStep Finans", BankAccountType.TYPE_ONE, CommentType.TYPE_ONE, List.of(Range.of(9680, 9689)), 11, 11),
        BNP_PARIBAS("BNP Paribas", BankAccountType.TYPE_ONE, CommentType.TYPE_TWO, List.of(Range.of(9470, 9479)), 11, 11),
        CITIBANK("Citibank", BankAccountType.TYPE_ONE, CommentType.TYPE_TWO, List.of(Range.of(9040, 9049)), 11, 11),
        DANSKE_BANK_TYPE1("Danske Bank", BankAccountType.TYPE_ONE, CommentType.TYPE_ONE, List.of(Range.of(1200, 1399), Range.of(2400, 2499)), 11, 11),
        DNB_BANK("DNB Bank", BankAccountType.TYPE_ONE, CommentType.TYPE_TWO, List.of(Range.of(9190, 9199), Range.of(9260, 9269)), 11, 11),
        EKOBANKEN("Ekobanken", BankAccountType.TYPE_ONE, CommentType.TYPE_TWO, List.of(Range.of(9700, 9709)), 11, 11),
        ERIK_PENSER("Erik Penser", BankAccountType.TYPE_ONE, CommentType.TYPE_TWO, List.of(Range.of(9590, 9599)), 11, 11),
        ICA_BANKEN("ICA Banken", BankAccountType.TYPE_ONE, CommentType.TYPE_ONE, List.of(Range.of(9270, 9279)), 11, 11),
        IKANO_BANK("IKANO Bank", BankAccountType.TYPE_ONE, CommentType.TYPE_ONE, List.of(Range.of(9170, 9179)), 11, 11),
        JAK_MEDLEMSBANK("JAK Medlemsbank", BankAccountType.TYPE_ONE, CommentType.TYPE_TWO, List.of(Range.of(9670, 9679)), 11, 11),
        KLARNA_BANK("Klarna Bank", BankAccountType.TYPE_ONE, CommentType.TYPE_TWO, List.of(Range.of(9780, 9789)), 11, 11),
        LANDSHYPOTEK("Landshypotek", BankAccountType.TYPE_ONE, CommentType.TYPE_TWO, List.of(Range.of(9390, 9399)), 11, 11),
        LUNAR_BANK("Lunar Bank", BankAccountType.TYPE_ONE, CommentType.TYPE_TWO, List.of(Range.of(9710, 9719)), 11, 11),
        LAN_SPAR_BANK_SVERIGE("Lån & Spar Bank Sverige", BankAccountType.TYPE_ONE, CommentType.TYPE_ONE, List.of(Range.of(9630, 9639)), 11, 11),
        LANSFORSÄKRINGAR_BANK_TYPE1("Länsförsäkringar Bank", BankAccountType.TYPE_ONE, CommentType.TYPE_ONE, List.of(Range.of(3400, 3499), Range.of(9060, 9069)), 11, 11),
        LANSFORSÄKRINGAR_BANK_TYPE2("Länsförsäkringar Bank", BankAccountType.TYPE_ONE, CommentType.TYPE_TWO, List.of(Range.of(9020, 9029)), 11, 11),
        MARGINALEN_BANK("Marginalen Bank", BankAccountType.TYPE_ONE, CommentType.TYPE_ONE, List.of(Range.of(9230, 9239)), 11, 11),
        MULTITUDE_BANK("Multitude Bank", BankAccountType.TYPE_ONE, CommentType.TYPE_ONE, List.of(Range.of(9070, 9079)), 11, 11),
        NOBA_BANK_GROUP_AB("NOBA Bank Group AB", BankAccountType.TYPE_ONE, CommentType.TYPE_TWO, List.of(Range.of(9640, 9649)), 11, 11),
        NORDEA_TYPE1_COMMENT1("Nordea", BankAccountType.TYPE_ONE, CommentType.TYPE_ONE, List.of(Range.of(1100, 1199), Range.of(1400, 2099), Range.of(3000, 3299), Range.of(3301, 3399), Range.of(3410, 3781), Range.of(3783, 3999)), 11, 11),
        NORDEA_TYPE1_COMMENT2("Nordea", BankAccountType.TYPE_ONE, CommentType.TYPE_TWO, List.of(Range.of(4000, 4999)), 11, 11),
        NORDNET_BANK("Nordnet Bank", BankAccountType.TYPE_ONE, CommentType.TYPE_TWO, List.of(Range.of(9100, 9109)), 11, 11),
        NORTHMILL_BANK("Northmill Bank", BankAccountType.TYPE_ONE, CommentType.TYPE_TWO, List.of(Range.of(9750, 9759)), 11, 11),
        RESURS_BANK("Resurs Bank", BankAccountType.TYPE_ONE, CommentType.TYPE_ONE, List.of(Range.of(9280, 9289)), 11, 11),
        RIKSGÄLDEN_TYPE1("Riksgälden", BankAccountType.TYPE_ONE, CommentType.TYPE_TWO, List.of(Range.of(9880, 9889)), 11, 11),
        SANTANDER_CONSUMER_BANK("Santander Consumer Bank", BankAccountType.TYPE_ONE, CommentType.TYPE_ONE, List.of(Range.of(9460, 9469)), 11, 11),
        SBAB("SBAB", BankAccountType.TYPE_ONE, CommentType.TYPE_ONE, List.of(Range.of(9250, 9259)), 11, 11),
        SEB("SEB", BankAccountType.TYPE_ONE, CommentType.TYPE_ONE, List.of(Range.of(5000, 5999), Range.of(9120, 9124), Range.of(9130, 9149)), 11, 11),
        SKANDIABANKEN("Skandiabanken", BankAccountType.TYPE_ONE, CommentType.TYPE_TWO, List.of(Range.of(9150, 9169)), 11, 11),
        SVEA_BANK("Svea Bank", BankAccountType.TYPE_ONE, CommentType.TYPE_TWO, List.of(Range.of(9660, 9669)), 11, 11),
        SWEDBANK_TYPE1("Swedbank", BankAccountType.TYPE_ONE, CommentType.TYPE_ONE, List.of(Range.of(7000, 7999)), 11, 11),
        ÅLANDSBANKEN("Ålandsbanken", BankAccountType.TYPE_ONE, CommentType.TYPE_TWO, List.of(Range.of(2300, 2399)), 11, 11),
        DANSKE_BANK_TYPE2("Danske Bank", BankAccountType.TYPE_TWO, CommentType.TYPE_ONE, List.of(Range.of(9180, 9189)), 10, 10),
        HANDELSBANKEN("Handelsbanken", BankAccountType.TYPE_TWO, CommentType.TYPE_TWO, List.of(Range.of(6000, 6999)), 8, 9),
        NORDEA_TYPE2_COMMENT1("Nordea", BankAccountType.TYPE_TWO, CommentType.TYPE_ONE, List.of(Range.of(3300, 3300), Range.of(3782, 3782)), 10, 10),
        NORDEA_PLUSGIROT("Nordea Plusgirot", BankAccountType.TYPE_TWO, CommentType.TYPE_THREE, List.of(Range.of(9500, 9549), Range.of(9960, 9969)), 2, 8),
        RIKSGÄLDEN_TYPE2("Riksgälden", BankAccountType.TYPE_TWO, CommentType.TYPE_ONE, List.of(Range.of(9890, 9899)), 10, 10),
        SPARBANKEN_SYD("Sparbanken Syd", BankAccountType.TYPE_TWO, CommentType.TYPE_ONE, List.of(Range.of(9570, 9579)), 10, 10),
        SWEDBANK_TYPE2_COMMENT3("Swedbank", BankAccountType.TYPE_TWO, CommentType.TYPE_THREE, List.of(Range.of(8000, 8999)), 10, 11),
        SWEDBANK_TYPE2_COMMENT1("Swedbank", BankAccountType.TYPE_TWO, CommentType.TYPE_ONE, List.of(Range.of(9300, 9349)), 10, 10);

        ;

    private final String bankName;
    private final BankAccountType type;
    private final CommentType comment;
    private final List<Range<Integer>> ranges;
    private final int accountMinLength;
    private final int accountMaxLength;

    Bank(String bankName, BankAccountType type, CommentType comment, List<Range<Integer>> ranges, int accountMinLength, int accountMaxLength) {
        this.bankName = bankName;
        this.type = type;
        this.comment = comment;
        this.ranges = ranges;
        this.accountMinLength = accountMinLength;
        this.accountMaxLength = accountMaxLength;
    }


    public static Bank getBankBySortingCode(int sortingCode) {
        return Arrays.stream(Bank.values())
                     .filter(bank -> bank.getRanges().stream().anyMatch(range -> range.contains(sortingCode)))
                     .findFirst()
                     .orElseThrow(() -> new BankNotFoundException("No bank found for sorting code: " + sortingCode));
    }

    public List<Range<Integer>> getRanges() {
        return ranges;
    }

    public int getAccountMinLength() {
        return accountMinLength;
    }

    public int getAccountMaxLength() {
        return accountMaxLength;
    }

    public BankAccountType getType() {
        return type;
    }

    public String getBankName() {
        return bankName;
    }

    public CommentType getComment() {
        return comment;
    }
}
