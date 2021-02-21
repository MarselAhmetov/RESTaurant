package team404.restaurant.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import team404.restaurant.model.Account;
import team404.restaurant.model.Role;
import team404.restaurant.model.dto.AccountDto;
import team404.restaurant.repository.AccountRepository;

@Service
@AllArgsConstructor
public class SignUpServiceImpl implements SignUpService{

    private final PasswordEncoder passwordEncoder;
    private final AccountRepository accountRepository;

    @Override
    public void signUp(AccountDto accountDto) {
        Account account = Account.builder()
                .login(accountDto.getLogin())
                .password(passwordEncoder.encode(accountDto.getPassword()))
                .role(Role.valueOf(accountDto.getRole()))
                .build();
        accountRepository.save(account);
    }
}
