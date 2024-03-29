package team404.restaurant.employee.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team404.restaurant.account.model.Account;
import team404.restaurant.employee.dto.EmployeeDto;
import team404.restaurant.employee.dto.EmployeeWithPasswordDto;
import team404.restaurant.employee.model.Employee;
import team404.restaurant.employee.repository.EmployeeRepository;
import team404.restaurant.general.config.mapping.GlobalMapper;
import team404.restaurant.general.security.jwt.details.UserDetailsImpl;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final GlobalMapper mapper;

    @Override
    public List<EmployeeDto> getEmployeesByRestaurant(UUID restaurantId) {
        List<Employee> employees = employeeRepository.getAllByRestaurant_Id(restaurantId);
        return mapper.mapAsList(employees, EmployeeDto.class);
    }

    @Override
    public EmployeeWithPasswordDto getEmployeeWithAccount(UUID employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow();
        return mapper.map(employee, EmployeeWithPasswordDto.class);
    }

    @Override
    public EmployeeDto getEmployee(UUID employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow();
        return mapper.map(employee, EmployeeDto.class);
    }

    @Override
    public Employee getEmployeeByAccount(Account account) {
        return employeeRepository.getEmployeeByAccount_Id(account.getId());
    }

    @Override
    @Transactional
    public EmployeeDto getCurrentEmployee() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) auth.getDetails();
        Employee employee = employeeRepository.getEmployeeByAccount_Id(userDetails.getAccount().getId());
        return mapper.map(employee, EmployeeDto.class);
    }
}
