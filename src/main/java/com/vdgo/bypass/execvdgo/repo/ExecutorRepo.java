package com.vdgo.bypass.execvdgo.repo;

import com.vdgo.bypass.execvdgo.domain.Executor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExecutorRepo extends JpaRepository<Executor, Integer> {
    Executor findByUsername(String username);
}
