package com.example.demo.repository;

import com.example.demo.model.Blog;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog,Long> {
    List<Blog> findAllByUserIdAndAndStatusContainingIgnoreCase(Long userId, String status);
    List<Blog> findAllByStatusContainingIgnoreCase(String status);
    List<Blog> findAllByOrderByLikeCount();
    List<Blog> findAllByOrderByLikeCountDesc();
    @Query("SELECT p FROM Blog p ORDER BY p.likeCount DESC LIMIT 4")
    List<Blog> findTop4ByOrderByLikesDesc();
}
