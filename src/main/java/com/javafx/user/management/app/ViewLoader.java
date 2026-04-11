package com.javafx.user.management.app;

import com.javafx.user.management.ui.controllers.LoginController;
import com.javafx.user.management.ui.controllers.RegisterController;
import com.javafx.user.management.user.UserService;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

public class ViewLoader {
    private final UserService userService;

    public ViewLoader(UserService userService) {
        this.userService = userService;
    }

    public Parent load(String viewPath) throws IOException {
        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource(viewPath)));
        loader.setControllerFactory(controllerClass -> {
            if (controllerClass == RegisterController.class) {
                return new RegisterController(userService, this);
            }
            if (controllerClass == LoginController.class) {
                return new LoginController(this);
            }

            try {
                return controllerClass.getDeclaredConstructor().newInstance();
            } catch (InstantiationException
                     | IllegalAccessException
                     | InvocationTargetException
                     | NoSuchMethodException e) {
                throw new IllegalStateException("Unable to create controller: " + controllerClass.getName(), e);
            }
        });

        return loader.load();
    }

    public void show(Stage stage, String viewPath) throws IOException {
        Scene scene = new Scene(load(viewPath));
        stage.setScene(scene);
        stage.show();
    }
}
