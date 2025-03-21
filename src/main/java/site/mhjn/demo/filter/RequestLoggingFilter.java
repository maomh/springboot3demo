package site.mhjn.demo.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.util.matcher.RequestMatcher;
import site.mhjn.demo.kit.RequestKit;

import java.io.IOException;

@Slf4j
public class RequestLoggingFilter implements Filter {
    private RequestMatcher requestMatcher;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        if (requestMatcher == null || requestMatcher.matches(request)) {
            log.info("Request info \n\n{}\n\n", RequestKit.getRequestInfo(request));
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    public RequestLoggingFilter requestMatcher(RequestMatcher requestMatcher) {
        this.requestMatcher = requestMatcher;
        return this;
    }
}
