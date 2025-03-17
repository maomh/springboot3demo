package site.mhjn.demo.consts;


import jakarta.persistence.Convert;
import jakarta.persistence.Converter;
import lombok.Getter;

@Getter
@Convert(converter = CustomerLevel.CustomerLevelConverter.class)
public enum CustomerLevel implements DictItem<String> {
    STAR1("STAR1", "1星"),
    STAR2("STAR2", "2星"),
    STAR3("STAR3", "3星"),
    STAR4("STAR4", "4星"),
    STAR5("STAR5", "5星"),
    STAR6("STAR6", "6星");

    private final String code;
    private final String label;

    CustomerLevel(String code, String label) {
        this.code = code;
        this.label = label;
    }

    @Converter(autoApply = true)
    public static class CustomerLevelConverter extends DictItemConverter<CustomerLevel, String> {
        public CustomerLevelConverter() {
            super(CustomerLevel.class);
        }
    }
}
