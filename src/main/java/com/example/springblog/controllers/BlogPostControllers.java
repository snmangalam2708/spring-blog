package com.example.springblog.controllers;

import com.example.springblog.model.BlogPost;
import com.example.springblog.services.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/home")
public class BlogPostControllers {

    private BlogPostService blogPostService;

    @Autowired
    public BlogPostControllers(BlogPostService blogPostService){ this.blogPostService=blogPostService;}

    @GetMapping("/{blogId}")
    public ResponseEntity<BlogPost> findById(@PathVariable Long blogId){

        return blogPostService.findById(blogId)
                .map(blogPost -> ResponseEntity.ok().body(blogPost))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/all/{username}")
    public ResponseEntity<List<BlogPost>> findAllByUsername(@PathVariable String username){

        return new ResponseEntity<>(blogPostService.findAllByUsername(username), HttpStatus.OK);
    }

    @GetMapping("/all/{tag}")
    public ResponseEntity<List<BlogPost>> findAllByTag(@PathVariable String tag){

        return new ResponseEntity<>(blogPostService.findAllByTag(tag), HttpStatus.OK);
    }

    @GetMapping("/all/{date}")
    public ResponseEntity<List<BlogPost>> findAllByDate(@RequestParam String year, @RequestParam String month, @RequestParam String day ){

        LocalDate date = LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
        return new ResponseEntity<>(blogPostService.findAllByDateCreatedAfter(date), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<BlogPost>> findAll(){

        return new ResponseEntity<>(blogPostService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<?> savePost(@RequestBody BlogPost blogPost){

        BlogPost newBlogPost = blogPostService.savePost(blogPost);

        try {
                return ResponseEntity.created(new URI("/home"+blogPost.getBlogId()))
                                    .body(blogPost);
        } catch (URISyntaxException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/update/{blogId}")
    public ResponseEntity<?> updateBlogpost(@PathVariable Long blogId, @RequestBody BlogPost newBlogPost){

        Optional<BlogPost> updatedBlogPost = blogPostService.updatePost(blogId, newBlogPost);

        return updatedBlogPost
                .map(blogPost -> {
                    try{
                        return ResponseEntity
                                .ok()
                                .location(new URI("/home/" + blogPost.getBlogId()))
                                .body(blogPost);
                    }
                    catch(URISyntaxException e){ return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); }
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("delete/{blogId}")
    public ResponseEntity<Boolean> deleteByBlogId(@PathVariable Long blogID){

        if(blogPostService.deletePostById(blogID))
        { return ResponseEntity.ok().build(); }
        else return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/all")
    public ResponseEntity<Boolean> deleteAll(){

        return new ResponseEntity<>(blogPostService.deleteAll(), HttpStatus.OK);
    }




}
