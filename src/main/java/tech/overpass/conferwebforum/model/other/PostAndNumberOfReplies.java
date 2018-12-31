package tech.overpass.conferwebforum.model.other;


import tech.overpass.conferwebforum.model.db.Post;

public class PostAndNumberOfReplies {

    private Post post;
    private int numberOfReplies;

    public PostAndNumberOfReplies(Post post, int numberOfReplies) {
        this.post = post;
        this.numberOfReplies = numberOfReplies;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public int getNumberOfReplies() {
        return numberOfReplies;
    }

    public void setNumberOfReplies(int numberOfReplies) {
        this.numberOfReplies = numberOfReplies;
    }
}
