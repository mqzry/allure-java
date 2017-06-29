package io.qameta.allure.servletapi;

import io.qameta.allure.attachment.http.HttpRequestAttachment;
import io.qameta.allure.attachment.http.HttpResponseAttachment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Collections;
import java.util.stream.Stream;

/**
 * @author charlie (Dmitry Baev).
 */
public final class HttpServletAttachmentBuilder {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpServletAttachmentBuilder.class);

    private HttpServletAttachmentBuilder() {
        throw new IllegalStateException();
    }

    public static HttpRequestAttachment buildRequest(final HttpServletRequest request) {
        final HttpRequestAttachment requestAttachment = new HttpRequestAttachment("Request")
                .withUrl(request.getRequestURI());
        Collections.list(request.getHeaderNames())
                .forEach(name -> {
                    final String value = request.getHeader(name);
                    requestAttachment.withHeader(name, value);
                });

        Stream.of(request.getCookies())
                .forEach(cookie -> requestAttachment.withCookie(cookie.getName(), cookie.getValue()));
        requestAttachment.withBody(getBody(request));
        return requestAttachment;
    }

    public static HttpResponseAttachment buildResponse(final HttpServletResponse response) {
        final HttpResponseAttachment responseBuilder = new HttpResponseAttachment("Response");
        response.getHeaderNames()
                .forEach(name -> response.getHeaders(name)
                        .forEach(value -> responseBuilder.withHeader(name, value)));
        return responseBuilder;
    }

    public static String getBody(final HttpServletRequest request) {
        final StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = request.getReader()) {
            readBody(sb, reader);
        } catch (IOException e) {
            LOGGER.warn("Could not read request body", e);
        }
        return sb.toString();
    }

    @SuppressWarnings("PMD.AssignmentInOperand")
    public static void readBody(final StringBuilder sb,
                                final BufferedReader reader) throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
    }
}
