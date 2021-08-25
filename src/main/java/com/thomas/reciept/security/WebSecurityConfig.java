
package com.thomas.reciept.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
            .csrf().disable();
        http   
            .authorizeRequests()
            .antMatchers("/signup","/").permitAll()
            .and()
            .authorizeRequests()
            .antMatchers("/home/**").authenticated()
            .and()
            .authorizeRequests()
            .antMatchers("/rest/api/**").authenticated()
            .and()
            .formLogin() // Cho phép người dùng xác thực bằng form login
                .loginPage("/login")
                .permitAll()
                .defaultSuccessUrl("/home",true)
                .usernameParameter("username")
                .passwordParameter("password")

            .and()
            .logout() // Cho phép logout
            .logoutUrl("/logout");

    }
    
}
