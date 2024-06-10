module com.example.sd_courswork_class_version {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.sd_courswork_class_version to javafx.fxml;
    exports com.example.sd_courswork_class_version;
}