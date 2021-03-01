package team404.restaurant.domain;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ADMIN, RESTAURATEUR, WAITER, CLIENT;

    @Override
    public String getAuthority() {
        return null;
    }
}