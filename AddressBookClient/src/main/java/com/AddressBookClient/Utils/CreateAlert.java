package com.AddressBookClient.Utils;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
public class CreateAlert  {
    public CreateAlert(String context){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.getButtonTypes().clear();
        ButtonType customButtonType = new ButtonType("我知道了");
        alert.getButtonTypes().add(customButtonType);
        alert.getDialogPane().getStylesheets().add(getClass().getResource("/com/AddressBookClient/User/CSS/ClearDefaultStyle.css").toExternalForm());
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/com/AddressBookClient/IMG/warning.png")));
        // 设置警告框标题
        alert.setTitle("警告");
        // 设置警告框头部文本
        alert.setHeaderText("这是一个警告");
        // 设置警告框内容文本
        alert.setContentText(context);
        // 显示警告框并等待用户响应
        alert.showAndWait();
    }
}
