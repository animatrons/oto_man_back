package com.oto.back.controller.security;

import com.oto.back.app.UserApp;
import com.oto.back.model.User;
import com.oto.back.model.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthUserDetailsService implements UserDetailsService {

    private final UserApp userApp;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user;
        try {
            user = userApp.getUserByEmail(email);
        } catch (Exception e) {
            throw new UsernameNotFoundException("USER NOT FOUND");
        }
        return user;
    }
}
