package com.vdgo.bypass.execvdgo.repo;

import com.vdgo.bypass.execvdgo.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepo extends JpaRepository<Client, Integer> {
}
