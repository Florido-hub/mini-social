package com.florido.workshopmongo.config.security;

import com.florido.workshopmongo.common.exceptions.NotFoundException;
import com.florido.workshopmongo.common.model.document.User;
import com.florido.workshopmongo.query.user.UserQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService{

    private final UserQueryService userQueryService;

    @Override
    public UserDetails loadUserByUsername(String password) throws UsernameNotFoundException {
        User user = userQueryService.findByEmail(password).orElseThrow(() -> new NotFoundException("User not found"));

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getName())
                .password(user.getPassword())
                .build();
    }
}
