package site.mhjn.demo.dict;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

@Getter
@RequiredArgsConstructor
public class DictJsonDeserializer<T extends Dict<C>, C> extends JsonDeserializer<T> {
    private final Class<T> clazz;

    @Override
    public T deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String code = p.getText();
        for (T item : clazz.getEnumConstants()) {
            C itemCode = item.getCode();
            if (itemCode instanceof String && itemCode.equals(code)) {
                return item;
            }
            if (String.valueOf(itemCode).equals(code)) {
                return item;
            }
        }
        throw new IllegalArgumentException("Unknown dict item code: " + code);
    }
}
