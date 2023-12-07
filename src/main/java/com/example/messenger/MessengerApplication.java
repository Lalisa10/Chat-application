package com.example.messenger;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class MessengerApplication extends Application {

    public static enum Status {
        RUNNING,
        EXIT
    }
    private static Status status = Status.RUNNING;

    public static Status getStatus() {
        return status;
    }

    public static void setStatus(Status st) {
        status = st;
    }


    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MessengerApplication.class.getResource("/view/hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.setTitle("Messenger");
        stage.show();
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                status = Status.EXIT;
            }
        });
    }

    public static void main(String[] args) {
        launch();
    }
}