package com.javafx.user.management.user;

import java.util.Collection;
import java.util.Optional;

public interface UserRepository {
    Optional<User> findByUsername(String username);
    Optional<User> findById(int id);

    Collection<User> findAllUsers();
    void save(User user);
    void delete(User user);
    void update(User user);
}
