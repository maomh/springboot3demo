package site.mhjn.demo.dict;

import jakarta.persistence.Convert;
import jakarta.persistence.Converter;
import lombok.Getter;

@Getter
@Convert(converter = CustomerLevel.CustomerLevelConverter.class)
public enum CustomerType implements Dict<String> {
    CPC("CPC", "A"),
    PC("PC", "PC"),
    D("D", "D");

    private final String label;
    private final String code;

    CustomerType(String label, String code) {
        this.label = label;
        this.code = code;
    }

    @Converter(autoApply = true)
    public static class CustomerTypeConverter extends DictConverter<CustomerType, String> {
        public CustomerTypeConverter() {
            super(CustomerType.class);
        }
    }
}
