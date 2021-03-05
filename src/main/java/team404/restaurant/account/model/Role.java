package team404.restaurant.account.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ADMIN, RESTAURATEUR, EMPLOYEE, CLIENT;

    @Override
    public String getAuthority() {
        return null;
    }
}
