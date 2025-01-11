package com.example.quanlybenhvien.Security;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizationRequestResolver;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestResolver;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.security.web.SecurityFilterChain;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Configuration
public class SecurityConfig {
    private final ClientRegistrationRepository clientRegistrationRepository;

    public SecurityConfig(ClientRegistrationRepository clientRegistrationRepository) {
        this.clientRegistrationRepository = clientRegistrationRepository;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/dangky","/dangky/**", "/login", "/css/**", "/js/**", "/images/**","/image/**", "/index").permitAll()
                        .anyRequest().authenticated())
                .oauth2Login(oauth2 -> oauth2
                        .loginPage("/login")
                        .defaultSuccessUrl("/loginSuccess", true)
                        .authorizationEndpoint(auth -> auth
                                .authorizationRequestResolver(customAuthorizationRequestResolver())))
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessHandler((request, response, authentication) -> {
                            response.sendRedirect("https://accounts.google.com/logout?continue=" +
                                    URLEncoder.encode("http://localhost:8080/login", StandardCharsets.UTF_8));
                            response.sendRedirect("https://accounts.facebook.com/logout?continue=" +
                                    URLEncoder.encode("http://localhost:8080/login", StandardCharsets.UTF_8));
                        })
                        .invalidateHttpSession(true)
                        .clearAuthentication(true));

        return http.build();
    }

    @Bean
    public OAuth2AuthorizationRequestResolver customAuthorizationRequestResolver() {
        DefaultOAuth2AuthorizationRequestResolver defaultResolver = new DefaultOAuth2AuthorizationRequestResolver(
                clientRegistrationRepository, "/oauth2/authorization");

        return new OAuth2AuthorizationRequestResolver() {
            @Override
            public OAuth2AuthorizationRequest resolve(HttpServletRequest request) {
                OAuth2AuthorizationRequest originalRequest = defaultResolver.resolve(request);
                if (originalRequest == null)
                    return null;

                // Thêm tham số `prompt=select_account`
                return OAuth2AuthorizationRequest.from(originalRequest)
                        .additionalParameters(params -> params.put("prompt", "select_account"))
                        .build();
            }

            @Override
            public OAuth2AuthorizationRequest resolve(HttpServletRequest request, String clientRegistrationId) {
                OAuth2AuthorizationRequest originalRequest = defaultResolver.resolve(request, clientRegistrationId);
                if (originalRequest == null)
                    return null;

                // Thêm tham số `prompt=select_account`
                return OAuth2AuthorizationRequest.from(originalRequest)
                        .additionalParameters(params -> params.put("prompt", "select_account"))
                        .build();
            }
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
