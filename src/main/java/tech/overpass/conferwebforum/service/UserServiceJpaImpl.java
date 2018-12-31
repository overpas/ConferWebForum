package tech.overpass.conferwebforum.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import tech.overpass.conferwebforum.model.db.User;
import tech.overpass.conferwebforum.model.other.UserAndNumberOfRecentPosts;
import tech.overpass.conferwebforum.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Primary
public class UserServiceJpaImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostService postService;

    @Override
    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return this.userRepository.findOne(id);
    }

    @Override
    public User findByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

    @Override
    public User findByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    @Override
    public List<User> findMostActiveUsers() {
        List<User> allUsers = this.userRepository.findAll();
        List<UserAndNumberOfRecentPosts> usersAndNumbersOfRecentPosts = new ArrayList<>();
        for (User user : allUsers) {
            int numberOfPosts = this.postService.findByAuthor(user).size();
            usersAndNumbersOfRecentPosts.add(new UserAndNumberOfRecentPosts(user, numberOfPosts));
        }
        usersAndNumbersOfRecentPosts.sort((o1, o2) -> o2.getNumberOfRecentPosts() - o1.getNumberOfRecentPosts());
        List<User> sortedUsers = new ArrayList<>();
        for (UserAndNumberOfRecentPosts uanorp : usersAndNumbersOfRecentPosts) {
            sortedUsers.add(uanorp.getUser());
        }
        return sortedUsers;
    }

    @Override
    public List<User> findAllExcludingPosts() {
        List<User> allUsers = userRepository.findAll();
        allUsers.forEach(user -> {
            user.setPosts(null);
        });
        return allUsers;
    }

    @Override
    public User create(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public User edit(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        this.userRepository.delete(id);
    }

    @Override
    public User getCurrentUser() {
        Object currentUserDetailsObj = SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        UserDetails currentUserDetails = (UserDetails) currentUserDetailsObj;
        String userEmail = currentUserDetails.getUsername();
        return findByEmail(userEmail);
    }

}
