package com.example.ecommerce_backend.configuration;

import com.example.ecommerce_backend.service.implementations.CustomUserDetailsService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@ConditionalOnProperty(value = "auth-disable", havingValue = "false", matchIfMissing = true)
@EnableWebSecurity()
@RequiredArgsConstructor
public class SecurityConfig {

  private final CustomUserDetailsService customUserDetailsService;

  @Value("${cors-allowed-origins}")
  private final String corsAllowedOrigins;

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig)
      throws Exception {
    return authConfig.getAuthenticationManager();
  }

  @Bean
  public DaoAuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

    authProvider.setUserDetailsService(customUserDetailsService);
    authProvider.setPasswordEncoder(passwordEncoder());

    return authProvider;
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(List.of(corsAllowedOrigins));
    configuration.setAllowedMethods(List.of("*"));
    configuration.setAllowedHeaders(List.of("*"));

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    // Enable and configure JWT security
    http.oauth2ResourceServer(rs -> rs.jwt(Customizer.withDefaults()));

    // Enable and configure CORS
    http.cors(cors -> cors.configurationSource(corsConfigurationSource()));

    // State-less session (state in access token only)
    http.sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

    // Disable CSRF because of state-less session-management
    http.csrf(AbstractHttpConfigurer::disable);

    // @formatter:off
    http.authorizeHttpRequests(
        authorize ->
            authorize
                .requestMatchers(HttpMethod.POST, "/api/login")
                .permitAll()
                .requestMatchers(HttpMethod.POST, "/api/users")
                .permitAll()
                .requestMatchers(HttpMethod.GET, "/api/products/**")
                .permitAll()
                .requestMatchers(HttpMethod.GET, "/api/categories/**")
                .permitAll()
                .requestMatchers(HttpMethod.POST, "/api/orders/*/confirm")
                .permitAll()
                .requestMatchers(HttpMethod.POST, "/api/orders/with_email")
                .permitAll()
                .requestMatchers(HttpMethod.POST, "/api/orders/with_user")
                .permitAll()
                .requestMatchers("/error/**")
                .permitAll()
                .requestMatchers("/swagger-ui/**")
                .permitAll()
                .requestMatchers("/swagger-ui.html")
                .permitAll()
                .requestMatchers("/v3/api-docs")
                .permitAll()
                .requestMatchers("/v3/api-docs.yaml")
                .permitAll()
                .requestMatchers("v3/api-docs/**")
                .permitAll()
                .anyRequest()
                .authenticated());
    // @formatter:on

    return http.build();
  }
}
