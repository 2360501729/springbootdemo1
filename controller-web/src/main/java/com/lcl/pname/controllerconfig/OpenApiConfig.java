package com.lcl.pname.controllerconfig;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * swagger OpenApi 配置
 *
 * @author hello  Word
 */
@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI().info(
                        new Info().title("System API")
                                .description("xxx系统API")
                                .version("v0.0.1")
                                .license(new License()
                                        .name("Apache 2.0")
                                        .url("http://www.lcl.org")
                                )
                )
                .externalDocs(new ExternalDocumentation()
                        .description("springboot project Documentation")
                        .url("https://xxxx.xx.github.org/docs"));
    }
}
