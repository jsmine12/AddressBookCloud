package com.AddressBookClient.Launch;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import com.AddressBookClient.Utils.ScreenSize;
public class UserMain extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(UserMain.class.getResource("/com/AddressBookClient/User/FXML/Login.fxml"));
        System.out.println(this.getClass().getResource("/com/AddressBookClient/User/FXML/Login.fxml").getFile());
        primaryStage.setTitle("登录");
        Image image=new Image(getClass().getResourceAsStream("/com/AddressBookClient/IMG/33.png"));
        //,32,32,false,false
        primaryStage.getIcons().add(image);
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



