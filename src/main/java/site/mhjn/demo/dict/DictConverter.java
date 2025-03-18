package site.mhjn.demo.dict;

import jakarta.persistence.AttributeConverter;

public abstract class DictConverter<T extends Dict<C>, C> implements AttributeConverter<T, C> {
    private final Class<T> clazz;
    public DictConverter(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public C convertToDatabaseColumn(T attribute) {
        return attribute.getCode();
    }

    @Override
    public T convertToEntityAttribute(C dbData) {
        for (T item : clazz.getEnumConstants()) {
            if (item.getCode().equals(dbData)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown database value: " + dbData);
    }
}
