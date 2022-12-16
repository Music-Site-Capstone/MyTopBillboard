package com.mytopbillboard.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
public class SecurityConfiguration {

// When ever you create a new mapping it needs to be added to the Security Bean. "Authenticated"
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .antMatchers("/login", "/", "/register", "/landingPage").permitAll()
                .antMatchers("/profile", "/homepage").authenticated()
                // sets the url success page
                .and().formLogin().loginPage("/login").defaultSuccessUrl("/homepage")
//                .and().logout().logoutSuccessUrl("/splash?logout")
                .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/landingPage").deleteCookies("JSESSIONID").invalidateHttpSession(true)
                .and().httpBasic();
        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }



    // This is if you don't want a password encoder

//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return NoOpPasswordEncoder.getInstance();
//    }



}//End of SecurityConfiguration Class
