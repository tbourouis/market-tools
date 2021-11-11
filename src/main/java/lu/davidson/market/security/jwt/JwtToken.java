package lu.davidson.market.security.jwt;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtToken implements Authentication {
    private final Claims claims;
    private boolean authenticated = true;

    public JwtToken(Claims claims) {
        this.claims = claims;
        setAuthenticated(true);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return claims;
    }

    @Override
    public Object getPrincipal() {
        return claims.getSubject();
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean authenticated) throws IllegalArgumentException {
        this.authenticated = authenticated;
    }

    @Override
    public String getName() {
        return claims.getSubject();
    }
}
