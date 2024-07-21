package com.kabera.sobersteps.config;

import com.kabera.sobersteps.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfiguration {

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(UserService userService) {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(configurer ->
                                configurer
                                        .requestMatchers(HttpMethod.GET,"/create-account/**").permitAll()
                                        .requestMatchers(HttpMethod.POST,"/create-account/**").permitAll()
                                        .requestMatchers(HttpMethod.GET, "/sober-steps/login").permitAll()
                                        .requestMatchers(HttpMethod.GET, "/").permitAll()
                                        .requestMatchers(HttpMethod.GET, "/survey").permitAll()
                                        .requestMatchers(HttpMethod.POST, "/submit-survey").permitAll()
                                        .requestMatchers(HttpMethod.GET, "/survey-intro").permitAll()
                                        .requestMatchers(HttpMethod.GET, "/standard-drinks").permitAll()
                                        .requestMatchers(HttpMethod.POST, "/saveStandardDrinkData").permitAll()
                                        .requestMatchers("/generate-plan").permitAll()
                                        .requestMatchers("/img/**").permitAll()
                                        .requestMatchers("/js/**").permitAll()
                                        .requestMatchers("/css/**").permitAll()
                                        .requestMatchers("/hp/**").hasRole("HP")
                                        .requestMatchers("/moh/**").hasRole("MOH")
                                        .requestMatchers("/create-survey/**").hasRole("HP")
                                        .requestMatchers("/create-survey/**").hasRole("MOH")
                                        .anyRequest().authenticated()
//                       .anyRequest().permitAll()
                )
                .formLogin(formLogin ->
                        formLogin
                                .loginPage("/sober-steps/login")
                                .loginProcessingUrl("/login")
                                .successHandler(customAuthSuccessHandler())
                                .permitAll()
                )
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/")
                        .permitAll()
                );

        http.httpBasic(Customizer.withDefaults());
        http.csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }

    public AuthenticationSuccessHandler customAuthSuccessHandler(){
        return new CustomAuthenticationSuccessHandler();
    }
}