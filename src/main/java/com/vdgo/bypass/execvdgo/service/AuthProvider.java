package com.vdgo.bypass.execvdgo.service;

import com.vdgo.bypass.execvdgo.domain.Executor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class AuthProvider implements AuthenticationProvider {

    @Autowired
    private ExecutorService executorService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public org.springframework.security.core.Authentication authenticate(org.springframework.security.core.Authentication authentication) throws org.springframework.security.core.AuthenticationException {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();

        Executor executor = (Executor) executorService.loadUserByUsername(username);

        if(executor != null && (executor.getUsername().equals(username)))
        {
            //if(!passwordEncoder.matches(password, executor.getPassword()))
            if (!password.equals(executor.getPassword()))
            {
                throw new BadCredentialsException("Неверный пароль! Повторите ввод!");
            }

            Collection<? extends GrantedAuthority> authorities = executor.getRoles();

            return new UsernamePasswordAuthenticationToken(executor, password, authorities);
        }
        else
            throw new BadCredentialsException("Данный пользователь не найден :(");
    }

    public boolean supports(Class<?> arg)
    {
        return true;
    }
}
