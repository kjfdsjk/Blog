package com.example.demo.controller;

import com.example.demo.model.Blog;
import com.example.demo.model.User;
import com.example.demo.repository.BlogRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin("*")
@RequestMapping("/api/blogs")
public class BlogApi {
    @Autowired
    BlogRepository blogRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping
    public ResponseEntity findAll() {
        return new ResponseEntity(blogRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity create(@RequestBody Blog blog) {
        return new ResponseEntity(blogRepository.save(blog), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity edit(@RequestBody Blog blog, @PathVariable Long id) {
        blog.setId(id);
        return new ResponseEntity(blogRepository.save(blog), HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        blogRepository.deleteById(id);
        return new ResponseEntity("Delete Done", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable Long id) {
        return new ResponseEntity(blogRepository.findById(id), HttpStatus.OK);
    }

    @GetMapping("/find")
    public ResponseEntity findBlogByStatusAndUser(@RequestParam Long userId, @RequestParam String status) {
        Optional<User> optionalUser = userRepository.findById(userId);
        User user = optionalUser.get();
        List<Blog> blogs = blogRepository.findAllByUserIdAndAndStatusContainingIgnoreCase(user.getId(), status);
        return new ResponseEntity<>(blogs, HttpStatus.OK);
    }

    @GetMapping("/sort1")
    public ResponseEntity sort() {
        return new ResponseEntity<>(blogRepository.findAllByOrderByLikeCount(), HttpStatus.OK);
    }

    @GetMapping("/sort2")
    public ResponseEntity sortD() {
        return new ResponseEntity<>(blogRepository.findAllByOrderByLikeCountDesc(), HttpStatus.OK);
    }

    @GetMapping("/status")
    public ResponseEntity findByStatus(String status) {
        return new ResponseEntity<>(blogRepository.findAllByStatusContainingIgnoreCase(status), HttpStatus.OK);
    }

    @GetMapping("/top4")
    public ResponseEntity findTop4() {
        return new ResponseEntity<>(blogRepository.findTop4ByOrderByLikesDesc(), HttpStatus.OK);
    }
}
