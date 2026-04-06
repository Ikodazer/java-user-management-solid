module user.management.solid.javausermanagementsolid {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.javafx.user.management to javafx.fxml;
    exports com.javafx.user.management;
}