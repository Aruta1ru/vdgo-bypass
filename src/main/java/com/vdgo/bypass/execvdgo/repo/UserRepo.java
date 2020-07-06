package com.vdgo.bypass.execvdgo.repo;

import com.vdgo.bypass.execvdgo.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository<User, Integer> {
    User findByUsername(String name);
}
