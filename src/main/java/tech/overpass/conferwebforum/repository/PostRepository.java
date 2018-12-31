package tech.overpass.conferwebforum.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tech.overpass.conferwebforum.model.db.Post;
import tech.overpass.conferwebforum.model.db.User;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("SELECT p FROM Post p LEFT JOIN FETCH p.author WHERE p.inReplyTo = null ORDER BY p.date DESC")
    List<Post> findLatestPosts(Pageable pageable);

    @Query("SELECT p FROM Post p LEFT JOIN FETCH p.author WHERE p.inReplyTo = null")
    List<Post> findOnlyRealPosts();

    @Query("SELECT p FROM Post p WHERE p.inReplyTo = :id")
    List<Post> findAllReplies(@Param("id") Long id);

    @Query("SELECT p FROM Post p WHERE p.author = :author ORDER BY p.date DESC")
    List<Post> findByAuthor(@Param("author") User author);

}
