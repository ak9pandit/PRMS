package com.ashish.PRMS.security;

import com.ashish.PRMS.auth.entity.User;
import com.ashish.PRMS.auth.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException(username));

        UserDetails userDetails = org.springframework.security.core.userdetails.User
                                        .builder()
                                        .username(username)
                                        .password(user.getPassword())
                                        .authorities(user.getRole().name())
                                        .build();
        return userDetails;
    }
}
