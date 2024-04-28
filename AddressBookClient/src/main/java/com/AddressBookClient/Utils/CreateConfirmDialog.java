package com.AddressBookClient.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.Window;
public class CreateConfirmDialog {
    public CreateConfirmDialog(ActionEvent event){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("确认对话框");
        alert.setHeaderText("是否要重新登录？");
        alert.setContentText("这是一条确认消息。");
        Window window = alert.getDialogPane().getScene().getWindow();
        window.setOnCloseRequest(e -> window.hide());
        Stage talkStage = (Stage) alert.getDialogPane().getScene().getWindow();
        talkStage.getIcons().add(new Image(getClass().getResourceAsStream("/com/AddressBookClient/IMG/chat.png")));
        // 设置按钮文本
        ButtonType okButton = new ButtonType("是");
        ButtonType cancelButton = new ButtonType("否");
        alert.getButtonTypes().setAll(okButton, cancelButton);
        alert.getDialogPane().getStylesheets().add(getClass().getResource("/com/AddressBookClient/User/CSS/ClearDefaultStyle.css").toExternalForm());
        // 显示对话框并等待用户选择
        alert.showAndWait().ifPresent(buttonType -> {
            if (buttonType == okButton) {
                // 在这里执行确认操作
                try{
                    Parent LoginRoot= FXMLLoader.load(getClass().getResource("/com/AddressBookClient/User/FXML/Login.fxml"));
                    Scene LoginScene = new Scene(LoginRoot);
                    // 获取当前 Stage，设置场景为主界面
                    Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
                    stage.setScene(LoginScene);
                    stage.setX(ScreenSize.centerX);
                    stage.setY(ScreenSize.centerY);
                    stage.setTitle("登录");
                }catch (Exception e){
                    e.printStackTrace();
                }
            } else if (buttonType == cancelButton) {
                // 在这里执行取消操作
                Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
                stage.close();
            }
        });
    }
}
