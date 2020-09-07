package com.example.springblog.services;

import com.example.springblog.model.BlogComments;
import com.example.springblog.repositories.BlogCommentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BlogCommentsService {

    private BlogCommentsRepository blogCommentsRepository;

    @Autowired
    public BlogCommentsService(BlogCommentsRepository blogCommentsRepository) {

        this.blogCommentsRepository = blogCommentsRepository;
    }

    public Optional<BlogComments> findById(Long commentId){

        return blogCommentsRepository.findById(commentId);
    }

    public List<BlogComments> findAllByUserId(Long userId){

        return blogCommentsRepository.findAllByUserId(userId);
    }

    public List<BlogComments> findAllByBlogId(Long blogID){

        return blogCommentsRepository.findAllByBlogId(blogID);
    }

    public List<BlogComments> findAllComments(){

        return blogCommentsRepository.findAll();
    }

    public BlogComments saveComments(BlogComments blogComments) {

        return blogCommentsRepository.save(blogComments);
    }

    public Optional<BlogComments> updateComments(Long commentId, String newComment ){

       Optional<BlogComments> currentComment = blogCommentsRepository.findById(commentId);

       if(currentComment.isPresent()){
           currentComment.get().setCommentText(newComment);
           blogCommentsRepository.save(currentComment.get());
       }

       return currentComment;
    }

    public Boolean deleteCommentsById(Long commentId) {

        Optional<BlogComments> currentComment = blogCommentsRepository.findById(commentId);

        if (currentComment.isPresent()) {
            blogCommentsRepository.deleteById(commentId);
            return true;
        }
        return false;
    }

    public Boolean deleteAll() {

        blogCommentsRepository.deleteAll();
        return true;
    }

    public Optional<BlogComments> increaseLike(Long commentId){

        Optional<BlogComments> currentLikeNumber = blogCommentsRepository.findById(commentId);
        if (currentLikeNumber.isPresent()) {
            currentLikeNumber.get().setLikes(currentLikeNumber.get().getLikes()+1);
            blogCommentsRepository.save(currentLikeNumber.get());
        }

        return currentLikeNumber;
    }

    public Optional<BlogComments> decreaseLike(Long commentId){

        Optional<BlogComments> currentLikeNumber = blogCommentsRepository.findById(commentId);
        if (currentLikeNumber.isPresent() && currentLikeNumber.get().getLikes()>=1 ) {
            currentLikeNumber.get().setLikes(currentLikeNumber.get().getLikes()-1);
            blogCommentsRepository.save(currentLikeNumber.get());
        }

        return currentLikeNumber;

    }
}

