module com.example.brief3_mutuellecentralisee {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires java.sql;
    requires jbcrypt;


    opens com.example.brief3_mutuellecentralisee to javafx.fxml, com.google.gson;
    opens com.example.brief3_mutuellecentralisee.helpers to javafx.fxml, com.google.gson;
    opens com.example.brief3_mutuellecentralisee.models to javafx.fxml, com.google.gson;

    exports com.example.brief3_mutuellecentralisee;
    exports com.example.brief3_mutuellecentralisee.helpers;
    exports com.example.brief3_mutuellecentralisee.models;
}