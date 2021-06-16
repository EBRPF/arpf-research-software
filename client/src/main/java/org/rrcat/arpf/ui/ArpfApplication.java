package org.rrcat.arpf.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ArpfApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/layouts/navigation.fxml"));

        Scene scene = new Scene(root, 300, 275);

        primaryStage.setTitle("Arpf");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
