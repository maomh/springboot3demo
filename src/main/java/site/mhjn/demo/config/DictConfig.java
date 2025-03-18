package site.mhjn.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import site.mhjn.demo.dict.CustomerType;
import site.mhjn.demo.dict.DictJsonDeserializer;
import site.mhjn.demo.dict.DictJsonSerializer;

//@Configuration
public class DictConfig {

    @Bean
    public DictJsonDeserializer<CustomerType, String> customerTypeJsonDeserializer() {
        return new DictJsonDeserializer<>(CustomerType.class);
    }

    @Bean
    public DictJsonSerializer<CustomerType, String> customerTypeJsonSerializer() {
        return new DictJsonSerializer<>(CustomerType.class);
    }

}
