package team404.restaurant.account.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ADMIN, RESTAURATEUR, WAITER, COOK, CLIENT;

    @Override
    public String getAuthority() {
        return null;
    }
}
