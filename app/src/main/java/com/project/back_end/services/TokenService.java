import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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
                .signWith(SignatureAlgorithm.HS256, secretKey) // Sign with HMAC using the secret key
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
                .setSigningKey(secretKey) // Use the same secret key to parse
                .parseClaimsJws(token)
                .getBody(); // Return the claims part of the JWT
    }

    // Method to check if the token is expired
    public boolean isTokenExpired(String token) {
        Claims claims = getClaims(token);
        return claims.getExpiration().before(new Date()); // Check if the token's expiration date is in the past
    }
}
