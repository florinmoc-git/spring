package OAuth2AuthorizationServer.demo.services;

import OAuth2AuthorizationServer.demo.model.SecurityUser;
import OAuth2AuthorizationServer.demo.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username)  {
        var user = userRepository.findByUsername(username);
        return user.map(SecurityUser::new).orElseThrow(() -> new UsernameNotFoundException("User not found!"));
    }
}
