package site.mhjn.demo.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

import java.io.IOException;
import java.util.UUID;

@Slf4j
public class TraceIdLoggingFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            String traceId = UUID.randomUUID().toString();
            MDC.put("traceId", traceId);
            log.debug("Put traceId to MDC: {}", traceId);
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            MDC.remove("traceId");
        }
    }
}
