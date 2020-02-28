package com.vdgo.bypass.execvdgo.repo;

import com.vdgo.bypass.execvdgo.domain.Addr;
import com.vdgo.bypass.execvdgo.domain.FileStorage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface FileStorageRepo extends JpaRepository<FileStorage, Integer> {
    List<FileStorage> findByAddress(Addr addr);
    FileStorage findById(int id);

    @Query(value = "DELETE from FileStorage f WHERE f.id=:id")
    @Modifying
    @Transactional
    void deleteById(@Param("id") int id);
}
