package com.example.Digital_Library_Management_System.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.Digital_Library_Management_System.Constants.Constants;
import com.example.Digital_Library_Management_System.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Bean
    public UserDetailsService userDetailsService(){
        return new UserService();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        return daoAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        auth -> auth
                                .requestMatchers("/admin/create").hasAuthority(Constants.CREATE_ADMIN_AUTHORITY)
                                .requestMatchers("/book/save").hasAuthority(Constants.CREATE_BOOK_AUTHORITY)
                                .requestMatchers("/book/search").hasAuthority(Constants.READ_BOOK_AUTHORITY)
                                .requestMatchers("/book/delete").hasAuthority(Constants.DELETE_BOOK_AUTHORITY)
                                .requestMatchers("/student/find").hasAuthority(Constants.STUDENT_INFO_AUTHORITY)
                                .requestMatchers("student/info").hasAuthority(Constants.STUDENT_SELF_INFO_AUTHORITY)
                                .requestMatchers("/transaction/initiate").hasAuthority(Constants.INITIATE_TRANSACTION_AUTHORITY)
                                .requestMatchers("/student/create").permitAll())

                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults())
                .build();
    }
    
}
