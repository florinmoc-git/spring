package methodsec.methodSec.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.httpBasic()
                .and().authorizeRequests()
                .anyRequest().authenticated()
                .and().build();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        var uds = new InMemoryUserDetailsManager();

        var user1 = User.withUsername("user1")
                .password(passwordEncoder().encode("pass"))
                .authorities("read")
                .build();
        var user2 = User.withUsername("user2")
                .password(passwordEncoder().encode("pass"))
                .authorities("write")
                .build();

        uds.createUser(user1);
        uds.createUser(user2);

        return uds;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
       return new BCryptPasswordEncoder();
    }
}
