package patientmanagementsystem.pms.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Value("${jwksUri}")
    private String jwksUri;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                // address of auth server to get public key
                .oauth2ResourceServer(
                        r -> r.jwt().jwkSetUri(jwksUri)
                                .jwtAuthenticationConverter(new CustomJwtAuthenticationTokenConverter())
                )
                .authorizeHttpRequests()
                .anyRequest().authenticated()
                .and().build();
    }
}
