package com.javafx.user.management.user;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class MemoryUserRepository implements UserRepository {
    @Override
    public Optional<User> findByUsername(String username) {
        return Optional.empty();
    }

    @Override
    public Optional<User> findById(int id) {
        return Optional.empty();
    }

    @Override
    public Collection<User> findAllUsers() {
        return List.of();
    }

    @Override
    public void save(User user) {

    }

    @Override
    public void delete(User user) {

    }

    @Override
    public void update(User user) {

    }
}
