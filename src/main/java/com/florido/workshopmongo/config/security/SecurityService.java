package com.florido.workshopmongo.config.security;

import com.florido.workshopmongo.common.model.document.User;
import com.florido.workshopmongo.query.user.UserQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SecurityService {

    private final UserQueryService usuarioService;

    public User obterUsuario() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        String name =  userDetails.getUsername();

        return usuarioService.findByUsername(name);
    }
}
