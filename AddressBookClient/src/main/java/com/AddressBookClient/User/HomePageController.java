package com.AddressBookClient.User;
import com.AddressBookClient.Utils.HttpSendUtils.HttpUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import com.AddressBookClient.Utils.*;
import com.AddressBookClient.pojo.*;
public class HomePageController {
    public  static  ObservableList<Person> tableData;
    @FXML
    private MyTableView<Person> tbView;
    @FXML
    private TextField SearchTextField;
    @FXML
    private Button backButton;
    public void initialize(){
        try{
        System.out.println(LoginController.transmit);
        tableData=FXCollections.observableArrayList();
        tableData.addAll(HttpUtils.getResultTableQueryAllContacts(LoginController.transmit));
        tbView.setItems(tableData);
        }
        catch (Exception e){
            new CreateAlert(HttpUtils.errMsg);
        }
    }
    //  1649453887
    @FXML
    private void handleSearchButtonClick(ActionEvent event) {
        // 在这里编写按钮点击事件的处理逻辑
       try{
           backButton.setVisible(true);
           String value=SearchTextField.getText();
//           List<Person> searchResult=HttpUtils.getResultTableQueryAllDataSuitConfident(SearchTextField.getText(),LoginController.transmit);
           ObservableList<Person> tableDataAfterSearch =FXCollections.observableArrayList();
           for(int i=0;i<tableData.size();i++){
               Person o=tableData.get(i);
               if(o.getName().indexOf(value)!=-1){
                    tableDataAfterSearch.add(o);
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
    private void handleAddButtonClick(ActionEvent event) throws Exception {
        // 在这里编写按钮点击事件的处理逻辑
        Parent AddButtonRoot= FXMLLoader.load(getClass().getResource("/com/AddressBookClient/User/FXML/AddContactPage.fxml"));
        Scene AddButtonScene = new Scene(AddButtonRoot);
        new CreateModelDialog(AddButtonScene,"增加联系人");
    }
    @FXML
    private void handleChangeButtonClick(ActionEvent event) throws Exception{
        // 在这里编写按钮点击事件的处理逻辑
        new CreateTips("不想修改的信息可以不填，编号不能为空");
        Parent handleChangeRoot= FXMLLoader.load(getClass().getResource("/com/AddressBookClient/User/FXML/ModifyContactInformation.fxml"));
        Scene handleChangeScene = new Scene(handleChangeRoot);
        new CreateModelDialog(handleChangeScene,"修改联系人信息");
    }
    @FXML
    private void handleDeleteButtonClick(ActionEvent event) throws Exception {
        // 在这里编写按钮点击事件的处理逻辑
        Parent DeleteButtonRoot= FXMLLoader.load(getClass().getResource("/com/AddressBookClient/User/FXML/DeleteContact.fxml"));
        Scene DeleteButtonScene = new Scene(DeleteButtonRoot);
        new CreateModelDialog(DeleteButtonScene,"删除联系人");
    }
    @FXML
    private void handleModifyPasswordOfAccountButtonClick(ActionEvent event) throws Exception {
        Parent ModifyPasswordOfAccountButtonRoot= FXMLLoader.load(getClass().getResource("/com/AddressBookClient/User/FXML/ModifyPasswordOfAccount.fxml"));
        Scene ModifyPasswordOfAccountButtonScene = new Scene(ModifyPasswordOfAccountButtonRoot);
        new CreateModelDialog(ModifyPasswordOfAccountButtonScene,"修改密码");
    }
    @FXML
    private void handleBackOperateButtonClick(ActionEvent event) throws Exception{
        backButton.setVisible(false);
        tbView.setItems(tableData);
    }
}
