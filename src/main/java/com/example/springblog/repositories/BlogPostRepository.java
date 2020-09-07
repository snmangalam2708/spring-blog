package com.example.springblog.repositories;

import com.example.springblog.model.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface BlogPostRepository extends JpaRepository<BlogPost, Long> {

    List<BlogPost> findAllByDateCreatedAfter(LocalDate date);

    List<BlogPost> findAllByTag(String tag);

    List<BlogPost> findAllByUsername(String username);

}
