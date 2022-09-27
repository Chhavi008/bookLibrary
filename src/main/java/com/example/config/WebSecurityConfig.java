package com.example.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends  WebSecurityConfigurerAdapter {

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("chhavi")
                .password(passwordEncoder().encode("admin"))
                .roles("admin","user")
                .and()
                .withUser("user")
                .password(passwordEncoder().encode("user"))
                .roles("user");
    }

    // Configuring the api
    // according to the roles.
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.cors().and().csrf().disable().httpBasic()
                .and().authorizeRequests().antMatchers("/getBook").access("hasRole('admin') or hasRole('user')")
                .antMatchers("/getAllBooksSorted").access("hasRole('admin')or hasRole('user')")
                .antMatchers("/addBook").access("hasRole('admin')or hasRole('user')")
                .antMatchers("/updateBookName").access("hasRole('admin') or hasRole('user')")
                .antMatchers("/deleteBook").access("hasRole('admin')")
                .antMatchers("/updateBookAuthors").access("hasRole('admin')");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
