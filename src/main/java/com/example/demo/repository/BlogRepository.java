package com.example.demo.repository;

import com.example.demo.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog,Long> {
    List<Blog> findAllByOrderByLocalDateTime();
    List<Blog> findAllByStatusId(Long id);
    List<Blog> findByStatusIdIn(List<Long> statusIds);
    List<Blog> findByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(String title, String content);
}
