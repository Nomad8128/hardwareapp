package hr.tvz.pilizota.hardwareapp.security.user;

import org.springframework.security.authentication.AbstractAuthenticationToken;

import java.io.Serial;
import java.util.Objects;

public class UserAuthentication extends AbstractAuthenticationToken {
    @Serial
    private static final long serialVersionUID = 3651172851990132112L;

    private final hr.tvz.pilizota.hardwareapp.security.user.ApplicationUser principal;

    public UserAuthentication(hr.tvz.pilizota.hardwareapp.security.user.ApplicationUser principal) {
        super(principal.getAuthorities());
        this.principal = principal;
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return "NO";
    }

    @Override
    public hr.tvz.pilizota.hardwareapp.security.user.ApplicationUser getPrincipal() {
        return principal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        UserAuthentication that = (UserAuthentication) o;
        return Objects.equals(principal, that.principal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), principal);
    }
}
