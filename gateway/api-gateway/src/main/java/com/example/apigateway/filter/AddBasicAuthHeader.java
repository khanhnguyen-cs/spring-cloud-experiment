package com.example.apigateway.filter;

import org.apache.commons.codec.binary.Base64;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractNameValueGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Component
public class AddBasicAuthHeader extends AbstractNameValueGatewayFilterFactory {

    @Override
    public GatewayFilter apply(NameValueConfig config) {
        return (exchange, chain) -> {
            String auth = config.getName() + ":" + config.getValue();
            byte[] encodedAuth = Base64.encodeBase64(
                    auth.getBytes(StandardCharsets.US_ASCII) );
            String authHeader = "Basic " + new String(encodedAuth);
            ServerHttpRequest request = exchange.getRequest().mutate()
                    .header(AUTHORIZATION, authHeader)
                    .build();

            return chain.filter(exchange.mutate().request(request).build());
        };
    }
}
