package com.guli.getway.filter;

import com.google.gson.JsonObject;
import com.guli.utils.JwtUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class AuthGlobalFilter implements GlobalFilter, Ordered {
    private AntPathMatcher absMatcher;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String path=request.getURI().getPath();
        if (absMatcher.match("api/**/auth/**",path)) {
            List<String> token=request.getHeaders().get("token");
            if (null==token){
                ServerHttpResponse response = exchange.getResponse();
                return out(response);
            }else {
                boolean checkToken = JwtUtils.checkToken(token.get(0));
                if (!checkToken){
                    ServerHttpResponse response = exchange.getResponse();
                    return out(response);
                }
            }
        }
        if (absMatcher.match("/**/inner/**",path)){
            ServerHttpResponse response = exchange.getResponse();
            return out(response);
        }
        return chain.filter(exchange);
    }

    private Mono<Void> out(ServerHttpResponse response) {
        JsonObject message=new JsonObject();
        message.addProperty("success",false);
        message.addProperty("code",28004);
        message.addProperty("data","缺少权限");
        byte[] bytes=message.toString().getBytes(StandardCharsets.UTF_8);
        DataBuffer wrap = response.bufferFactory().wrap(bytes);
        response.getHeaders().add("Content-Type","application/json;charset=UTF-8");
        return response.writeWith(Mono.just(wrap));


    }

    @Override
    public int getOrder() {
        return 0;
    }


}
