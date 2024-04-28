package com.AddressBookClient.User;
import com.AddressBookClient.Utils.HttpSendUtils.HttpUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import com.AddressBookClient.Utils.*;
public class DeleteContactController {
    @FXML
    private TextField idTextField;
    @FXML
    private void handleDeleteContactButtonClick(ActionEvent event){
        String index = idTextField.getText();
        String Account=LoginController.transmit;
        boolean hasSpace=false;
        if(index.isEmpty()){
            new CreateAlert("请输入编号");
        }
        else {
            if(index.indexOf(" ")!=-1)hasSpace=true;
            if(hasSpace){
                new CreateAlert("编号不能包含空格");
            }
            else{
                int at=Integer.parseInt(index);
                boolean exist=false;
                if(at<=HomePageController.tableData.size())exist=true;
                if(!exist){
                    new CreateAlert("请输入正确的编号");
                }
                else{
                    try {
                        HttpUtils.deleteRowDataById(Integer.valueOf(HomePageController.tableData.get(at-1).getId()).toString());
                        new CreateTips("删除成功");
                        HomePageController.tableData.remove(at-1);
                        //adjust previous index of person
                        //After removing an element, the subsequent elements will shift forward
                        //The position that is deleted will be occupied.
                        for(int i=at-1;i<HomePageController.tableData.size();i++){
                            HomePageController.tableData.get(i).setIndex(i+1);
                        }
                        idTextField.setText("");
                    } catch (Exception e) {
                        new CreateAlert(HttpUtils.errMsg);
                    }
                }
            }
        }
    }
    @FXML
     void handleCloseDialogButtonClick(ActionEvent event){
        Stage stage = (Stage) ((javafx.scene.control.Button) event.getSource()).getScene().getWindow();
        stage.close();
    }
}
