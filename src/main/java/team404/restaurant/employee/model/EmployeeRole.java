package team404.restaurant.employee.model;

import org.springframework.security.core.GrantedAuthority;

public enum EmployeeRole implements GrantedAuthority {
    WAITER, COOK;

    @Override
    public String getAuthority() {
        return null;
    }
}
