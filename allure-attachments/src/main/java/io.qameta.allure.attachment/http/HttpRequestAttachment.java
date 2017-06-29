package io.qameta.allure.attachment.http;

import io.qameta.allure.attachment.AttachmentData;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author charlie (Dmitry Baev).
 */
public class HttpRequestAttachment implements AttachmentData {

    private final String name;

    private String url;

    private String method;

    private String body;

    private final Map<String, String> headers;

    private final Map<String, String> cookies;

    public HttpRequestAttachment(final String name) {
        this.headers = new HashMap<>();
        this.cookies = new HashMap<>();
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public HttpRequestAttachment withUrl(final String url) {
        Objects.requireNonNull(url, "Url must not be null value");
        this.url = url;
        return this;
    }

    public String getMethod() {
        return method;
    }

    public HttpRequestAttachment withMethod(final String method) {
        Objects.requireNonNull(method, "Method must not be null value");
        this.method = method;
        return this;
    }

    public String getBody() {
        return body;
    }

    public HttpRequestAttachment withBody(final String body) {
        this.body = body;
        return this;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public HttpRequestAttachment withHeader(final String name, final String value) {
        if (Objects.nonNull(name)) {
            this.headers.put(name, value);
        }
        return this;
    }

    public HttpRequestAttachment withHeaders(final Map<String, String> headers) {
        if (Objects.nonNull(headers)) {
            this.headers.putAll(headers);
        }
        return this;
    }

    public Map<String, String> getCookies() {
        return cookies;
    }

    public HttpRequestAttachment withCookie(final String name, final String value) {
        if (Objects.nonNull(name)) {
            this.cookies.put(name, value);
        }
        return this;
    }

    public HttpRequestAttachment withCookies(final Map<String, String> cookies) {
        if (Objects.nonNull(cookies)) {
            this.cookies.putAll(cookies);
        }
        return this;
    }

}
