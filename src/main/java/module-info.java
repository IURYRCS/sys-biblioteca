module com.example.sysbiblioteca {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires jdk.compiler;
    requires java.naming;

    requires jakarta.persistence;
    requires org.hibernate.orm.core;


    opens com.example.sysbiblioteca to javafx.fxml;
    exports com.example.sysbiblioteca;

    exports com.example.sysbiblioteca.model.entity;
    opens com.example.sysbiblioteca.model.entity to javafx.fxml;
    exports com.example.sysbiblioteca.controller;
    opens com.example.sysbiblioteca.controller to javafx.fxml;

}