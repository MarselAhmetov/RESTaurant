package team404.restaurant.restaurateur.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team404.restaurant.account.model.Account;
import team404.restaurant.account.model.Role;
import team404.restaurant.employee.dto.EmployeeWithPasswordDto;
import team404.restaurant.employee.model.Employee;
import team404.restaurant.employee.model.EmployeeRole;
import team404.restaurant.general.config.mapping.GlobalMapper;
import team404.restaurant.general.repository.RestaurateurRepository;
import team404.restaurant.general.security.jwt.details.UserDetailsImpl;
import team404.restaurant.restaurant.model.Restaurant;
import team404.restaurant.restaurant.repository.RestaurantRepository;
import team404.restaurant.restaurateur.model.Restaurateur;
import team404.restaurant.restaurateur.dto.RestaurateurDto;
import team404.restaurant.general.repository.SimpleDao;

@Service
@RequiredArgsConstructor
public class RestaurateurServiceImpl implements RestaurateurService {

    private final SimpleDao simpleDao;
    private final RestaurateurRepository restaurateurRepository;
    private final RestaurantRepository restaurantRepository;
    private final GlobalMapper mapper;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void submit(RestaurateurDto restaurateurDto) {
        Restaurateur restaurateur;
        if (restaurateurDto.getId() != null) {
            restaurateur = simpleDao.findById(Restaurateur.class, restaurateurDto.getId());
            if (restaurateur == null) {
                throw new IllegalArgumentException("Restaurateur with such id does not exists");
            }
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
            throw new IllegalArgumentException("You are not restaurateur");
        }
        return restaurateurDto;
    }

    @Override
    @Transactional
    public void createEmployeeAccount(EmployeeWithPasswordDto employeeWithPasswordDto) {
        Account account = new Account();
        if (employeeWithPasswordDto.getAccount() != null) {
            account.setEmail(employeeWithPasswordDto.getAccount().getEmail());
            account.setPassword(passwordEncoder.encode(employeeWithPasswordDto.getPassword()));
            switch (employeeWithPasswordDto.getRole()) {
                case "WAITER":
                    account.setRole(Role.WAITER);
                    break;
                case "COOK":
                    account.setRole(Role.COOK);
                    break;
            }
        }
        simpleDao.save(account);

        Employee employee = new Employee();
        employee.setAccount(account);
        if (employeeWithPasswordDto.getFirstName() != null) {
            employee.setFirstName(employeeWithPasswordDto.getFirstName());
        }
        if (employeeWithPasswordDto.getLastName() != null) {
            employee.setLastName(employeeWithPasswordDto.getLastName());
        }
        if (employeeWithPasswordDto.getFatherName() != null) {
            employee.setFatherName(employeeWithPasswordDto.getFatherName());
        }
        if (employeeWithPasswordDto.getPhoneNumber() != null) {
            employee.setPhoneNumber(employeeWithPasswordDto.getPhoneNumber());
        }
        if (employeeWithPasswordDto.getRole() != null) {
            employee.setRole(EmployeeRole.valueOf(employeeWithPasswordDto.getRole()));
        }
        if (employeeWithPasswordDto.getPassword() != null) {
            employee.setPassword(employeeWithPasswordDto.getPassword());
        }
        if (employeeWithPasswordDto.getRestaurant() != null) {
            Restaurant restaurant = restaurantRepository.findById(employeeWithPasswordDto.getRestaurant().getId()).orElseThrow();
            employee.setRestaurant(restaurant);
        }
        simpleDao.save(employee);
    }

}
