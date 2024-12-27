package com.sync.sysodontologico.repository;

import com.sync.sysodontologico.dto.HistoryDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepository extends JpaRepository<HistoryDto, Long> {
}
