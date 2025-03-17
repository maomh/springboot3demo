package site.mhjn.demo.consts;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import jakarta.annotation.Nullable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import site.mhjn.demo.assets.kit.CollectionKit;

import java.util.List;

@Slf4j
@Component
@SuppressWarnings("all")
public class ObjectMapperBeanPostProcessor implements BeanPostProcessor {

//    @Autowired(required = false)
    private List<DictItemJsonDeserializer> deserializers;

//    @Autowired(required = false)
    private List<DictItemJsonSerializer> serializers;

    public ObjectMapperBeanPostProcessor(
            @Nullable List<DictItemJsonDeserializer> deserializers,
            @Nullable List<DictItemJsonSerializer> serializers ) {
        this.deserializers = deserializers;
        this.serializers = serializers;
    }


    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof ObjectMapper objectMapper) {

            SimpleModule module = new SimpleModule("Auto Regist DictItemModule");

            if (CollectionKit.isNotEmpty(deserializers)) {
                for (DictItemJsonDeserializer deserializer : deserializers) {
                    log.info("regist type={}, deserializer={}", deserializer.getClazz(), deserializer.getClass().getSimpleName());
                    module.addDeserializer(deserializer.getClazz(), deserializer);
                }
            }

            if (CollectionKit.isNotEmpty(serializers)) {
                for (DictItemJsonSerializer serializer : serializers) {
                    log.info("regist type={}, serializer={}", serializer.getClazz(), serializer.getClass().getSimpleName());
                    module.addSerializer(serializer.getClazz(), serializer);
                }
            }

            objectMapper.registerModule(module);
        }
        return bean;
    }
}
