package com.rsd_movieshop.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomUserDetailsService customUserDetailsService;

    public WebSecurityConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return this.customUserDetailsService;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());

        return authenticationProvider;
    }

    public UsernamePasswordAuthenticationFilter usernamePasswordAuthenticationFilter() throws Exception {
        JsonUsernamePasswordAuthenticationFilter authenticationFilter
                = new JsonUsernamePasswordAuthenticationFilter();
        authenticationFilter.setAuthenticationSuccessHandler(customAuthenticationSuccessHandler());
        authenticationFilter.setAuthenticationManager(authenticationManagerBean());

        return authenticationFilter;
    }

    @Bean
    public CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler(){
        return new CustomAuthenticationSuccessHandler();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

    	http.cors();
    	
    	http.headers().frameOptions().disable();
    	
    	http.authorizeRequests()
    			.antMatchers("/h2-console/**").permitAll();
    	
        http.authorizeRequests()
                .antMatchers("/api/**").permitAll();

        http.authorizeRequests()
                .antMatchers("/api/admin/**")
                .access("hasRole('ROLE_ADMIN')");

        http.authorizeRequests()
                .anyRequest()
                .authenticated();

        http.csrf()
                .disable();


        http.formLogin()
                .loginProcessingUrl("/login")
                .permitAll();

        http.logout()
                .logoutUrl("/logout")
                .permitAll();

        http.addFilterAt(
                usernamePasswordAuthenticationFilter(),
                UsernamePasswordAuthenticationFilter.class
        );

        http.exceptionHandling()
                .accessDeniedHandler(
                        (httpServletRequest, httpServletResponse, e) ->
                                httpServletResponse.sendError(
                                        HttpServletResponse.SC_FORBIDDEN
                                )
                )
                .authenticationEntryPoint(
                        new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)
                );

    }
}
