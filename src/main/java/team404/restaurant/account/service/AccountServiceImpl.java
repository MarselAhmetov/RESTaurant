package team404.restaurant.account.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team404.restaurant.account.dto.AccountDto;
import team404.restaurant.account.dto.AccountSafeDto;
import team404.restaurant.account.model.Account;
import team404.restaurant.account.model.Role;
import team404.restaurant.general.config.mapping.GlobalMapper;
import team404.restaurant.general.repository.SimpleDao;
import team404.restaurant.general.security.jwt.details.UserDetailsImpl;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final SimpleDao simpleDao;
    private final GlobalMapper mapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void submit(AccountDto accountDto) {
        Account account;
        if (accountDto.getId() != null) {
            account = simpleDao.findById(Account.class, accountDto.getId());
        } else {
            account = new Account();
        }

        account.setEmail(accountDto.getEmail());
        account.setPassword(passwordEncoder.encode(accountDto.getPassword()));
        if (accountDto.getRole() != null) {
            account.setRole(Role.valueOf(accountDto.getRole()));
        }

        simpleDao.saveOrUpdate(account);
    }

    @Override
    public AccountSafeDto getCurrentAccountInformation() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) auth.getDetails();
        AccountSafeDto accountSafeDto;
        Account account = simpleDao.findById(Account.class, userDetails.getAccount().getId());
        if (account !=  null) {
            accountSafeDto = mapper.map(account, AccountSafeDto.class);
        } else {
            throw new IllegalArgumentException("Account not found");
        }
        return accountSafeDto;
    }
}
