package com.vdgo.bypass.execvdgo.config;

import com.vdgo.bypass.execvdgo.repo.ExecutorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.vdgo.bypass.execvdgo.service.AuthProvider;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter
{
    @Autowired
    private AuthProvider authProvider;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ExecutorRepo executorRepo;

    @Bean
    PasswordEncoder passwordEncoder()
    {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http
                .authorizeRequests()
                .antMatchers(  "/", "/login**").permitAll()
                .anyRequest().authenticated()
                .and().formLogin().loginPage("/login")
                .defaultSuccessUrl("/").failureUrl("/login?error").permitAll()
                .and().logout().logoutSuccessUrl("/").permitAll().and().csrf().disable();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
    {
        auth.authenticationProvider((AuthenticationProvider) authProvider);
    }
}
