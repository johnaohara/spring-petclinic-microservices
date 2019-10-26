/*
 * Copyright 2002-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.quarkus.samples.petclinic.api;

import io.quarkus.beans.factory.annotation.Value;
import io.quarkus.boot.QuarkusApplication;
import io.quarkus.boot.autoconfigure.QuarkusBootApplication;
import io.quarkus.cloud.client.circuitbreaker.EnableCircuitBreaker;
import io.quarkus.cloud.client.discovery.EnableDiscoveryClient;
import io.quarkus.cloud.client.loadbalancer.LoadBalanced;
import io.quarkus.context.annotation.Bean;
import io.quarkus.core.io.ClassPathResource;
import io.quarkus.core.io.Resource;
import io.quarkus.http.MediaType;
import io.quarkus.web.client.RestTemplate;
import io.quarkus.web.reactive.function.server.RequestPredicates;
import io.quarkus.web.reactive.function.server.RouterFunction;
import io.quarkus.web.reactive.function.server.RouterFunctions;
import io.quarkus.web.reactive.function.server.ServerResponse;


/**
 * @author Maciej Szarlinski
 */
@EnableDiscoveryClient
@EnableCircuitBreaker
@QuarkusBootApplication
public class ApiGatewayApplication {

    public static void main(String[] args) {
        QuarkusApplication.run(ApiGatewayApplication.class, args);
    }

    @Bean
    @LoadBalanced
    RestTemplate loadBalancedRestTemplate() {
        return new RestTemplate();
    }

    @Value("classpath:/static/index.html")
    private Resource indexHtml;

    /**
     * workaround solution for forwarding to index.html
     * @see <a href="https://github.com/quarkus-projects/quarkus-boot/issues/9785">#9785</a>
     */
    @Bean
    RouterFunction<?> routerFunction() {
        RouterFunction router = RouterFunctions.resources("/**", new ClassPathResource("static/"))
            .andRoute(RequestPredicates.GET("/"),
                request -> ServerResponse.ok().contentType(MediaType.TEXT_HTML).syncBody(indexHtml));
        return router;
    }
}
