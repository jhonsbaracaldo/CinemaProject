package ProyectoIntegradorCine.aplication.services.serviceJwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;

@Service
public record JwtServices(
@Value("${application.security.jwt.secret-key}")
String secretkey,
@Value("${application.security.jwt.expiration}")
  Long jwtExpiration) {

    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    private String generateToken(HashMap<String, Object> extraClaims, UserDetails userDetails) {
        return buildToken(extraClaims, userDetails, jwtExpiration);
    }

    private String buildToken(
            HashMap<String, Object> extraClaims,
            UserDetails userDetails,
            Long expiration) {
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(Date.from(
                        LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()
                ))
                .setExpiration(Date.from(
                        LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().plusMillis(expiration)
                ))
                .signWith(getSignnkey(), SignatureAlgorithm.HS256)
                .compact();

    }


    public String  extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims,T> claimsResolver){
        final Claims claims = extracAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extracAllClaims(String token) {
        return Jwts.parserBuilder().
                setSigningKey(getSignnkey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    private Key getSignnkey() {

        byte[] keyBytes = Decoders.BASE64.decode(secretkey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUserName(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).isBefore(LocalDateTime.now());
    }

    private LocalDateTime extractExpiration(String token) {
        Date date = extractClaim(token, Claims::getExpiration);
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }



}
