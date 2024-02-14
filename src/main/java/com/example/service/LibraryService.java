package com.example.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Library;
import com.example.entity.Log;
import com.example.repository.LibraryRepository;
import com.example.repository.LogRepository;

@Service
public class LibraryService {

    private final LibraryRepository libraryRepository;
    
    private final LogRepository logRepository;

    @Autowired
    public LibraryService(LibraryRepository libraryRepository, LogRepository logRepository) {
        this.libraryRepository = libraryRepository;
        this.logRepository = logRepository;
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
    	
    	Log log = new Log();
    	log.setId(id);
    	log.setLibraryId(library.getId());
    	log.setUserId(loginUser.getUser().getId());
    	log.setRentDate(LocalDateTime.now());
    	log.setReturnDueDate(LocalDateTime.parse(returnDueDate + "T00:00:00"));
    	log.setReturnDate(null);
    	logRepository.save(log);
    	
    }
    

}