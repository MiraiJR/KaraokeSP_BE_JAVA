package com.miraijr.karaoke.application.jwt;

import com.miraijr.karaoke.application.user.UserEntity;
import com.miraijr.karaoke.application.user.UserService;
import com.miraijr.karaoke.shared.constants.Constant;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {
    @Value("${jwt.access_key}")
    private String ACCESS_SECRET_KEY;
    @Value(("${jwt.refresh_key}"))
    private String REFRESH_SECRET_KEY;
    private final Integer ONE_DAY = 1000 * 60 * 60 * 24;
    private final UserService userService;

    @Autowired
    public JwtService(UserService userService) {
        this.userService = userService;
    }

    public String extractUserId(String token) throws Exception {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) throws Exception {
        Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(
            Integer userId,
            String type
    ) {
        return Jwts.builder()
                .setSubject(String.valueOf(userId))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + ONE_DAY))
                .signWith(SignatureAlgorithm.HS256, type.equals(Constant.ACCESS_TOKEN) ? ACCESS_SECRET_KEY : REFRESH_SECRET_KEY)
                .compact();
    }

    private Claims extractAllClaims(String token) throws Exception {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(ACCESS_SECRET_KEY)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (MalformedJwtException ex) {
            throw new Exception("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            throw new Exception("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            throw new Exception("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            throw new Exception("JWT claims string is empty");
        }
    }

    public boolean isTokenValid(String token) throws Exception {
        String userId = extractUserId(token);

        UserEntity user = userService.findUserById(Integer.valueOf(userId));

        return user != null && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) throws Exception {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) throws Exception {
        return extractClaim(token, Claims::getExpiration);
    }
}
