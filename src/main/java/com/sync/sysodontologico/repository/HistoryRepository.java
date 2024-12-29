package com.sync.sysodontologico.repository;

import com.sync.sysodontologico.dto.HistoryDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HistoryRepository extends JpaRepository<HistoryDto, Long> {
    @Query(value = "SELECT * FROM HISTORY WHERE ID_DOCTOR = :idDoctor" , nativeQuery = true)
    List<HistoryDto> findByDoctorId(@Param("idDoctor") int idDoctor);

    List<HistoryDto> findByIdDoctor(int idDoctor);



}
