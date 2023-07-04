package com.anuj.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
public class SwaggerConfig {

    //for security purpose
    public static final String AUTHORIZATION_HEADER = "Authorization";

    //for security purpose
    private ApiKey apiKey()
    {
        return new ApiKey("JWT",AUTHORIZATION_HEADER,"header");
    }

    //for security purpose
    private List<SecurityContext> securityContext()
    {
        return Arrays.asList(SecurityContext.builder().securityReferences(securityReferences()).build());
    }

    private List<SecurityReference> securityReferences()
    {
        AuthorizationScope authorizationScope = new AuthorizationScope("global","accessEverything");
        return Arrays.asList(
                new SecurityReference("JWT",
                        new AuthorizationScope[] {authorizationScope}  //returning anonymous array and placing authScope at 0 index
                ));
    }

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getInfo())
                .securityContexts(securityContext())    //for adding security
                .securitySchemes(Arrays.asList(apiKey()))     //for adding security
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any()).build();
    }

    public ApiInfo getInfo()
    {
        return new ApiInfo("Blogging Application : Backend by Durgesh",
                "This is the course for backend",
                "1.0",
                "Term of Services",
                new Contact("Anuj Garg","https://github.com/anujg0028","anujg0028@gmail.com"),
                "License of APIs",
                "API License URL",
                Collections.emptyList());
    }
}
