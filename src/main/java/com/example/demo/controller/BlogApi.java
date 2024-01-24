package com.example.demo.controller;

import com.example.demo.model.Blog;
import com.example.demo.model.Status;
import com.example.demo.repository.BlogRepository;
import com.example.demo.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;


@RestController
@CrossOrigin("*")
@RequestMapping("/api/blog")
public class BlogApi {
    @Autowired
    BlogRepository blogRepository;

    @Autowired
    StatusRepository statusRepository;

    @GetMapping
    public ResponseEntity findAll() {
        return new ResponseEntity(blogRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity create(@RequestBody Blog blog) {
        blog.setLocalDateTime(LocalDateTime.now());
        return new ResponseEntity(blogRepository.save(blog), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity edit(@RequestBody Blog blog , @PathVariable Long id) {
        blog.setId(id);
        return new ResponseEntity(blogRepository.save(blog), HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        blogRepository.deleteById(id);
        return new ResponseEntity( "Delete Done",HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable Long id){
        return new ResponseEntity(blogRepository.findById(id),HttpStatus.OK);
    }

    @GetMapping("/sorted")
    public ResponseEntity sort(){
        return new ResponseEntity<>(blogRepository.findAllByOrderByLocalDateTime(),HttpStatus.OK);
    }

    @GetMapping("/byStatus/{id}")
    public ResponseEntity findAllByStatus(@PathVariable Long id){
        return new ResponseEntity(blogRepository.findAllByStatusId(id),HttpStatus.OK);
    }

    @PostMapping("/findByS")
    public ResponseEntity findByStatus(@RequestBody Map<String, List<Long>> statusIdsMap) {
        List<Long> statusIds = statusIdsMap.get("statusIds");
        List<Blog> blogs = blogRepository.findByStatusIdIn(statusIds);
        return new ResponseEntity<>(blogs, HttpStatus.OK);
    }

    @GetMapping("/findByTitleOrContent")
    public ResponseEntity<List<Blog>> findByTitleOrContent(@RequestParam String query) {
        List<Blog> blogs = blogRepository.findByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(query, query);
        return ResponseEntity.ok(blogs);
    }



}
