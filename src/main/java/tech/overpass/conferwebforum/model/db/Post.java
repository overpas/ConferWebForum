package tech.overpass.conferwebforum.model.db;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 300)
    private String title;

    @Lob
    @Column(nullable = false)
    private String body;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User author;

    @Column(nullable = false)
    private Date date = new Date();

    @Column()
    private Long inReplyTo;

    public Post() {
    }

    public Post(Long id, String title, String body, User author) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.author = author;
    }

    public Post(String title, String body, User author, Date date, Long inReplyTo) {
        this.date = date;
        this.body = body;
        this.author = author;
        this.title = title;
        this.inReplyTo = inReplyTo;
    }

    public Post(Long id, String title, String body, User author, Date date, Long inReplyTo) {
        this(title, body, author, date, inReplyTo);
        this.id = id;
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

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getInReplyTo() {
        return inReplyTo;
    }

    public void setInReplyTo(Long inReplyTo) {
        this.inReplyTo = inReplyTo;
    }

    @Override
    public String toString() {
        return "Post[id = " + id + ", title = " + title + ", author = " + author + ", date = "
                + date + "]";
    }
}
