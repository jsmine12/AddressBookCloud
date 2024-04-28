package com.AddressBookClient.Admin;
import com.AddressBookClient.Utils.HttpSendUtils.HttpUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import com.AddressBookClient.Utils.*;
import com.AddressBookClient.pojo.*;
public class AdminDeleteContactController {
    @FXML
    private TextField idTextField;
    @FXML
    private void handleDeleteContactButtonClick(ActionEvent event){
        String Account = idTextField.getText();
        if(Account.isEmpty()){
            new CreateAlert("请输入账号");
        }
        else {
            try{
                int cnt= HttpUtils.isExistAccount(Account);//check account whether correct
                if(cnt==0){
                    new CreateAlert("请输入正确的账号");
                }
                else{
                    HttpUtils.deleteRowDataByAccount(Account);
                    new CreateTips("销户成功");
                    User o=new User();
                    o.setAccount(Account);
                    AdminHomePageController.tableData.remove(o);
                    idTextField.setText("");
                }
            }
            catch (Exception e){
                new CreateAlert(HttpUtils.errMsg);
            }
        }
    }
    @FXML
    void handleCloseDialogButtonClick(ActionEvent event){
        Stage stage = (Stage) ((javafx.scene.control.Button) event.getSource()).getScene().getWindow();
        stage.close();
    }
}
