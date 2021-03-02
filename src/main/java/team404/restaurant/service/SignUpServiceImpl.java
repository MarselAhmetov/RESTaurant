package team404.restaurant.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import team404.restaurant.domain.Account;
import team404.restaurant.domain.Role;
import team404.restaurant.dto.AccountDto;
import team404.restaurant.repository.AccountRepository;

@Service
@RequiredArgsConstructor
public class SignUpServiceImpl implements SignUpService{

    private final PasswordEncoder passwordEncoder;
    private final AccountRepository accountRepository;

    @Override
    public void signUp(AccountDto accountDto) {
        Account account = Account.builder()
                .email(accountDto.getEmail())
                .password(passwordEncoder.encode(accountDto.getPassword()))
                .role(Role.valueOf(accountDto.getRole()))
                .build();
        accountRepository.save(account);
    }
}
