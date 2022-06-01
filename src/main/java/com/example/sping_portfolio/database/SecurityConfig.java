package com.example.sping_portfolio.database;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.POST;

@Configuration @EnableWebSecurity @RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService; //constructor that gets past in below
    private final BCryptPasswordEncoder bCryptPasswordEncoder; //password encoder from spring

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder); //
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // remove web resources from security rules
        web
                .ignoring()
                .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**", "/fonts/**", "/scss/**", "/uploads/**")
        ;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception { //THis is going to be altered to use the JWT
        // security rules
        http
                .authorizeRequests()
                .antMatchers(POST, "/api/aboutTeam/post/**").hasAnyAuthority("CLUB_ADMIN")
                .antMatchers(DELETE, "/api/aboutTeam/delete/**").hasAnyAuthority("CLUB_ADMIN")
                .antMatchers("/aboutTeamCreate/**").hasAnyAuthority("CLUB_ADMIN")
                .antMatchers("/aboutTeamEdit/**").hasAnyAuthority("CLUB_ADMIN")
                .antMatchers("/aboutTeamDelete/**").hasAnyAuthority("CLUB_ADMIN")
                .antMatchers("/clubMembers/**").hasAnyAuthority("CLUB_ADMIN")
                .antMatchers( "/aboutTeam/**").permitAll()
                .antMatchers( "/api/refresh/token/**").permitAll()
                .antMatchers("/", "/CustomStickers", "/donations", "/events", "/RequestWorkshop", "/reviews", "/signup", "/signupSuccess", "/stickerSale").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/")
                .permitAll()
        ;
        // Cross-Site Request Forgery needs to be disabled to allow activation of JS Fetch URIs
        http.csrf().disable();
    }
}