package com.example.sping_portfolio.controllers.aboutTeam;

import com.example.sping_portfolio.controllers.aboutTeam.Profile;
import com.example.sping_portfolio.controllers.aboutTeam.ProfileJpaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class ModelRepository implements UserDetailsService {

    @Autowired
    private ProfileJpaRepository profileJpaRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserbyUsername(String name) throws UsernameNotFoundException {
        Profile profile = profileJpaRepository.findByName(String name);
        if (profile == null) {
            throw new UsernameNotFoundException("User not found in database");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        return new org.springframework.security.core.userdetails.User(person.)
    }
}
