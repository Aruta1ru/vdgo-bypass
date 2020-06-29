package com.vdgo.bypass.execvdgo.repo;

import com.vdgo.bypass.execvdgo.domain.UndoneReason;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UndoneReasonRepo extends JpaRepository<UndoneReason, Integer> {
}
