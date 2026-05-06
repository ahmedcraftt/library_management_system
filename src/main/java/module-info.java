module com.example.library_management_system {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.library_management_system to javafx.fxml;
    exports com.example.library_management_system;
}