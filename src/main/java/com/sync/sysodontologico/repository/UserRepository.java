package com.sync.sysodontologico.repository;

import com.sync.sysodontologico.dto.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserDto, Long> {
    UserDto findByUsername(String username);
}
