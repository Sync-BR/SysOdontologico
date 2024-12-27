package com.sync.sysodontologico.repository;

import com.sync.sysodontologico.dto.DentistDto;
import com.sync.sysodontologico.dto.PatientsDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DentistRepository  extends JpaRepository<DentistDto, Long> {
    @Query(value = "SELECT * FROM DENTIST WHERE CLINIC_ID = :id", nativeQuery = true)
    List<DentistDto> getDentistById(int id);
    @Query(value = "SELECT * FROM DENTIST WHERE CLINIC_ID = :clinicId AND CPF = :cpf", nativeQuery = true)
    DentistDto findByClinicIdAndCpf(@Param("clinicId") Long clinicId, @Param("cpf") String cpf);
    @Query(value = "SELECT * FROM DENTIST WHERE CLINIC_ID = :clinicId AND EMAIL = :email", nativeQuery = true)
    DentistDto findByClinicIdAndEmail(@Param("clinicId") Long clinicId, @Param("email") String email);
}
