package com.example.springblog.controllers;

import com.example.springblog.model.BlogComments;
import com.example.springblog.model.BlogPost;
import com.example.springblog.services.BlogCommentsService;
import com.example.springblog.services.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

@RestController
@RequestMapping("/home/comment")
@CrossOrigin
public class BlogCommentsControllers{


    private BlogCommentsService blogCommentsService;

    @Autowired
    public BlogCommentsControllers(BlogCommentsService blogCommentsService){ this.blogCommentsService = blogCommentsService; }

    @GetMapping("/{commentId}")
    public ResponseEntity<?> findById(@PathVariable Long commentId){

        return blogCommentsService.findById(commentId)
                .map(blogComments -> ResponseEntity.ok().body(blogComments))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/all/{userId}")
    public ResponseEntity<?> findAllByUserId(@PathVariable Long userId){

        return new ResponseEntity(blogCommentsService.findAllByUserId(userId), HttpStatus.OK);

    }

    @GetMapping("/all/{blogId}")
    public ResponseEntity<?> findAllByBlogId(@PathVariable Long blogId){

        return new ResponseEntity(blogCommentsService.findAllByBlogId(blogId), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAll(){

        return new ResponseEntity(blogCommentsService.findAllComments(), HttpStatus.OK);
    }

    @GetMapping("/likes/{commentId}")
    public ResponseEntity<?> findLikes(@PathVariable Long commentId) {

        return new ResponseEntity(blogCommentsService.findLikes(commentId), HttpStatus.OK);
    }

    @GetMapping("/+likes/{commentId}")
    public ResponseEntity<?> increaseLike(@PathVariable Long commentId){

        return blogCommentsService.increaseLike(commentId)
                .map(comment -> ResponseEntity
                        .ok()
                        .body(comment))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/-likes/{commentId}")
    public ResponseEntity<?> decreaseLike(@PathVariable Long commentId){

        return blogCommentsService.decreaseLike(commentId)
                .map(comment -> ResponseEntity
                        .ok()
                        .body(comment))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/save")
    public ResponseEntity<BlogComments> saveComment(@RequestBody BlogComments blogComments) {

        BlogComments newComment = blogCommentsService.saveComments(blogComments);

        try {
            return ResponseEntity
                    .created(new URI("/home/comment/" + newComment.getCommentId()))
                    .body(newComment);
        } catch (URISyntaxException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/update/{commentId}")
    public ResponseEntity<?> updateComment(@PathVariable Long commentId, @RequestParam String newText) {

        Optional<BlogComments> updatedComment = blogCommentsService.updateComments(commentId, newText);

        return updatedComment
                .map(comment -> {
                    try{
                        return ResponseEntity
                                .ok()
                                .location(new URI("/home/comment/" + comment.getCommentId()))
                                .body(comment);
                    }catch(URISyntaxException e){
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                    }
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{commentId}")
    public ResponseEntity<Boolean> deleteComment(@PathVariable Long commentId) {

        if(blogCommentsService.deleteCommentsById(commentId))
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.notFound().build();
    }
}