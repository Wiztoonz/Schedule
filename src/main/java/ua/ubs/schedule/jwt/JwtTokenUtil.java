package ua.ubs.schedule.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import ua.ubs.schedule.auth.UserPrincipal;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@PropertySource("classpath:security.properties")
@Component
public class JwtTokenUtil {

    @Value("${security.jwt.token.validity}")
    private long JWT_TOKEN_VALIDITY_TIME;
    @Value("${security.jwt.token.expiration}")
    private long JWT_TOKEN_VALIDITY_EXPIRATION_TIME;
    @Value("${security.jwt.private.token}")
    private String secret;

    private Key getSecretKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    private String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() +
                        JWT_TOKEN_VALIDITY_TIME * JWT_TOKEN_VALIDITY_EXPIRATION_TIME))
                .signWith(getSecretKey())
                .compact();
    }

    public String generateToken(UserPrincipal userPrincipal) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, String.valueOf(userPrincipal.getUsername()));
    }

    public Claims getAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(secret.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public  <T> T getClaimsFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public Date getExpirationDate(String token) {
        return getClaimsFromToken(token, Claims::getExpiration);
    }

    public Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDate(token);
        return expiration.before(new Date());
    }

    public String getUsernameFromToken(String token) {
        return getClaimsFromToken(token, Claims::getSubject);
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

}
