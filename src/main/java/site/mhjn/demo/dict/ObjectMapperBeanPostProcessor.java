package site.mhjn.demo.dict;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import jakarta.annotation.Nullable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AssignableTypeFilter;
import org.springframework.stereotype.Component;
import site.mhjn.demo.kit.CollectionKit;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@SuppressWarnings("all")
public class ObjectMapperBeanPostProcessor implements BeanPostProcessor {
    private List<DictJsonDeserializer> deserializers;
    private List<DictJsonSerializer> serializers;

    public ObjectMapperBeanPostProcessor(
            @Nullable List<DictJsonDeserializer> deserializers,
            @Nullable List<DictJsonSerializer> serializers ) {
        this.deserializers = deserializers == null ? new ArrayList<>() : deserializers;
        this.serializers = serializers == null ? new ArrayList<>() : serializers;

        ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
        scanner.addIncludeFilter(new AssignableTypeFilter(Dict.class));
        scanner.findCandidateComponents("site.mhjn.demo.dict").forEach(beanDefinition -> {
            try {
                Class<?> clazz = Class.forName(beanDefinition.getBeanClassName());
                if (clazz.isEnum()) {
                    this.deserializers.add(new DictJsonDeserializer(clazz));
                    this.serializers.add(new DictJsonSerializer(clazz));
                }
            } catch (Exception e) {
                log.error("error", e);
            }
        });
    }


    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof ObjectMapper objectMapper) {

            SimpleModule module = new SimpleModule("Auto Regist DictItemModule");

            if (CollectionKit.isNotEmpty(deserializers)) {
                for (DictJsonDeserializer deserializer : deserializers) {
                    log.info("regist type={}, deserializer={}", deserializer.getClazz(), deserializer.getClass().getSimpleName());
                    module.addDeserializer(deserializer.getClazz(), deserializer);
                }
            }

            if (CollectionKit.isNotEmpty(serializers)) {
                for (DictJsonSerializer serializer : serializers) {
                    log.info("regist type={}, serializer={}", serializer.getClazz(), serializer.getClass().getSimpleName());
                    module.addSerializer(serializer.getClazz(), serializer);
                }
            }

            objectMapper.registerModule(module);
        }
        return bean;
    }
}
