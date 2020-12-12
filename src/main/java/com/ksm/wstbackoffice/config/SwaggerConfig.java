package com.ksm.wstbackoffice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

  @Bean
  public Docket getSwaggerConfig() {
    return new Docket(DocumentationType.OAS_30)
        .apiInfo(new ApiInfoBuilder()
            .title("WST Backoffice")
            .description("A CRUD API to manage reference info")
            .version("0.0.1-SNAPSHOT")
            .contact(new Contact("Pinta Oleg", "", "oleg.sabfir@gmail.com"))
            .license("MIT")
            .licenseUrl("https://choosealicense.com/licenses/mit/")
            .build())
        //.tags(new Tag("Note", "Endpoints for CRUD operations on notes"))
        .select()
        .paths(PathSelectors.ant("/api/*"))
        .apis(RequestHandlerSelectors.basePackage("com.ksm"))
        .build();
  }
}
