package com.example.ecommerce_backend.filter;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.example.ecommerce_backend.exception.InvalidCredentialException;
import com.example.ecommerce_backend.model.CustomUserDetails;
import com.example.ecommerce_backend.service.implementations.CustomUserDetailsService;
import com.example.ecommerce_backend.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class JwtAuthorizationFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService customUserDetailsService;
    private final HandlerExceptionResolver handlerExceptionResolver;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String jwt = checkTokenFormatThenReturnToken(request);
            checkAuthenticationFromToken(jwt);
        } catch (InvalidCredentialException invalidCredentialException) {
            handlerExceptionResolver.resolveException(request, response, null, invalidCredentialException);
            return;
        }
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(
            HttpServletRequest request) {
        String path = request.getServletPath();
        if (path.equals("/api/login")) {
            return true;
        } else if (path.equals("/api/users") && request.getMethod().equals("POST")) {
            return true;
        } else if (path.equals("/api/products") && request.getMethod().equals("GET")) {
            return true;
        }  else if (path.equals("/swagger-ui/index.html")) {
            return true;
        } else {
            return false;
        }
    }

    private String checkTokenFormatThenReturnToken(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");

        if (headerAuth != null && headerAuth.startsWith("Bearer ")) {
            String jwt = headerAuth.substring(7);

            try {
                if (jwtUtil.validateJwtToken(jwt)) {
                    return jwt;
                }
            } catch (JWTDecodeException jwtDecodeException) {
                throw new InvalidCredentialException("wrong token format");
            }

        }

        throw new InvalidCredentialException("wrong token format");
    }

    private void checkAuthenticationFromToken(String jwt) {
        String username = jwtUtil.getUserNameFromJwtToken(jwt);

        if (username != null && !username.isEmpty()) {
            try {
                CustomUserDetails customUserDetails = customUserDetailsService.loadUserByUsername(username);
                Authentication authentication = new UsernamePasswordAuthenticationToken(
                        customUserDetails,
                        null,
                        customUserDetails.getAuthorities()
                );
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (UsernameNotFoundException usernameNotFoundException) {
                throw new InvalidCredentialException(usernameNotFoundException.getMessage());
            }
        } else {
            throw new InvalidCredentialException("name is empty");
        }
    }
}
