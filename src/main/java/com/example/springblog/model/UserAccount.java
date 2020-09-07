package com.example.springblog.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "date_created")
    private LocalDate dateCreated;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "email")
    private String email;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "userAccount")
    @JsonIgnore
    private List<BlogPost> blogPostList;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private List<BlogComments> commentList;

    public UserAccount() {
    }

    public UserAccount(LocalDate dateCreated, String username, String password, String email) {
        this.dateCreated = dateCreated;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public UserAccount(Long userId, LocalDate dateCreated, String username, String password, String email) {
        this(dateCreated, username, password, email);
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<BlogPost> getBlogPostList() {
        return blogPostList;
    }

    public void setBlogPostList(List<BlogPost> blogPostList) {
        this.blogPostList = blogPostList;
    }

    public List<BlogComments> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<BlogComments> commentList) {
        this.commentList = commentList;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        UserAccount userAccount = (UserAccount) o;
//        return Objects.equals(userId, userAccount.userId) &&
//                Objects.equals(dateCreated, userAccount.dateCreated) &&
//                Objects.equals(username, userAccount.username) &&
//                Objects.equals(password, userAccount.password) &&
//                Objects.equals(email, userAccount.email);
//    }
}
