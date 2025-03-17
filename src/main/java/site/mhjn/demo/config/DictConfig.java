package site.mhjn.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import site.mhjn.demo.consts.CustomerType;
import site.mhjn.demo.consts.DictItemJsonDeserializer;
import site.mhjn.demo.consts.DictItemJsonSerializer;

@Configuration
public class DictConfig {

    @Bean
    public DictItemJsonDeserializer<CustomerType, String> customerTypeJsonDeserializer() {
        return new DictItemJsonDeserializer<>(CustomerType.class);
    }

    @Bean
    public DictItemJsonSerializer<CustomerType, String> customerTypeJsonSerializer() {
        return new DictItemJsonSerializer<>(CustomerType.class);
    }
}
