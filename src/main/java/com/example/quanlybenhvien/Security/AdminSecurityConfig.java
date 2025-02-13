package com.example.quanlybenhvien.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@Order(1)
public class AdminSecurityConfig {
    private final PasswordEncoder passwordEncoder;

    public AdminSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public SecurityFilterChain adminSecurityFilterChain(HttpSecurity http) throws Exception {
        http
            .securityMatcher("/nguoidung/**","/api/**","/js/**") // Chỉ áp dụng cho "/admin/**"
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/nguoidung/**","/api/**").hasRole("QUANLY") // Chỉ ADMIN mới vào được
                .anyRequest().authenticated())
            .formLogin(form -> form
                .loginPage("/nguoidung/login") // Trang đăng nhập riêng cho admin
                .loginProcessingUrl("/nguoidung/login")
                .defaultSuccessUrl("/nguoidung/trangchu", true) // Sau khi đăng nhập thành công chuyển vào "/admin/home"
                .failureUrl("/nguoidung/login?error=true")
                .permitAll())
            .logout(logout -> logout
                .logoutUrl("/nguoidung/logout") // Đăng xuất dành riêng cho admin
                .logoutSuccessUrl("/nguoidung/login?logout=true")
                .invalidateHttpSession(true)
                .clearAuthentication(true))
            .csrf(csrf -> csrf.disable());
    
        return http.build();
    }
  
}
