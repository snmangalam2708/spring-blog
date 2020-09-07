package com.example.springblog.repositories;

import com.example.springblog.model.BlogComments;
import com.example.springblog.model.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface BlogCommentsRepository extends JpaRepository<BlogComments, Long> {

    List<BlogComments> findAllByBlogId(Long blogId);

    List<BlogComments> findAllByUserId(Long userId);


}
