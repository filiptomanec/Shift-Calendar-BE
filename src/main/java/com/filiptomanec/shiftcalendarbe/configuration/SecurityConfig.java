package com.filiptomanec.shiftcalendarbe.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {

	private final JwtAuthenticationFilter jwtAuthFilter;
	private final AuthenticationProvider authenticationProvider;
	private final LogoutHandler logoutHandler;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(AbstractHttpConfigurer::disable)
		    .authorizeHttpRequests(request -> {
			    request.requestMatchers("/auth/**",
			                            "/swagger-ui/**",
			                            "/v3/api-docs/**",
			                            "/swagger-resources/**").permitAll();
			    request.anyRequest().authenticated();
		    })
		    .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		    .authenticationProvider(authenticationProvider)
		    .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
		    .logout(logout ->
				            logout.logoutUrl("/auth/logout")
				                  .addLogoutHandler(logoutHandler)
				                  .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
		    );
		return http.build();
	}
}