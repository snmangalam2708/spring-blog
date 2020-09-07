package com.example.springblog.services;

import com.example.springblog.model.BlogPost;
import com.example.springblog.repositories.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BlogPostService {

    private BlogPostRepository blogPostRepository;

    @Autowired
    public BlogPostService(BlogPostRepository blogPostRepository) {

        this.blogPostRepository = blogPostRepository;
    }

    public List<BlogPost> findAll(){

        return blogPostRepository.findAll();
    }

    public Optional<BlogPost> findById(Long blogId){

        return blogPostRepository.findById(blogId);
    }

    public List<BlogPost> findAllByUsername(String username){

        return blogPostRepository.findAllByUsername(username);
    }

    public List<BlogPost> findAllByDateCreatedAfter(LocalDate date){

        return blogPostRepository.findAllByDateCreatedAfter(date);
    }

    public List<BlogPost> findAllByTag(String tag){

        return blogPostRepository.findAllByTag(tag);
    }

    public BlogPost savePost(BlogPost blogPost) {

        return blogPostRepository.save(blogPost);
    }

    public Optional<BlogPost> updatePost(Long blogId, BlogPost newBlogPost){

        Optional<BlogPost> currentBlogPost = findById(blogId);

        if(currentBlogPost.isPresent()){
            currentBlogPost.get().setTitle(newBlogPost.getTitle());
            currentBlogPost.get().setBody(newBlogPost.getBody());
            currentBlogPost.get().setTag(newBlogPost.getTag());
            currentBlogPost.get().setStatus(newBlogPost.getStatus());
            blogPostRepository.save(currentBlogPost.get());
        }

        return currentBlogPost;
    }

    public Boolean deletePostById(Long blogId) {

        if (findById(blogId).isPresent()) {
            blogPostRepository.deleteById(blogId);
            return true;
        }
        return false;
    }

    public Boolean deleteAll() {

        blogPostRepository.deleteAll();
        return true;
    }
}
