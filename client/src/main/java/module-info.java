module org.rrcat.arpf.ui {
    requires javafx.base;
    requires javafx.graphics;
    requires org.rrcat.arpf.dto;
    requires org.jetbrains.annotations;
    requires okhttp3;
    requires com.google.gson;
    requires retrofit2.converter.jackson;
    requires retrofit2;
    requires javafx.fxml;
    requires com.jfoenix;
    requires com.gluonhq.charm.glisten;
    requires java.desktop;
    requires javax.inject;
    exports org.rrcat.arpf.ui;
}