package springboot3.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;

@Configuration
//@EnableGlobalMethodSecurity(prePostEnabled = true) //DEPRECATED
@EnableMethodSecurity
public class ProjectConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        return httpSecurity
//                .authorizeRequests() //DEPRECATED
                .authorizeHttpRequests()
//                .antMatchers() //DEPRECATED
//                .mvcMatchers() //DEPRECATED
                .requestMatchers("/spr3")
//                .authenticated()
                .access(new WebExpressionAuthorizationManager("isAuthenticated()"))
                .and()
                .build();

    }
}
