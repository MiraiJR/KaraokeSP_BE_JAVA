package com.miraijr.karaoke.shared.filter;

import com.miraijr.karaoke.application.jwt.JwtService;
import com.miraijr.karaoke.shared.constants.Constant;
import com.miraijr.karaoke.shared.exception.ErrorResponse;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private static List<String> skipFilterUrls = Arrays.asList("/auth/login");

    private final JwtService jwtService;

    @Autowired
    public JwtAuthenticationFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith(Constant.bearer)) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase(),
                   "Unauthorized");
            response.setContentType("application/json");
            response.getWriter().write(errorResponse.toString());
            return;
        }

        String jwtToken = authHeader.substring(Constant.bearer.length());

        try {
            jwtService.isTokenValid(jwtToken);
            request.setAttribute("userId", jwtService.extractUserId(jwtToken));
        } catch (Exception ex) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase(),
                    ex.getMessage());
            response.setContentType("application/json");
            response.getWriter().write(errorResponse.toString());
            return;
        }

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter (HttpServletRequest request) throws ServletException {
        return skipFilterUrls.stream().anyMatch(url -> new AntPathRequestMatcher(url).matches(request));
    }
}
