package tech.overpass.conferwebforum.service;

import tech.overpass.conferwebforum.model.db.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    User findById(Long id);
    User findByUsername(String username);
    User findByEmail(String email);
    List<User> findMostActiveUsers();
    List<User> findAllExcludingPosts();
    User create(User user);
    User edit(User user);
    void deleteById(Long id);
    User getCurrentUser();
}
