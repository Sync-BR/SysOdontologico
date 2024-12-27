package com.sync.sysodontologico.repository;

import com.sync.sysodontologico.dto.ConsultDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ConsultRepository extends JpaRepository<ConsultDto, Long> {
    @Query(value = "SELECT * FROM CONSULT WHERE clinic_id = :clinicId", nativeQuery = true)
    List<ConsultDto> getConsultByClinicId(@Param("clinicId") Long clinicId);

}
