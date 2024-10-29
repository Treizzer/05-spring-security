package com.treizer.spring_security_app.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.treizer.spring_security_app.persistence.repositories.IUserRepository;

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "El usuario " + username + " no existe."));

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        user.getRoles()
                .forEach(
                        role -> authorities.add(new SimpleGrantedAuthority(
                                "ROLE_".concat(role.getRoleName().name()))));

        user.getRoles().stream()
                .flatMap(role -> role.getPermissions().stream())
                .forEach(permission -> authorities
                        .add(new SimpleGrantedAuthority(permission.getName())));

        return new User(user.getUsername(),
                user.getPassword(),
                user.getIsEnabled(),
                user.getAccountNoExpired(),
                user.getCredentialNoExpired(),
                user.getAccountNoLocked(),
                authorities);
    }

}
