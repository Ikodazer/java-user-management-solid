package com.javafx.user.management.app;

import com.javafx.user.management.database.Database;
import com.javafx.user.management.security.PasswordHasher;
import com.javafx.user.management.user.MemoryUserRepository;
import com.javafx.user.management.user.UserService;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Database database = new Database();
        MemoryUserRepository repository = new MemoryUserRepository(database);
        PasswordHasher passwordHasher = new PasswordHasher();
        UserService userService = new UserService(repository, passwordHasher);
        ViewLoader viewLoader = new ViewLoader(userService);

        viewLoader.show(stage, "/com/javafx/user/management/views/register.fxml");
    }
}
