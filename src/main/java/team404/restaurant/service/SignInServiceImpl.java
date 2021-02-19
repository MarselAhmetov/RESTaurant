package team404.restaurant.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import team404.restaurant.model.Account;
import team404.restaurant.model.dto.SignInDto;
import team404.restaurant.model.dto.TokenDto;
import team404.restaurant.repository.UserRepository;

import java.util.Optional;

@Service
public class SignInServiceImpl implements SignInService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${jwt.secret}")
    private String secret;

    @Override
    public TokenDto signIn(SignInDto signInData) {
        Optional<Account> userOptional = userRepository.findUserByLogin(signInData.getLogin());

        if (userOptional.isPresent()) {
            Account account = userOptional.get();
            if (passwordEncoder.matches(signInData.getPassword(), account.getPassword())) {
                String token = Jwts.builder()
                        .setSubject(account.getId().toString())
                        .claim("login", account.getLogin())
                        .claim("role", account.getRole().name())
                        .signWith(SignatureAlgorithm.HS256, secret)
                        .compact();
                return new TokenDto(token);
            } else throw new AccessDeniedException("Wrong login/password");
        } else throw new AccessDeniedException("User not found");
    }

}
