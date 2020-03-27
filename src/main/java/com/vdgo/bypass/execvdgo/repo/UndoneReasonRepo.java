package com.vdgo.bypass.execvdgo.repo;

import com.vdgo.bypass.execvdgo.domain.UndoneReason;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UndoneReasonRepo extends JpaRepository<UndoneReason, Byte> {
    List<UndoneReason> findAll();
}
