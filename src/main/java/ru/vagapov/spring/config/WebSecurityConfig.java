package ru.vagapov.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import ru.vagapov.spring.service.Impl.UserServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    private final SuccessUserHandler successUserHandler;
    private final UserServiceImpl userService;
    private final PasswordEncoder passwordEncoder;

    public WebSecurityConfig(SuccessUserHandler successUserHandler, UserServiceImpl userService, PasswordEncoder passwordEncoder) {
        this.successUserHandler = successUserHandler;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public SecurityFilterChain filterChainForAdmin(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers( "/admin/**")
                        .hasRole("ADMIN")
                        .requestMatchers( "/user/**").permitAll()
                        .anyRequest().hasAnyRole("USER", "ADMIN")

                )
                .formLogin((form) -> form
                        .successHandler(successUserHandler)
                        .permitAll()
                )
                .logout(LogoutConfigurer::permitAll
                );
        return http.build();
    }


    // аутентификация UserDetailService
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setPasswordEncoder(passwordEncoder);
        authProvider.setUserDetailsService(userService);
        return authProvider;
    }
}
