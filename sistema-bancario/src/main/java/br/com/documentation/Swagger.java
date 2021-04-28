package br.com.documentation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
		.select()
        .apis(RequestHandlerSelectors.basePackage("br.com.controller"))
        .paths(PathSelectors.any())
        .build();
	}
	
	@SuppressWarnings({ "deprecation", "unused" })
	private ApiInfo metaInfo() {

		ApiInfo apiInfo = new ApiInfo(
        		"Spring Boot Swagger API",
                "Banco API REST",
                "API REST de Banco",
                "1.0",
                "Terms of Service",
                "Apache License Version 2.0",
                "https://www.apache.org/licesen.html"
        );

        return apiInfo;
    }
}
