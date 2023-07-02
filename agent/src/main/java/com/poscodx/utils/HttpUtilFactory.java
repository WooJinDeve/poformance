package com.poscodx.utils;

import com.poscodx.KeyValue;
import java.net.URI;
import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.web.util.UriComponentsBuilder;

public class HttpUtilFactory {

    public static URI createUrl(String url, List<KeyValue> params) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url);
        params.forEach(keyValue -> uriBuilder.queryParam(keyValue.getKey(), keyValue.getValue()));
        return uriBuilder.build().encode().toUri();
    }

    public static HttpHeaders createHeaders(List<KeyValue> header) {
        HttpHeaders headers = new HttpHeaders();
        header.forEach(keyValue -> headers.add(keyValue.getKey(), keyValue.getValue()));
        return headers;
    }
}
