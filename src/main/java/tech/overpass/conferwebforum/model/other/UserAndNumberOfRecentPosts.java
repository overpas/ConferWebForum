package tech.overpass.conferwebforum.model.other;

import tech.overpass.conferwebforum.model.db.User;

public class UserAndNumberOfRecentPosts {

    private User user;
    private int numberOfRecentPosts;

    public UserAndNumberOfRecentPosts(User user, int numberOfRecentPosts) {
        this.user = user;
        this.numberOfRecentPosts = numberOfRecentPosts;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getNumberOfRecentPosts() {
        return numberOfRecentPosts;
    }

    public void setNumberOfRecentPosts(int numberOfRecentPosts) {
        this.numberOfRecentPosts = numberOfRecentPosts;
    }
}
