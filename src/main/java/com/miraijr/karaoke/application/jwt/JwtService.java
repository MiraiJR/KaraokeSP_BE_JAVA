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
    private final Integer ACCESS_TOKEN_EXPIRED = 1000 * 60 * 60 * 1;
    private final Integer REFRESH_TOKEN_EXPIRED = 1000 * 60 * 60 * 24 * 7;
    private final UserService userService;

    @Autowired
    public JwtService(UserService userService) {
        this.userService = userService;
    }

    public String extractUserId(String token, String type) throws Exception {
        return extractClaim(token, Claims::getSubject, type);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver, String type) throws Exception {
        Claims claims = extractAllClaims(token, type);
        return claimsResolver.apply(claims);
    }

    public String generateToken(
            Integer userId,
            String type
    ) {
        return Jwts.builder()
                .setSubject(String.valueOf(userId))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiredOfTypeToken(type)))
                .signWith(SignatureAlgorithm.HS256, checkTypeToken(type))
                .compact();
    }

    private String checkTypeToken(String type) {
        return type.equals(Constant.ACCESS_TOKEN) ? ACCESS_SECRET_KEY : REFRESH_SECRET_KEY;
    }

    private Integer expiredOfTypeToken(String type) {
        return type.equals(Constant.ACCESS_TOKEN) ? ACCESS_TOKEN_EXPIRED : REFRESH_TOKEN_EXPIRED;
    }

    private Claims extractAllClaims(String token, String type) throws Exception {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(checkTypeToken(type))
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

    public boolean isTokenValid(String token, String type) throws Exception {
        String userId = extractUserId(token, type);

        UserEntity user = userService.findUserById(Integer.valueOf(userId));

        if (type.equals(Constant.REFRESH_TOKEN)) {
            return user != null && user.getRefreshToken().equals(token) && !isTokenExpired(token, type);
        }

        return user != null && user.getAccesstToken().equals(token) && !isTokenExpired(token, type);
    }

    private boolean isTokenExpired(String token, String type) throws Exception {
        return extractExpiration(token, type).before(new Date());
    }

    private Date extractExpiration(String token, String type) throws Exception {
        return extractClaim(token, Claims::getExpiration, type);
    }
}
