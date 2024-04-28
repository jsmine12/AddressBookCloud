package com.AddressBookClient.Admin;
import com.AddressBookClient.Utils.HttpSendUtils.HttpUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import com.AddressBookClient.User.LoginController;
import com.AddressBookClient.Utils.*;
public class ModifyPasswordOfAccountController {
    @FXML
    private TextField previousPasswordTextField;
    @FXML
    private PasswordField modifyPasswordTextField;
    @FXML
    private PasswordField confirmPasswordTextField;
    @FXML
    private void handleModifyPasswordButtonClick(ActionEvent event) {
        String previousPassword=previousPasswordTextField.getText();
        String modifyPassword=modifyPasswordTextField.getText();
        String confirmPassword=confirmPasswordTextField.getText();
        String AdminPassword=LoginController.password;
        if(previousPassword.isEmpty()){
            new CreateAlert("原密码不能为空");
        }
        else if(modifyPassword.isEmpty()){
            new CreateAlert("新密码不能为空");
        }
        else if(confirmPassword.isEmpty()){
            new CreateAlert("请再次输出新密码");
        }
        else if(!modifyPassword.equals(confirmPassword)){
            new CreateAlert("新密码不一致");
        }
        else if(AdminPassword==null){
            new CreateAlert("服务器异常");
        }
        else if(!previousPassword.equals(AdminPassword)){//previous password is wrong
            new CreateAlert("原密码错误");
        }
        else{
            try {
                HttpUtils.updateAdminPasswordOfAccount(modifyPassword);
                new CreateTips("修改成功");
            } catch (Exception e) {
                new CreateAlert(HttpUtils.errMsg);
            }
        }
    }
}
