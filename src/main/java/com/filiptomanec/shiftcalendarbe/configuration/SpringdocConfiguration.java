package com.filiptomanec.shiftcalendarbe.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class SpringdocConfiguration {

	@Bean
	public OpenAPI openAPI() {
		final String securitySchemeName = "apiKey";

		return new OpenAPI()
				.info(new Info()
						      .title("Shift Calendar REST API")
						      .description("Spring Boot REST API of the Shift Calendar application.")
						      .version("1.0"))
				.components(new Components()
						            .addSecuritySchemes(securitySchemeName, new SecurityScheme()
								            .type(SecurityScheme.Type.APIKEY)
								            .description("Bearer token access")
								            .in(SecurityScheme.In.HEADER)
								            .name("Authorization")))
				.addSecurityItem(new SecurityRequirement()
						                 .addList(securitySchemeName));
	}
}
