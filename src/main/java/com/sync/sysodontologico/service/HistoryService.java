package com.sync.sysodontologico.service;

import com.sync.sysodontologico.dto.HistoryDto;
import com.sync.sysodontologico.repository.HistoryRepository;
import org.springframework.stereotype.Service;

@Service
public class HistoryService {
    private HistoryRepository historyRepository;

    public void registerHistory(HistoryDto history) {
        System.out.println(history);
        historyRepository.save(history);
    }

}
