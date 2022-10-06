package com.reymar.lesson4.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.PathSelectors;

import java.util.Collections;

/**
 * Esta es la forma de configurar la firma de la aplicacion que se va a mostrar en swagger
 * http://localhost:8080/swagger-ui/
 */

@Configuration
public class SwaggerConfig {
	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiDetails())
				.select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build();
	}
	
	private ApiInfo apiDetails() {
		return new ApiInfo(
				"Api REST",
				"Api REST de laptops",
				"1.0",
				"https://opensource.org/licenses/MIT",
				new Contact(
						"Reymar",
						"www.reymar.com",
						"contacto@reymar.com"
						),
				"MIT",
				"https://opensource.org/licenses/MIT",
				Collections.emptyList()
		);
	}
}
