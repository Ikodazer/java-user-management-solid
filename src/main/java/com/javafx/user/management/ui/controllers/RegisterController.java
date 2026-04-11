package com.javafx.user.management.ui.controllers;

import com.javafx.user.management.database.Database;
import com.javafx.user.management.security.PasswordHasher;
import com.javafx.user.management.user.MemoryUserRepository;
import com.javafx.user.management.user.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class RegisterController {
    public TextField username;
    public TextField email;
    public TextField password;
    public Label result;

    public Database database = new Database();
    public MemoryUserRepository repository = new MemoryUserRepository(database);
    public PasswordHasher passwordHasher = new PasswordHasher();
    public UserService service = new UserService(repository, passwordHasher);

    public void register (ActionEvent actionEvent) {
        try {
            service.register(username.getText(), password.getText(), email.getText());
            result.setText("User registered successfully!");
        } catch (Exception e) {
            result.setText(e.getMessage());
        }
    }

    public void changeToLogin (ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/javafx/user/management/views/login.fxml")));
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
