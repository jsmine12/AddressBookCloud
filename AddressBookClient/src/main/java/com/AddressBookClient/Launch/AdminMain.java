package com.AddressBookClient.Launch;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.AddressBookClient.Utils.*;
public class AdminMain extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/com/AddressBookClient//Admin/FXML/AdminHomePagelayout.fxml"));
        primaryStage.setTitle("登录");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.setX(ScreenSize.centerX);
        primaryStage.setY(ScreenSize.centerY);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}