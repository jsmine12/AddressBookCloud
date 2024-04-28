package com.AddressBookClient.Utils;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
public class CreateTips {
        public CreateTips(String context){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.getButtonTypes().clear();
            ButtonType customButtonType = new ButtonType("我知道了");
            alert.getButtonTypes().add(customButtonType);
            alert.getDialogPane().getStylesheets().add(getClass().getResource("/com/AddressBookClient/User/CSS/ClearDefaultStyle.css").toExternalForm());
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/com/AddressBookClient/IMG/chat.png")));
            // 设置提示框标题
            alert.setTitle("提示");
            // 设置提示框头部文本
            alert.setHeaderText("这是一个提示");
            // 设置提示框内容文本
            alert.setContentText(context);
            // 显示提示框并等待用户响应
            alert.showAndWait();
        }
}
