package com.will.localcache.localcacheserver.config;

import com.github.xiaoymin.knife4j.core.util.CollectionUtils;
import com.github.xiaoymin.knife4j.spring.extension.OpenApiExtensionResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;
import java.util.List;

/**
 * swagger配置
 *
 * @author cwq
 */
@EnableOpenApi
@Configuration
@Import(BeanValidatorPluginsConfiguration.class)
public class Knife4jConfig {

    private final OpenApiExtensionResolver openApiExtensionResolver;

    public Knife4jConfig(OpenApiExtensionResolver openApiExtensionResolver) {
        this.openApiExtensionResolver = openApiExtensionResolver;
    }

    @Bean
    public Docket defaultApi() {
        List<SecurityScheme> securitySchemes = Collections.singletonList(new ApiKey("Authorization", "Authorization", "header"));

        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;

        List<SecurityContext> securityContexts = Collections.singletonList(SecurityContext.builder()
                .securityReferences(CollectionUtils.newArrayList(new SecurityReference("Authorization", authorizationScopes)))
                .forPaths(PathSelectors.regex("^(?!auth).*$"))
                .build());

        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .select()
                //这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.basePackage("com.will.localcache.localcacheserver.controller"))
                .paths(PathSelectors.any())
                .build()
                .extensions(openApiExtensionResolver.buildSettingExtensions())
                .securityContexts(securityContexts).securitySchemes(securitySchemes);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("本地缓存 API")
                .description("本地缓存模块 接口文档")
                .version("2.0")
                .build();
    }
}
