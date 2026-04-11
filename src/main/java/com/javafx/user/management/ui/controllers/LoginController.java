package com.javafx.user.management.ui.controllers;

import com.javafx.user.management.app.ViewLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    private final ViewLoader viewLoader;

    public LoginController(ViewLoader viewLoader) {
        this.viewLoader = viewLoader;
    }

    @FXML
    public void login(ActionEvent actionEvent) {
    }

    @FXML
    public void changeToRegister(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        viewLoader.show(stage, "/com/javafx/user/management/views/register.fxml");
    }
}
