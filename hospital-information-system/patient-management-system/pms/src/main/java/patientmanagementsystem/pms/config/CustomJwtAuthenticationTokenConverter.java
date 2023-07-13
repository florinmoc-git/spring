package patientmanagementsystem.pms.config;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.List;

public class CustomJwtAuthenticationTokenConverter implements Converter<Jwt, JwtAuthenticationToken> {
    @Override
    public CustomJwtAuthenticationToken convert(Jwt source) {
        List<String> authorities = (List<String>)source.getClaims().get("authorities");

        var authorizationObj = new CustomJwtAuthenticationToken(
                source,
                authorities.stream()
                        .map(SimpleGrantedAuthority::new)
                        .toList()
        );
        return authorizationObj;
    }
}
