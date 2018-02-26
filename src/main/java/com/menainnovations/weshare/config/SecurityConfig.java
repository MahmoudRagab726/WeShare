package com.menainnovations.weshare.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    // Authentication : User --> Roles

    // Authorization : Role -> Access
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.
                httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/api/**").hasRole("USER")
                .antMatchers("/users").hasRole("ADMIN")
                .and()
                .formLogin();
    }


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user").password("pass123")
                .authorities("USER").roles("USER")
                .and()
                .withUser("mahmoud").password("mahmoud123")
                .authorities("ADMIN").roles("ADMIN","USER");;
    }



}