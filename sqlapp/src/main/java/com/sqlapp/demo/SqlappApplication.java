package com.sqlapp.demo;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class SqlappApplication {

	public static void main(String[] args) {
		SpringApplication.run(SqlappApplication.class, args);
	
	}

	@Bean
	public Docket swaggerConfiguration() {
		// Return a prepared Docket instance
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.sqlapp.demo.controller"))
				.build()
				.apiInfo(getApiInfo());
	}
	
	private ApiInfo getApiInfo() {
		return new ApiInfo("Thymeleaf Demo with Spring Boot", 
				"Application to Play with Employee and Department", 
				"1.0.0", 
				"Free to use", 
				new Contact("XXX XXX", "www.facebook.com/", "xyz@xyz.com"),
				"Apache License", 
				"www.gooogle.com",
				Collections.emptyList());
	}
}
