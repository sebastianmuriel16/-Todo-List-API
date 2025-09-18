package practica_spring_boot.Todo_List_API.securiity;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

@Service
public class JwtService {

    private final SecretKey key;

    public JwtService(@Value("${jwt.secret}") String secret_key){
        this.key = Keys.hmacShaKeyFor(secret_key.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(UserTodoDetails userTodoDetails) {
        return Jwts.builder()
                .claims()
                .subject(userTodoDetails.getUsername())
                .add("id",userTodoDetails.getId())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 10800000))
                .and()
                .signWith(key,Jwts.SIG.HS256)
                .compact();
    }

    public UserTodoDetails validationToken(String token){
        Claims claims = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();

        Long id = claims.get("id",Long.class);
        String email = claims.getSubject();

        return new UserTodoDetails(id,email,null,List.of());

    }

}
