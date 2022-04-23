package com.github.zzay.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
//import io.swagger.v3.oas.annotations.info.Contact;
//import io.swagger.v3.oas.annotations.info.Info;
//import io.swagger.v3.oas.annotations.security.SecurityScheme;
//import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 【Swagger-UI page】
 * http://localhost:9661/swagger-ui.html  [http://server:port/context-path/swagger-ui.html]
 * 【OpenAPI description(json format)】
 * http://localhost:9661/api-docs  [http://server:port/context-path/v3/api-docs]
 * 【BLOG】
 * https://blog.csdn.net/zxt521yt/article/details/110558598?ops_request_misc=%257B%2522request%255Fid%2522%253A%2522165063981016781435460190%2522%252C%2522scm%2522%253A%252220140713.130102334.pc%255Fblog.%2522%257D&request_id=165063981016781435460190&biz_id=0&utm_medium=distribute.pc_search_result.none-task-blog-2~blog~first_rank_ecpm_v1~hot_rank-7-110558598.nonecase&utm_term=springdoc&spm=1018.2226.3001.4450
 *
 * @author zzay
 * @className SpringdocOpenapiConfig
 * @description Springdoc配置
 * @create 2022/04/22 23:01
 */
@Configuration
public class OpenAPIConfig implements WebMvcConfigurer {

    // Title
    private static final String OPENAPI_TITLE = "News Content Management System";

    // Description
    private static final String OPENAPI_DESCRIPTION = "RESTful API Documentation of News Content Management System";

    // Version
    private static final String OPENAPI_VERSION = "1.0.0";

    // Contact name
    private static final String OPENAPI_CONTACT_NAME = "Zzay";

    // Contact URL
    private static final String OPENAPI_CONTACT_URL = "https://github.com/zZay132-4ONE?tab=repositories";

    // Contact email
    private static final String OPENAPI_CONTACT_EMAIL = "19271169@bjtu.edu.cn";

    // Security scheme
    private static final String SECURITY_SCHEME = "bearer";

    // Security scheme name
    private static final String SECURITY_SCHEME_NAME = "BearerAuth";

    // Security bearer format
    private static final String SECURITY_BEARER_FORMAT = "JWT";

    // Servers
    private static final List<Server> OPENAPI_SERVERS = new ArrayList<>(Collections.singletonList(
            new Server().url("http://localhost:9661").description("本地地址")
    ));

    /**
     * OpenAPI配置
     *
     * @return OpenAPI
     */
    @Bean
    public OpenAPI webOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title(OPENAPI_TITLE)
                        .description(OPENAPI_DESCRIPTION)
                        .version(OPENAPI_VERSION)
                        .contact(new Contact().name(OPENAPI_CONTACT_NAME).url(OPENAPI_CONTACT_URL).email(OPENAPI_CONTACT_EMAIL)))
                .servers(OPENAPI_SERVERS)
                .addSecurityItem(new SecurityRequirement().addList(SECURITY_SCHEME_NAME))
                .components(new Components()
                        .addSecuritySchemes(SECURITY_SCHEME_NAME,
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .name(SECURITY_SCHEME_NAME)
                                        .scheme(SECURITY_SCHEME)
                                        .bearerFormat(SECURITY_BEARER_FORMAT)));
    }

    /**
     * 分组配置：web
     *
     * @return GroupedOpenApi
     */
    @Bean
    public GroupedOpenApi webApi() {
        String[] packagesToScan = {
                "com.github.zzay.controller"
        };
        return GroupedOpenApi.builder()
                .group("web")
                .packagesToScan(packagesToScan)
                .build();
    }

}
