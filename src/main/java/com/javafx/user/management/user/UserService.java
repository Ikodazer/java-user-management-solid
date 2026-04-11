package com.javafx.user.management.user;

import com.javafx.user.management.security.PasswordHasher;

import java.util.Optional;

public class UserService {
    private final UserRepository userRepository;
    private final PasswordHasher passwordHasher;

    public UserService(UserRepository userRepository, PasswordHasher passwordHasher) {
        this.userRepository = userRepository;
        this.passwordHasher = passwordHasher;
    }

    private String normalizeUsername(String username) {
        return username.trim().toLowerCase();
    }

    private void validateUsername(String username) {
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("Username cannot be empty");
        }
    }

    private void validatePassword(String password) {
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }
    }

    private void validateEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email cannot be empty");
        }
    }

    public void register(String username, String password, String email) {
        validateUsername(username);
        validateEmail(email);
        validatePassword(password);

        username = normalizeUsername(username);

        Optional<User> existingUser = userRepository.findByUsername(username);
        if (existingUser.isPresent()) {
            throw new IllegalArgumentException("Username already exists");
        }

        String hashedPassword = passwordHasher.hash(password);

        User newUser = new User(username, hashedPassword, email);
        userRepository.save(newUser);
    }

    public User login(String username, String password) {
        validateUsername(username);
        validatePassword(password);

        username = normalizeUsername(username);

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        boolean validPassword = passwordHasher.verify(password, user.getPassword());
        if (!validPassword) {
            throw new IllegalArgumentException("Incorrect password");
        }

        return user;
    }

    public void update(int id, String username, String password, String email) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        validateUsername(username);
        validateEmail(email);

        username = normalizeUsername(username);

        Optional<User> existingUser = userRepository.findByUsername(username);

        if (existingUser.isPresent() && existingUser.get().getId() != id) {
            throw new IllegalArgumentException("Username already taken");
        }

        user.setUsername(username);
        user.setEmail(email);

        boolean wantsToChangePassword = password != null;
        if (wantsToChangePassword) {
            validatePassword(password);

            String hashedPassword = passwordHasher.hash(password);
            user.setPassword(hashedPassword);
        }

        userRepository.update(user);
    }

    public void delete(int id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        userRepository.delete(user);
    }
}