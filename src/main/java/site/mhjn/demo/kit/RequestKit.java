package site.mhjn.demo.kit;

import jakarta.servlet.http.HttpServletRequest;
import lombok.experimental.UtilityClass;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatchers;

@UtilityClass
public class RequestKit {
    public static String getRequestInfo(final HttpServletRequest request) {
        StringBuilder sb = new StringBuilder();

        sb.append("Method: ").append(request.getMethod()).append(System.lineSeparator());
        sb.append("URL: ").append(request.getRequestURL()).append(System.lineSeparator());
        sb.append("URI: ").append(request.getRequestURI()).append(System.lineSeparator());
        sb.append("QueryString: ").append(request.getQueryString()).append(System.lineSeparator());
        sb.append("ContentType: ").append(request.getContentType()).append(System.lineSeparator());

        sb.append("Headers: ").append(System.lineSeparator());
        request.getHeaderNames().asIterator().forEachRemaining(headerName ->
                sb.append("  ").append(headerName).append("=").append(request.getHeader(headerName)).append(System.lineSeparator())
        );

        return sb.toString();
    }

    public static RequestMatcher anyOf(String ... patterns) {
        RequestMatcher[] matchers = new RequestMatcher[patterns.length];
        for (int i = 0; i < patterns.length; i++) {
            matchers[i] = AntPathRequestMatcher.antMatcher(patterns[i]);
        }
        return RequestMatchers.anyOf(matchers);
    }
}
