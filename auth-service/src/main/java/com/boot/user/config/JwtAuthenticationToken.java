package com.boot.user.config;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter
@EqualsAndHashCode(callSuper = true)
public class JwtAuthenticationToken extends AbstractAuthenticationToken {

    private final Object principal;
    private final String token;
    private final String tenantId;
    private final String deviceId;

    public JwtAuthenticationToken(Collection<? extends GrantedAuthority> authorities, Object principal, String token, String tenantId, String deviceId) {
        super(authorities);
        this.principal = principal;
        this.token = token;
        this.tenantId = tenantId;
        this.deviceId = deviceId;
        super.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return token;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }

}