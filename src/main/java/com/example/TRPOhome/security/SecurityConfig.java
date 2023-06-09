//package com.example.TRPOhome.security;
//
//import com.example.TRPOhome.service.impl.EmployeeServiceImpl;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor
//public class SecurityConfig {
//
//    private final EmployeeServiceImpl employeeService;
//
//
//    @Bean
//    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        /*http.csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/api/employee").hasRole("ADMIN")
//                .antMatchers("/api/employee", "/error").permitAll()
//                .anyRequest().hasAnyRole("USER", "ADMIN")
//                .antMatchers("/home").authenticated()
//                .and()
//                .formLogin().loginPage("/login")
//                .loginProcessingUrl("/process_login")
//                .defaultSuccessUrl("/home", true)
//                .failureUrl("/home?")
//                .and()
//                .logout().logoutUrl("/logout").logoutSuccessUrl("/login");
//        return http.build();*/
//        http.csrf().disable().authorizeRequests().anyRequest().authenticated().and().formLogin();
//        return http.build();
//    }
//
//    @Bean
//    public DaoAuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//        authProvider.setUserDetailsService(employeeService);
//        authProvider.setPasswordEncoder(passwordEncoder());
//        return authProvider;
//    }
//
//    @Bean
//    PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//}
