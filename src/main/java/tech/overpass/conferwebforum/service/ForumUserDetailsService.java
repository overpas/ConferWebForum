package tech.overpass.conferwebforum.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import tech.overpass.conferwebforum.model.db.User;
import tech.overpass.conferwebforum.model.dto.UserRegistrationDto;

public interface ForumUserDetailsService extends UserDetailsService {
    User findByEmail(String email);

    User save(UserRegistrationDto registration);
}
