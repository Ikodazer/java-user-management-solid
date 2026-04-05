module user.management.solid.javausermanagementsolid {
    requires javafx.controls;
    requires javafx.fxml;


    opens user.management.solid.javausermanagementsolid to javafx.fxml;
    exports user.management.solid.javausermanagementsolid;
}