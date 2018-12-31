package tech.overpass.conferwebforum.service;

import tech.overpass.conferwebforum.model.db.Post;
import tech.overpass.conferwebforum.model.db.User;

import java.util.List;

public interface PostService {
    List<Post> findAll();

    List<Post> findLatest(int quantity);

    List<Post> findMostPopular(long limit);

    List<Post> findAllReplies(Long id);

    List<Post> findByAuthor(User author);

    Post findById(Long id);

    Post create(Post post);

    Post edit(Post post);

    void deleteById(Long id);
}
