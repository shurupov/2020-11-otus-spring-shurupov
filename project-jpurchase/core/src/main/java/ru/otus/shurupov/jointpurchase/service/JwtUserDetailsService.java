package ru.otus.shurupov.jointpurchase.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.otus.shurupov.jointpurchase.repository.UserRepository;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(JwtUserDetailsService.class);

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ru.otus.shurupov.jointpurchase.domain.User user = userRepository.getByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("User not found with username: " + username)
        );
        return new User(user.getUsername(), user.getPassword(),
                user.getRoles().stream()
                    .map(r -> new SimpleGrantedAuthority(r.name()))
                    .collect(Collectors.toList())
        );


    }
}
