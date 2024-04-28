package com.AddressBookClient.Utils;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
public class CreateModelDialog {
    public CreateModelDialog(Scene scene,String msg){
        Stage stage = new Stage();
        //The title should be set before setting the scene; otherwise, the scene won't take effect."
        stage.setTitle(msg);
        stage.setScene(scene);
        Image image=new Image(getClass().getResourceAsStream("/com/AddressBookClient/IMG/33.png"));
        stage.getIcons().add(image);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.showAndWait();
    }
}
