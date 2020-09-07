package com.example.springblog.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class BlogComments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long commentId;
    @Column(name = "date_created")
    private LocalDate dateCreated;
    @Column(name = "username")
    private String username;
//    @Column(name = "author_email")
//    private String authorEmail;
    @Column(name = "comment_text")
    private String commentText;
    @Column(name = "likes")
    private Integer likes;
    @Column(name = "blog_id")
    private Long blogId;
    @Column(name = "user_id")
    private Long userId;

    public BlogComments() {
    }

    public BlogComments(LocalDate dateCreated, String username, String commentText, Integer likes, Long blogId, Long userId) {
        this.dateCreated = dateCreated;
        this.username = username;
        this.commentText = commentText;
        this.likes = likes;
        this.blogId = blogId;
        this.userId = userId;
    }

    public BlogComments(Long commentId, LocalDate dateCreated, String username, String text, Integer likes, Long blogId, Long userId) {
        this(dateCreated, username, text, likes, blogId, userId);
        this.commentId = commentId;
    }


    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getAuthorName() {
        return username;
    }

    public void setAuthorName(String authorName) {
        this.username = username;
    }

//    public String getAuthorEmail() {
//        return authorEmail;
//    }
//
//    public void setAuthorEmail(String authorEmail) {
//        this.authorEmail = authorEmail;
//    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Long getBlogId() {
        return blogId;
    }

    public void setBlogId(Long blogId) {
        this.blogId = blogId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BlogComments comments = (BlogComments) o;
        return Objects.equals(commentId, comments.commentId) &&
                Objects.equals(dateCreated, comments.dateCreated) &&
                Objects.equals(username, comments.username) &&
                Objects.equals(commentText, comments.commentText) &&
                Objects.equals(likes, comments.likes) &&
                Objects.equals(blogId, comments.blogId) &&
                Objects.equals(userId, comments.userId);
    }
}
