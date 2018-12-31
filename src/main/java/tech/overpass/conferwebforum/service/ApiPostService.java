package tech.overpass.conferwebforum.service;


import tech.overpass.conferwebforum.model.db.Post;
import tech.overpass.conferwebforum.model.other.PostTree;

import java.util.List;

public interface ApiPostService {
    List<Post> findPagedLatest(int offset, int limit);

    List<Post> findMostPopular(long limit);

    PostTree findById(long id);

    Post create(Post post);
}
