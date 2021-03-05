package team404.restaurant.restaurateur.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team404.restaurant.account.model.Account;
import team404.restaurant.general.config.mapping.GlobalMapper;
import team404.restaurant.general.repository.RestaurateurRepository;
import team404.restaurant.general.security.jwt.details.UserDetailsImpl;
import team404.restaurant.restaurateur.model.Restaurateur;
import team404.restaurant.restaurateur.dto.RestaurateurDto;
import team404.restaurant.general.repository.SimpleDao;

@Service
@RequiredArgsConstructor
public class RestaurateurServiceImpl implements RestaurateurService {

    private final SimpleDao simpleDao;
    private final RestaurateurRepository restaurateurRepository;
    private final GlobalMapper mapper;

    @Override
    @Transactional
    public void submit(RestaurateurDto restaurateurDto) {
        Restaurateur restaurateur;
        if (restaurateurDto.getId() != null) {
            restaurateur = simpleDao.findById(Restaurateur.class, restaurateurDto.getId());
        } else {
            restaurateur = new Restaurateur();
        }

        restaurateur.setFirstName(restaurateurDto.getFirstName());
        restaurateur.setLastName(restaurateurDto.getLastName());
        restaurateur.setFatherName(restaurateurDto.getFatherName());
        restaurateur.setPhoneNumber(restaurateurDto.getPhoneNumber());
        if (restaurateurDto.getAccount() != null) {
            Account account = simpleDao.findById(Account.class, restaurateurDto.getAccount().getId());
            if (account != null) {
                restaurateur.setAccount(account);
            }
        }

        simpleDao.saveOrUpdate(restaurateur);
    }

    @Override
    public RestaurateurDto getCurrentRestaurateur() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) auth.getDetails();
        RestaurateurDto restaurateurDto;
        Restaurateur restaurateur = restaurateurRepository.findRestaurateurByAccount_Id(userDetails.getAccount().getId());
        if (restaurateur !=  null) {
            restaurateurDto = mapper.map(restaurateur, RestaurateurDto.class);
        } else {
            throw new IllegalArgumentException("Restaurateur not found");
        }
        return restaurateurDto;
    }
}
