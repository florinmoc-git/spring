package endpointsecurity.endpointSec.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.httpBasic()
                .and().authorizeRequests()

//                AUTHORIZATION RULES
//                matcher method + authorization rule
//                .anyRequest().authenticated() // endpoint level authorization
//                .anyRequest().permitAll() // not allowed users with failed authentication
//                .anyRequest().hasAuthority("read")
//                .anyRequest().hasAnyAuthority("read", "write")
//                .anyRequest().hasRole("ADMIN")
//                .anyRequest().hasAnyRole("ADMIN", "USER")
//                .anyRequest().access("isAuthenticated() and hasAuthority('read')") // SpEL rules
//                .anyRequest().denyAll() // all requests denied; recommended at the end of this chain

//                MATCHER METHODS
                .mvcMatchers("/end1/**").authenticated()
                .mvcMatchers(HttpMethod.POST,"/end2/**").hasAuthority("write")
                .mvcMatchers(HttpMethod.GET,"/end2/**").hasAnyAuthority("read")
                .and().csrf().disable() // DON'T DO THIS IN PROD!!! Just to make requests work in Postman


//                can use ant expressions such as /endpoint/abc/*/def, where * can be any path

                .build();

    }

    @Bean
    public UserDetailsService userDetailsService(){
        var uds = new InMemoryUserDetailsManager();

        var user1 = User.withUsername("user1")
                .password(passwordEncoder().encode( "pass"))
                .authorities("read")
//                .roles("ADMIN") // equivalane with and authority called ROLE_ADMIN
                .build();
        uds.createUser(user1);

        var user2 = User.withUsername("user2")
                .password(passwordEncoder().encode( "pass"))
                .authorities("write")
//                .roles("USER")
                .build();
        uds.createUser(user2);

        return uds;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
