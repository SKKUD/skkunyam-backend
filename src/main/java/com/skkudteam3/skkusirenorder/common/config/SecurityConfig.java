package com.skkudteam3.skkusirenorder.common.config;

import com.skkudteam3.skkusirenorder.common.oauth.HttpCookieOAuth2AuthorizationRequestRepository;
import com.skkudteam3.skkusirenorder.common.oauth.handler.OAuth2AuthenticationFailureHandler;
import com.skkudteam3.skkusirenorder.common.oauth.handler.OAuth2AuthenticationSuccessHandler;
import com.skkudteam3.skkusirenorder.common.oauth.service.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;


@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final CustomOAuth2UserService customOAuth2UserService;
    private final OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;
    private final OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler;
    private final HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        //csrf disabled
        http.csrf(AbstractHttpConfigurer::disable)
                .headers(headersConfigurer -> headersConfigurer.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable)) // For H2 DB
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers(antMatcher("/api/admin/**")).hasRole("ADMIN")
                        .requestMatchers(antMatcher("/api/user/**")).hasRole("USER")
                        .requestMatchers(antMatcher("/h2-console/**")).permitAll()
                        .anyRequest().authenticated()
                )
                .sessionManagement(sessions -> sessions.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .oauth2Login(configure ->
                        configure.authorizationEndpoint(config -> config.authorizationRequestRepository(httpCookieOAuth2AuthorizationRequestRepository))
                                .userInfoEndpoint(config -> config.userService(customOAuth2UserService))
                                .successHandler(oAuth2AuthenticationSuccessHandler)
                                .failureHandler(oAuth2AuthenticationFailureHandler)
                );

        return http.build();
    }
}
