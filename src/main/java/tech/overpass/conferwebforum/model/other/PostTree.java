package tech.overpass.conferwebforum.model.other;

import tech.overpass.conferwebforum.model.db.Post;
import tech.overpass.conferwebforum.model.db.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PostTree {

    private Long id;
    private String title;
    private String body;
    private Long authorId;
    private String authorUsername;
    private String authorFullName;
    private Date date;
    private List<PostTree> replies;

    public PostTree() {
    }

    public PostTree(Long id, String title, String body, User author, Date date, List<PostTree> replies) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.authorId = author.getId();
        this.authorUsername = author.getUsername();
        this.authorFullName = author.getFullName();
        this.date = date;
        this.replies = replies;
    }

    public PostTree(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.body = post.getBody();
        this.authorId = post.getAuthor().getId();
        this.authorUsername = post.getAuthor().getUsername();
        this.authorFullName = post.getAuthor().getFullName();
        this.date = post.getDate();
        this.replies = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<PostTree> getReplies() {
        return replies;
    }

    public void setReplies(List<PostTree> replies) {
        this.replies = replies;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getAuthorUsername() {
        return authorUsername;
    }

    public void setAuthorUsername(String authorUsername) {
        this.authorUsername = authorUsername;
    }

    public String getAuthorFullName() {
        return authorFullName;
    }

    public void setAuthorFullName(String authorFullName) {
        this.authorFullName = authorFullName;
    }
}
