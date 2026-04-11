package com.javafx.user.management.ui.controllers;

import com.javafx.user.management.app.ViewLoader;
import com.javafx.user.management.user.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class RegisterController {
    private final UserService service;
    private final ViewLoader viewLoader;

    @FXML
    private TextField username;
    @FXML
    private TextField email;
    @FXML
    private TextField password;
    @FXML
    private Label result;

    public RegisterController(UserService service, ViewLoader viewLoader) {
        this.service = service;
        this.viewLoader = viewLoader;
    }

    @FXML
    public void register(ActionEvent actionEvent) {
        try {
            service.register(username.getText(), password.getText(), email.getText());
            result.setText("User registered successfully!");
        } catch (Exception e) {
            result.setText(e.getMessage());
        }
    }

    @FXML
    public void changeToLogin(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        viewLoader.show(stage, "/com/javafx/user/management/views/login.fxml");
    }
}
