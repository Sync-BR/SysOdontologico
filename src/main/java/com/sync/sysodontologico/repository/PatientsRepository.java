package com.sync.sysodontologico.repository;

import com.sync.sysodontologico.dto.PatientsDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PatientsRepository extends JpaRepository<PatientsDto, Long> {
    @Query(value = "SELECT * FROM PATIENTS WHERE CLINIC_ID = :id", nativeQuery = true)
    List<PatientsDto> getPatientsById(int id);
    PatientsDto findById(long id);
    @Query(value = "SELECT * FROM PATIENTS WHERE CLINIC_ID = :clinicId AND CPF = :cpf", nativeQuery = true)
    PatientsDto findByClinicIdAndCpf(@Param("clinicId") Long clinicId, @Param("cpf") String cpf);
    @Query(value = "SELECT * FROM PATIENTS WHERE CLINIC_ID = :clinicId AND EMAIL = :email", nativeQuery = true)
    PatientsDto findByClinicIdAndEmail(@Param("clinicId") Long clinicId, @Param("email") String email);


}
