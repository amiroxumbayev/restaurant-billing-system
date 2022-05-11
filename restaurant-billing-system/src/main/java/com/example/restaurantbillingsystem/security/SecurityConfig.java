package com.example.restaurantbillingsystem.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.example.restaurantbillingsystem.data.UserRepository;
import com.example.restaurantbillingsystem.domain.User;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private AccessDeniedHandler accessDeniedHandler;
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Autowired
    public SecurityConfig(AccessDeniedHandler accessDeniedHandler) {
        this.accessDeniedHandler = accessDeniedHandler;
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(
                        "/",
                        "/js/**",
                        "/css/**",
                        "/img/**",
                        "/webjars/**").permitAll()
                    .antMatchers("/", "/home", "/about").permitAll()
                    .antMatchers("/admin/**").hasAnyRole("ADMIN")
                    .antMatchers("/user/**", "/main-dining/**", "/orders/**", "/order/**", "/statistics/**").hasAnyRole("USER")
                    .anyRequest().authenticated()
                .and()
                .formLogin()
                    .loginPage("/login")
                    .permitAll()
                    .and()
                .logout()
                    .permitAll()
                    .and()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler);
    }
    
    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepo) {
        return name -> {
            User user = userRepo.findByName(name);
            if (user != null) {
                return user;
            }
            if("admin".equals(name)) {
                return org.springframework.security.core.userdetails.User.builder()
                        .username("admin")
                        .password("$2y$10$NQEkeiO4OAHYVF2a7iysQegHnFXcrZbiDzzpWhuLIwDgWQz9mvPeC")
                        .roles("ADMIN")
                        .build();
            }
            throw new UsernameNotFoundException("User '" + name + "' not found");
        };
    }

}
