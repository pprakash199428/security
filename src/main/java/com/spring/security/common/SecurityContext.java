package com.spring.security.common;

import com.spring.security.dto.UserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SecurityContext {

    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }


    public UserDetails getUserDetails() {
        Authentication auth = getAuthentication();
        List<? extends GrantedAuthority> authorities = (List<GrantedAuthority>) auth.getAuthorities();
        return new UserDetails(auth.getName(), authorities.get(0).getAuthority()," ");

    }

}
