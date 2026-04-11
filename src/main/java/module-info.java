module com.javafx.user.management {
    requires javafx.controls;
    requires javafx.fxml;
    requires jbcrypt;


    opens com.javafx.user.management.ui.controllers to javafx.fxml;
    exports com.javafx.user.management.app;
}