package com.iege.cryptocurrency.service.impl;

import com.iege.cryptocurrency.entity.SecUserDetails;
import com.iege.cryptocurrency.entity.User;
import com.iege.cryptocurrency.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class SecUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(userName);
        if (user == null) {
            throw new UsernameNotFoundException(userName);
        } else if (!user.isActive()) {
            throw new DisabledException("The email with confirmation link was sent for " + userName);
        } else {
            return new SecUserDetails(user);
        }
    }
}
