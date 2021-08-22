package com.mayikt.filter;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author 周宇
 * @create 2021-08-22 23:14
 * 举一反三可以实现：黑名单，白名单，token效验...
 */
@Component
public class TokenGlobalFilter implements GlobalFilter, Ordered {

    @Value("${server.port}")
    private String serverPort;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //如何获取参数
        String token = exchange.getRequest().getQueryParams().getFirst("token");
        if(StringUtils.isEmpty(token)){
            ServerHttpResponse response = exchange.getResponse();
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);//500
            String msg = "token not is null ";
            DataBuffer buffer = response.bufferFactory().wrap(msg.getBytes());
            return response.writeWith(Mono.just(buffer));
        }
        //直接转发到真实服务
        //return chain.filter(exchange);

        // 在请求头中存放serverPort serverPort
        ServerHttpRequest request = exchange.getRequest().mutate().header("serverPort", serverPort).build();
        return chain.filter(exchange.mutate().request(request).build());
    }

    /**
     * 制定优先级
     */
    @Override
    public int getOrder() {
        return -1;
    }
}
