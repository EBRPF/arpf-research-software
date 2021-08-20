package org.rrcat.arpf.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ArpfApplication extends Application {
    public static final String APPLICATION_TITLE = "A.R.P.F, Indore (Research Products)";
    public static final int MIN_WIDTH = 1400;
    public static final int MIN_HEIGHT = 800;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/layouts/login.fxml"));

        Scene scene = new Scene(root, MIN_WIDTH, MIN_HEIGHT);
        primaryStage.setMinWidth(MIN_WIDTH);
        primaryStage.setMinHeight(MIN_HEIGHT);
        primaryStage.setWidth(MIN_WIDTH);
        primaryStage.setHeight(MIN_HEIGHT);

        primaryStage.setTitle(APPLICATION_TITLE);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
