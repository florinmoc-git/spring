package patientmanagementsystem.pms.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.Collection;

public class CustomJwtAuthenticationToken extends JwtAuthenticationToken {

    // extra field for Authentication object
    private String extraField;

    public CustomJwtAuthenticationToken(Jwt jwt, Collection<? extends GrantedAuthority> authorities) {
        super(jwt, authorities);
        extraField = "An extra field";
    }
}
