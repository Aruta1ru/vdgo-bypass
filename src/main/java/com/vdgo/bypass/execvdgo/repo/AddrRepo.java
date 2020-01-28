package com.vdgo.bypass.execvdgo.repo;

import com.vdgo.bypass.execvdgo.domain.Addr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddrRepo extends JpaRepository<Addr, Integer> {
}
