package team404.restaurant.security.jwt.provider;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import team404.restaurant.security.jwt.authentication.JwtAuthentication;
import team404.restaurant.security.jwt.details.UserDetailsImpl;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

    @Value("${jwt.secret}")
    private String secret;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String token = authentication.getName();

        Claims claims;
        try {
            claims =  Jwts.parser().setSigningKey(secret).parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            throw new AuthenticationCredentialsNotFoundException("Bad token");
        }

        UserDetails userDetails = UserDetailsImpl.builder()
                .id(Long.parseLong(claims.get("sub", String.class)))
                .role(claims.get("role", String.class))
                .login(claims.get("login", String.class))
                .build();

        authentication.setAuthenticated(true);
        ((JwtAuthentication)authentication).setUserDetails(userDetails);
        return authentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtAuthentication.class.equals(authentication);
    }
}
