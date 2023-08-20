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
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

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

        if(authHeader == null || !authHeader.startsWith(Constant.bearer)) {
            filterChain.doFilter(request, response);
            return;
        }

        String jwtToken = authHeader.substring(Constant.bearer.length());
        Boolean isTokenValid = jwtService.isTokenValid(jwtToken);

        if(!isTokenValid) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase(),
                    "Token is not valid!");
            response.setContentType("application/json");
            response.getWriter().write(errorResponse.toString());
            return;
        }

        request.setAttribute("userId", jwtService.extractUserId(jwtToken));
        filterChain.doFilter(request, response);
    }
}
