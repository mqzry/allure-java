package io.qameta.allure.attachment.http;

import io.qameta.allure.attachment.AttachmentData;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author charlie (Dmitry Baev).
 */
public class HttpResponseAttachment implements AttachmentData {

    private final String name;

    private String url;

    private String body;

    private int responseCode;

    private final Map<String, String> headers;

    private final Map<String, String> cookies;

    public HttpResponseAttachment(final String name) {
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

    public HttpResponseAttachment withUrl(final String url) {
        Objects.requireNonNull(url, "Url must not be null value");
        this.url = url;
        return this;
    }

    public String getBody() {
        return body;
    }

    public HttpResponseAttachment withBody(final String body) {
        this.body = body;
        return this;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public HttpResponseAttachment withResponseCode(final int responseCode) {
        Objects.requireNonNull(responseCode, "Response code must not be null value");
        this.responseCode = responseCode;
        return this;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public HttpResponseAttachment withHeader(final String name, final String value) {
        if (Objects.nonNull(name)) {
            this.headers.put(name, value);
        }
        return this;
    }

    public HttpResponseAttachment withHeaders(final Map<String, String> headers) {
        if (Objects.nonNull(headers)) {
            this.headers.putAll(headers);
        }
        return this;
    }

    public Map<String, String> getCookies() {
        return cookies;
    }

    public HttpResponseAttachment withCookie(final String name, final String value) {
        if (Objects.nonNull(name)) {
            this.cookies.put(name, value);
        }
        return this;
    }

    public HttpResponseAttachment withCookies(final Map<String, String> cookies) {
        if (Objects.nonNull(cookies)) {
            this.cookies.putAll(cookies);
        }
        return this;
    }

}
