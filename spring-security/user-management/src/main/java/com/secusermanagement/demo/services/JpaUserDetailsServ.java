package com.secusermanagement.demo.services;

import com.secusermanagement.demo.repositories.UserRepository;
import com.secusermanagement.demo.security.SecurityUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JpaUserDetailsServ implements UserDetailsService {

    private final UserRepository userRepository;

    public JpaUserDetailsServ(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) {
        var u = userRepository.findUserByUsername(username);
        return u.map(SecurityUser::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
