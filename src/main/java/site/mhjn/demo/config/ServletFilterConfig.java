package site.mhjn.demo.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import site.mhjn.demo.filter.RequestLoggingFilter;
import site.mhjn.demo.filter.TraceIdLoggingFilter;

@Configuration
public class ServletFilterConfig {

    @Bean
    public FilterRegistrationBean<RequestLoggingFilter> requestLoggingFilter() {
        FilterRegistrationBean<RequestLoggingFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new RequestLoggingFilter());
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<TraceIdLoggingFilter> traceIdLoggingFilter() {
        FilterRegistrationBean<TraceIdLoggingFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new TraceIdLoggingFilter());
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }
}
