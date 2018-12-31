package tech.overpass.conferwebforum.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import tech.overpass.conferwebforum.model.db.Post;
import tech.overpass.conferwebforum.model.db.User;
import tech.overpass.conferwebforum.model.other.PostAndNumberOfReplies;
import tech.overpass.conferwebforum.repository.PostRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Primary
public class PostServiceJpaImpl implements PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostServiceJpaImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public List<Post> findAll() {
        return this.postRepository.findAll();
    }

    @Override
    public List<Post> findLatest(int quantity) {
        return this.postRepository.findLatestPosts(new PageRequest(0, quantity));
    }

    @Override
    public List<Post> findMostPopular(long limit) {
        List<Post> allRealPosts = this.postRepository.findOnlyRealPosts();
        List<PostAndNumberOfReplies> postsAndNumbersOfReplies = new ArrayList<>();
        for (Post post : allRealPosts) {
            int numberOfReplies = this.postRepository.findAllReplies(post.getId()).size();
            postsAndNumbersOfReplies.add(new PostAndNumberOfReplies(post, numberOfReplies));
        }
        return postsAndNumbersOfReplies
                .stream()
                .sorted((o1, o2) -> o2.getNumberOfReplies() - o1.getNumberOfReplies())
                .map(PostAndNumberOfReplies::getPost)
                .limit(limit)
                .collect(Collectors.toList());
    }

    @Override
    public List<Post> findAllReplies(Long id) {
        return this.postRepository.findAllReplies(id);
    }

    @Override
    public List<Post> findByAuthor(User author) {
        return this.postRepository.findByAuthor(author);
    }

    @Override
    public Post findById(Long id) {
        return this.postRepository.findOne(id);
    }

    @Override
    public Post create(Post post) {
        return this.postRepository.save(post);
    }

    @Override
    public Post edit(Post post) {
        return this.postRepository.save(post);
    }

    @Override
    public void deleteById(Long id) {
        this.postRepository.delete(id);
    }

}