package com.sync.sysodontologico.repository;

import com.sync.sysodontologico.dto.PatientsDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientsRepository extends JpaRepository<PatientsDto, Long> {
    List<PatientsDto> getPatientsById(int id);
}
