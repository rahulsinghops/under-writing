package com.ipru.mca.underwriting.configuration;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
	public Docket getDocketForDocumentation() {
		// return new Docket(DocumentationType.SWAGGER_2).select().build();
		return new Docket(DocumentationType.SWAGGER_2).select().paths(PathSelectors.ant("/mca/*")).build().apiInfo(apiInfo() );

	}

	private ApiInfo apiInfo() {
		//title titledescription descriptionversion versiontermsOfServiceUrl termsOfServiceUrlcontact contactlicense licenselicenseUrl license urlvendorExtensions vendor extensions
		
		ApiInfo apiInfo = new ApiInfo("UnderWriting", "Bike suggestion and details", "V_1.1", "Free to use", new Contact("rahul singh", "www.bikeguru.com", "siingh.rahul@gmail.com"), "RS Licence", "www.bikeguru.com",Collections.emptyList());
		return apiInfo;
	}

}
