package com.example.springblog.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class BlogPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "blog_id")
    private Long blogId;
    @Column(name = "blog_date_created")
    private LocalDate dateCreated;
    @Column(name = "username")
    private String username;
    @Column(name = "title")
    private String title;
    @Column(name = "body", length = 6500)
    private String body;
    @Column(name = "tag")
    private String tag;
    @Column(name = "status")
    private String status;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "blog_id")
    private List<BlogComments> listOfBlogComments;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserAccount userAccount;

    public BlogPost() {
    }

    public BlogPost(LocalDate dateCreated, String username, String title, String body, String tag, String status) {
        this.dateCreated = dateCreated;
        this.username = username;
        this.title = title;
        this.body = body;
        this.tag = tag;
        this.status = status;
    }

    public BlogPost(Long blogId, LocalDate dateCreated, String username, String title, String body, String tag, String status) {
        this(dateCreated, username, title, body, tag, status);
        this.blogId = blogId;
    }

    public Long getBlogId() {
        return blogId;
    }

    public void setBlogId(Long blogId) {
        this.blogId = blogId;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<BlogComments> getListOfBlogComments() {
        return listOfBlogComments;
    }

    public void setListOfBlogComments(List<BlogComments> listOfBlogComments) {
        this.listOfBlogComments = listOfBlogComments;
    }
}
