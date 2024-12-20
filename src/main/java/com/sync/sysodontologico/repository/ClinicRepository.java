package com.sync.sysodontologico.repository;

import com.sync.sysodontologico.dto.ClinicDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClinicRepository extends JpaRepository<ClinicDto, Long> {
}
