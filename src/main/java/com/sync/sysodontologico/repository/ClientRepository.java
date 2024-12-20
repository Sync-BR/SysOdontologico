package com.sync.sysodontologico.repository;

import com.sync.sysodontologico.dto.ClientDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<ClientDto, Long> {
    ClientDto findByCpf(String cpf);
    ClientDto findByEmail(String email);
    @Query(value = "SELECT * FROM CLIENTS WHERE user_id = :userId", nativeQuery = true)
    Optional<ClientDto> findByUserId(long userId);
    @Query(value = "SELECT * FROM CLIENTS WHERE clinic_id = :clinic_id", nativeQuery = true)
    Optional<ClientDto> findByClinicId(long userId);

}
