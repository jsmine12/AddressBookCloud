package com.AddressBookClient.Admin;

import com.AddressBookClient.Utils.HttpSendUtils.HttpUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import com.AddressBookClient.Utils.*;
import com.AddressBookClient.pojo.*;
import java.util.List;

public class AdminHomePageController {
    public  static ObservableList<User> tableData;
    @FXML
    private MyTableView<User> tbView;
    @FXML
    private Button backButton;
    @FXML
    private TextField SearchTextField;
    public void initialize(){
        try {
            tableData= FXCollections.observableArrayList();
            tableData.addAll(HttpUtils.getResultTableQueryAllUsers());
            tbView.setItems(tableData);
        }
        catch (Exception e){
            new CreateAlert(HttpUtils.errMsg);
        }
    }
    @FXML
    private void handleBackOperateButtonClick(ActionEvent event) throws Exception{
        backButton.setVisible(false);
        tbView.setItems(tableData);
    }
    @FXML
    private void handleModifyPasswordOfAccountButtonClick(ActionEvent event) throws Exception {
        Parent ModifyPasswordOfAccountButtonRoot= FXMLLoader.load(getClass().getResource("/com/AddressBookClient/Admin/FXML/ModifyPasswordOfAccount.fxml"));
        Scene ModifyPasswordOfAccountButtonScene = new Scene(ModifyPasswordOfAccountButtonRoot);
        new CreateModelDialog(ModifyPasswordOfAccountButtonScene,"修改密码");
    }
    @FXML
    public  void handleSearchButtonClick(ActionEvent event){
        try{
            backButton.setVisible(true);
            String value=SearchTextField.getText();
            ObservableList<User> tableDataAfterSearch =FXCollections.observableArrayList();
            for(int i=0;i<tableData.size();i++){
                if(tableData.get(i).getAccount().equals(value)){
                    tableDataAfterSearch.add(tableData.get(i));
                }
            }
            tbView.setItems(tableDataAfterSearch);
        }
        catch (Exception e){
            new CreateAlert(HttpUtils.errMsg);
        }
    }
    @FXML
    private void handleExitButtonClick(ActionEvent event) {
        // 在这里编写按钮点击事件的处理逻辑
        new CreateConfirmDialog(event);
    }
    @FXML
    private void handleDeleteButtonClick(ActionEvent event) throws Exception {
        // 在这里编写按钮点击事件的处理逻辑
        Parent DeleteButtonRoot= FXMLLoader.load(getClass().getResource("/com/AddressBookClient/Admin/FXML/DeleteContact.fxml"));
        Scene DeleteButtonScene = new Scene(DeleteButtonRoot);
        new CreateModelDialog(DeleteButtonScene,"删除用户");
    }
}
