package com.example.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Library;
import com.example.entity.Logs;
import com.example.repository.LibraryRepository;
import com.example.repository.LogsRepository;

@Service
public class LibraryService {

    private final LibraryRepository libraryRepository;
    
    private final LogsRepository logsRepository;

    @Autowired
    public LibraryService(LibraryRepository libraryRepository, LogsRepository logsRepository) {
        this.libraryRepository = libraryRepository;
        this.logsRepository = logsRepository;
    }

    public List<Library> findAll() {
        return this.libraryRepository.findAll();
    }
    
    public Library findById(Integer id) {
    	Optional<Library> optionalLibrary = this.libraryRepository.findById(id);
    	Library library = optionalLibrary.get();
    	return library;
    }
    
    public void borrowBook(Integer id, String returnDueDate, LoginUser loginUser) {
    	Library library = findById(id);
    	
    	library.setUserId(loginUser.getUser().getId());
    	libraryRepository.save(library);
    	
    	Logs logs = new Logs();
    	logs.setLibraryId(library.getId());
    	logs.setUserId(loginUser.getUser().getId());
    	logs.setRentDate(LocalDateTime.now());
    	logs.setReturnDueDate(LocalDateTime.parse(returnDueDate + "T00:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME));
    	logs.setReturnDate(null);
    	logsRepository.save(logs);
    	
    }
    

}