package com.trafigura.equity.position.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ClassName Swagger2Config
 * @Description TODO
 * @Author cheng
 **/
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    /**
     * 该方法用于创建API接口
     *
     * @return Docket
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
            .apiInfo(apiInfo())
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.trafigura.equity.position.controller"))
            .paths(PathSelectors.any())
            .build();
    }

    /**
     * 该方法用于创建API信息
     *
     * @return ApiInfo
     */
    private ApiInfo apiInfo() {
        Contact contact = new Contact("cheng", "https://blog.csdn.net/changgongcheng_yq/", "zhangcheng_java@163.com");
        return new ApiInfoBuilder()
            .title("Trafigura Business API")
            .description("Business API 文档")
            .termsOfServiceUrl("http://localhost:8080/")
            .contact(contact)
            .version("v1.0.0")
            .build();
    }

    /**
     * 该方法用于配置UI
     *
     * @return UiConfiguration
     */
    @Bean
    public UiConfiguration uiConfig() {
        return UiConfigurationBuilder
            .builder()
            .deepLinking(true)
            .displayOperationId(false)
            .defaultModelsExpandDepth(1)
            .defaultModelExpandDepth(1)
            .defaultModelRendering(ModelRendering.EXAMPLE)
            .displayRequestDuration(false)
            .docExpansion(DocExpansion.NONE)
            .filter(true)
            .maxDisplayedTags(null)
            .operationsSorter(OperationsSorter.ALPHA)
            .showExtensions(false)
            .tagsSorter(TagsSorter.ALPHA)
            .validatorUrl(null)
            .build();
    }
}
