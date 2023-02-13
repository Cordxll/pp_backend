package ProductivePeople.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


//This class is responsible for setting or getting a token from a user, identifying the user's authorities, and setting the specifications of a token
@Component
public class JwtConverter {

    private static final String SECRET_KEY = "bPeShVmYp3sasdf6v9yaB1234fEBHaMcQasdffTjWnZr";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    //extracts a single claim
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    //extracts claims associated with a user by using their token
    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    //checks that the token a user is utilizing has not yet expired
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    //checks if the token has expired
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    //extract the expiration time from the token
    private Date extractExpiration(String token) {
        return extractClaim(token,Claims::getExpiration);
    }


    //generates a token for a user by using their claims and their userdetails
    public String generateToken(Map<String, Object> extractClaims, UserDetails userDetails) {
            return Jwts.builder()
                    .setClaims(extractClaims)
                    .setSubject(userDetails.getUsername())
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date((System.currentTimeMillis() + 1000 * 60 * 24)))
                    .signWith((getSignInKey()), SignatureAlgorithm.HS256)
                    .compact();
    }

    //generates a token for a user solely off their userdetails
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    //a hard-coded key (taken from a key generator) that token generation and extract claims methods
    // use to ensure the application is the one generating the tokens and extracting claims
    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
