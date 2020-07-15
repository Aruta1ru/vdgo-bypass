package com.vdgo.bypass.execvdgo.repo;

import com.vdgo.bypass.execvdgo.domain.Bypass;
import com.vdgo.bypass.execvdgo.domain.Executor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BypassRepo extends JpaRepository<Bypass, Integer> {
    List<Bypass> findByExecutorAndBypassDate(Executor executor, LocalDateTime bypassDate);
    Bypass findById(int id);
}
