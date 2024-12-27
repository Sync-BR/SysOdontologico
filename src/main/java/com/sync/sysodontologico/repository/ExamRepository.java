package com.sync.sysodontologico.repository;

import com.sync.sysodontologico.dto.ExamDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExamRepository extends JpaRepository<ExamDto, Long> {
    @Query(value = "SELECT * FROM EXAM WHERE PATIENTS_ID = :patientId AND CLINIC_ID = :clinicId", nativeQuery = true)
    List<ExamDto> findExamsByPatientAndClinic(Long patientId, Long clinicId);
}
