package tech.overpass.conferwebforum.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.overpass.conferwebforum.model.db.Post;
import tech.overpass.conferwebforum.model.db.User;
import tech.overpass.conferwebforum.model.other.PostAndNumberOfReplies;
import tech.overpass.conferwebforum.model.other.PostTree;
import tech.overpass.conferwebforum.repository.ApiPostRepository;
import tech.overpass.conferwebforum.repository.PostRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApiPostServiceImpl implements ApiPostService {

    private final ApiPostRepository apiPostRepository;
    private final PostRepository postRepository;

    @Autowired
    public ApiPostServiceImpl(ApiPostRepository apiPostRepository, PostRepository postRepository) {
        this.apiPostRepository = apiPostRepository;
        this.postRepository = postRepository;
    }

    public List<Post> findPagedLatest(int offset, int limit) {
        return apiPostRepository.findPagedLatest(offset, limit);
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
                .map(postAndNumberOfReplies -> {
                    Post post = postAndNumberOfReplies.getPost();
                    User author = post.getAuthor();
                    author.setPosts(null);
                    post.setAuthor(author);
                    return post;
                })
                .limit(limit)
                .collect(Collectors.toList());
    }

    @Override
    public PostTree findById(long id) {
        Post originalPost = postRepository.findOne(id);
        PostTree postTree = new PostTree(originalPost);
        List<Post> replies = postRepository.findAllReplies(postTree.getId());
        List<PostTree> treeReplies = new ArrayList<>();
        replies.forEach((post -> {
            treeReplies.add(findById(post.getId()));
        }));
        postTree.setReplies(treeReplies);
        return postTree;
    }

    @Override
    public Post create(Post post) {
        return postRepository.save(post);
    }
}
