module org.example.uas_pbo_fr {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.sql;

    opens org.example.uas_pbo_fr to javafx.fxml;
    exports org.example.uas_pbo_fr;
}