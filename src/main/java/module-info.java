module org.utl.dsm42d_optik {
    requires javafx.controls;
    requires javafx.fxml;
    //para usar json
    requires gson;
    requires java.sql;
    //para cliente http
    requires unirest.java;
    //para implementar el modelo
    opens org.utl.dsm42d_optik.model to gson;

    opens org.utl.dsm42d_optik to javafx.fxml;
    opens org.utl.dsm42d_optik.controller to javafx.fxml;
    exports org.utl.dsm42d_optik;
    exports org.utl.dsm42d_optik.controller;
}