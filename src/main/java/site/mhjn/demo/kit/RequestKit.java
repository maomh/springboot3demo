package site.mhjn.demo.kit;

import jakarta.servlet.http.HttpServletRequest;
import lombok.experimental.UtilityClass;

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
}
