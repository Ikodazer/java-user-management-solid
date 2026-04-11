package com.javafx.user.management.user;

import java.util.Collection;
import java.util.Optional;

public interface UserRepository {
    Optional<User> findByUsername(String username);
    Optional<User> findById(int id);

    Collection<User> findAllActiveUsers();
    void save(User user);
    void deactivate(User user);
    void update(User user);
}
