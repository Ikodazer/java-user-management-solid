package com.javafx.user.management.ui.controllers;

import com.javafx.user.management.app.ViewLoader;
import com.javafx.user.management.user.User;
import com.javafx.user.management.user.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    private final UserService service;
    private final ViewLoader viewLoader;

    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Label result;

    public LoginController(UserService service, ViewLoader viewLoader) {
        this.service = service;
        this.viewLoader = viewLoader;
    }

    @FXML
    public void login(ActionEvent actionEvent) {
        try {
            User authenticatedUser = service.login(username.getText(), password.getText());
            result.setText("Welcome, " + authenticatedUser.getUsername() + "!");
        } catch (Exception e) {
            result.setText(e.getMessage());
        }
    }

    @FXML
    public void changeToRegister(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        viewLoader.show(stage, "/com/javafx/user/management/views/register.fxml");
    }
}
