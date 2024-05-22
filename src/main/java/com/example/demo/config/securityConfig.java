package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo.jwt.JwtAuthenticationEntryPoint;
import com.example.demo.jwt.JwtAuthenticationFilter;

import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class securityConfig {

	    private JwtAuthenticationEntryPoint point;
	    private JwtAuthenticationFilter filter;
	    private  CustomUserDetailsService customUserDetailsService;

    @Bean
    DaoAuthenticationProvider authenticationProvider() {
      System.out.println(passwordEncoder().encode("dahit"));
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(customUserDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
       
        return daoAuthenticationProvider;
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity security) throws Exception {
        return security.csrf(csrf-> csrf.disable())
                .cors(cors-> cors.disable())
                .authorizeHttpRequests(auth->auth.requestMatchers("/authenticate","/**").permitAll()
                		.requestMatchers("/","/users/**").hasRole("admin").requestMatchers("/students", "/users/*")
                		.hasAnyRole("user")
                		.anyRequest().authenticated())
                        .exceptionHandling(ex ->ex.authenticationEntryPoint(point))
                        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                        .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
                        .build();

       
    }
//	@Bean
//	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//		http.csrf(csrf -> csrf.disable()).cors(cors -> cors.disable())
//				.authorizeHttpRequests((requests) -> requests.requestMatchers("/", "/users/*").permitAll()
//						.requestMatchers("/students/*").hasRole("admin").requestMatchers("/students", "/users/*")
//						.hasAnyRole("admin", "user").anyRequest().authenticated())
//				.formLogin((form) -> form.loginPage("/login").permitAll()).logout((logout) -> logout.permitAll());
//		return http.build();
//	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
