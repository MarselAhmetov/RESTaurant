package team404.restaurant.general.security.jwt.provider;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import team404.restaurant.account.model.Account;
import team404.restaurant.general.repository.SimpleDao;
import team404.restaurant.general.security.jwt.authentication.JwtAuthentication;
import team404.restaurant.general.security.jwt.details.UserDetailsImpl;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationProvider implements AuthenticationProvider {

    @Value("${jwt.secret}")
    private String secret;

    private final SimpleDao simpleDao;

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

        UserDetails userDetails;
        Account account = simpleDao.findById(Account.class, Long.parseLong(claims.getSubject()));
        if (account != null) {
            userDetails = UserDetailsImpl.builder()
                    .account(account)
                    .build();
        } else {
            throw new IllegalArgumentException("Account is not found");
        }

        authentication.setAuthenticated(true);
        ((JwtAuthentication)authentication).setUserDetails(userDetails);
        return authentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtAuthentication.class.equals(authentication);
    }
}
