import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;

@Service
public class TokenService {

    @Value("${jwt.secret}")
    private String secretKey; // Secret key to sign JWT

    @Value("${jwt.expiration}")
    private long expirationTime; // Token expiration time in milliseconds

    // Method to generate JWT for a user
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username) // Set the subject (username)
                .setIssuedAt(new Date()) // Set the issued at date
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime)) // Set expiration time
                .signWith(getSigningKey(), SignatureAlgorithm.HS256) // Sign with HMAC using the secret key
                .compact();
    }

    // Method to validate a JWT token
    public boolean validateToken(String token) {
        try {
            // Extract claims and check the token validity
            Claims claims = getClaims(token);
            return !claims.getExpiration().before(new Date()); // Return true if token is not expired
        } catch (Exception e) {
            return false; // Invalid token or other exceptions
        }
    }

    // Method to extract the username from a token
    public String extractUsername(String token) {
        Claims claims = getClaims(token);
        return claims.getSubject(); // The subject is the username
    }

    // Helper method to get claims from the token
    private Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(getSigningKey()) // Use the signing key
                .parseClaimsJws(token)
                .getBody(); // Return the claims part of the JWT
    }

    // Method to check if the token is expired
    public boolean isTokenExpired(String token) {
        Claims claims = getClaims(token);
        return claims.getExpiration().before(new Date()); // Check if the token's expiration date is in the past
    }

    // Private method to retrieve or generate a signing key
    private Key getSigningKey() {
        // Using the secret key configured in properties to generate a SecretKeySpec
        return new SecretKeySpec(secretKey.getBytes(), SignatureAlgorithm.HS256.getJcaName());
    }
}
