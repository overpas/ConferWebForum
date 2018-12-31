package tech.overpass.conferwebforum.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tech.overpass.conferwebforum.model.db.Post;
import tech.overpass.conferwebforum.model.db.User;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class ApiPostRepositoryImpl implements ApiPostRepository {

    private final EntityManager entityManager;

    @Autowired
    public ApiPostRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Post> findPagedLatest(int offset, int limit) {
        String query = "select p.*, u.id as uid, u.email, u.full_name, u.password_hash, u.username from posts p " +
                "inner join users u on p.author_id = u.id " +
                "where p.in_reply_to is null order by p.date desc";
        Query nativeQuery = entityManager.createNativeQuery(query);
        //Paging
        nativeQuery.setFirstResult(offset);
        nativeQuery.setMaxResults(limit);
        final List<Object[]> resultList = nativeQuery.getResultList();
        List<Post> posts = new ArrayList<>();
        resultList.forEach(objects -> posts.add(
                new Post(
                        Long.valueOf(((BigInteger) objects[0]).toString()),
                        (String) objects[4],
                        (String) objects[1],
                        new User(
                                Long.valueOf(((BigInteger) objects[6]).toString()),
                                (String) objects[10],
                                (String) objects[8],
                                (String) objects[7],
                                (String) objects[9]
                        ),
                        new Date(((Timestamp) objects[2]).getTime()),
                        objects[3] == null ? null : Long.valueOf(((BigInteger) objects[3]).toString())
                )
        ));
        return posts;
    }

}
