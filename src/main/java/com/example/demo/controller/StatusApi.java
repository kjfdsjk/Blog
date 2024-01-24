package com.example.demo.controller;

import com.example.demo.model.Status;
import com.example.demo.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/status")
public class StatusApi {
    @Autowired
    StatusRepository statusRepository;

    @GetMapping
    public ResponseEntity findAll() {
        return new ResponseEntity(statusRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable Long id){
        return new ResponseEntity(statusRepository.findById(id),HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity create(@RequestBody Status status ) {
        return new ResponseEntity(statusRepository.save(status), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity edit(@RequestBody Status status , @PathVariable Long id) {
        status.setId(id);
        return new ResponseEntity(statusRepository.save(status), HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        statusRepository.deleteById(id);
        return new ResponseEntity( "Delete Done",HttpStatus.OK);
    }
}
