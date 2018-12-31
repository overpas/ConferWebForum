package tech.overpass.conferwebforum.repository;


import tech.overpass.conferwebforum.model.db.Post;

import java.util.List;

public interface ApiPostRepository {
    List<Post> findPagedLatest(int offset, int limit);
}
