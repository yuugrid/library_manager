package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Log;
import com.example.repository.LogRepository;

@Service
public class LogService {

    private final LogRepository logsRepository;

    @Autowired
    public LogService(LogRepository logsRepository) {
        this.logsRepository = logsRepository;
    }

    public List<Log> findAll() {
        return this.logsRepository.findAll();
    }

}