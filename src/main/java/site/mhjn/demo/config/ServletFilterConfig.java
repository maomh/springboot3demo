package site.mhjn.demo.config;

import jakarta.annotation.Resource;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import site.mhjn.demo.filter.RequestLoggingFilter;
import site.mhjn.demo.filter.TraceIdLoggingFilter;
import site.mhjn.demo.kit.RequestKit;

@Configuration
public class ServletFilterConfig {

    @Resource
    private SecurityProperties securityProperties;

    @Bean
    public FilterRegistrationBean<RequestLoggingFilter> requestLoggingFilter() {
        FilterRegistrationBean<RequestLoggingFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new RequestLoggingFilter().requestMatcher(RequestKit.anyOf(
                "/demo/**",
                "/api/**"
        )));
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(securityProperties.getFilter().getOrder() - 1);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<TraceIdLoggingFilter> traceIdLoggingFilter() {
        FilterRegistrationBean<TraceIdLoggingFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new TraceIdLoggingFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return registrationBean;
    }
}
