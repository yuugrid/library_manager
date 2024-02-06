package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Logs;
import com.example.repository.LogsRepository;

@Service
public class LogsService {

    private final LogsRepository logsRepository;

    @Autowired
    public LogsService(LogsRepository logsRepository) {
        this.logsRepository = logsRepository;
    }

    public List<Logs> findAll() {
        return this.logsRepository.findAll();
    }

}