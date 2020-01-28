package com.vdgo.bypass.execvdgo.service;

import com.vdgo.bypass.execvdgo.domain.Executor;
import com.vdgo.bypass.execvdgo.repo.ExecutorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ExecutorService implements UserDetailsService
{

    @Autowired
    private ExecutorRepo executorRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        Executor executorFindByUsername = executorRepo.findByUsername(username);

        if(executorFindByUsername != null)
        {
            return  executorFindByUsername;
        }

        return null;
    }
}