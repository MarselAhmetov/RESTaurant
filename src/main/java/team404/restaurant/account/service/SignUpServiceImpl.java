package team404.restaurant.account.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team404.restaurant.account.model.Account;
import team404.restaurant.account.model.Role;
import team404.restaurant.client.model.Client;
import team404.restaurant.account.dto.AccountDto;
import team404.restaurant.general.repository.AccountRepository;
import team404.restaurant.general.repository.SimpleDao;
import team404.restaurant.restaurateur.model.Restaurateur;

@Service
@RequiredArgsConstructor
public class SignUpServiceImpl implements SignUpService {

    private final PasswordEncoder passwordEncoder;
    private final AccountRepository accountRepository;
    private final SimpleDao simpleDao;

    @Override
    @Transactional
    public void signUp(AccountDto accountDto) {
        Account account = Account.builder()
                .email(accountDto.getEmail())
                .password(passwordEncoder.encode(accountDto.getPassword()))
                .role(Role.valueOf(accountDto.getRole()))
                .build();
        accountRepository.save(account);

        switch (Role.valueOf(accountDto.getRole())) {
            case CLIENT:
                Client client = new Client();
                client.setAccount(account);
                simpleDao.save(client);
                break;
            case RESTAURATEUR:
                Restaurateur restaurateur = new Restaurateur();
                restaurateur.setAccount(account);
                simpleDao.save(restaurateur);
                break;
        }
    }
}
