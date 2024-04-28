package com.AddressBookClient.User;
import com.AddressBookClient.Utils.HttpSendUtils.HttpUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import com.AddressBookClient.Utils.*;
import java.util.regex.Pattern;
public class LoginController {
    @FXML
    private TextField AccountTextField;
    @FXML
    private PasswordField PasswordTextField;
    @FXML
    private TextField  VisiblePasswordField;
    @FXML
    private CheckBox checkBox1;
    @FXML
    private CheckBox checkBox2;
    public static String transmit;
    public static String password;
    public void setTransmit(String t){
        transmit=t;
    }
    public void setPassword(String p){password=p;};
    //hidden password function
    @FXML
    private void togglePasswordVisibility(ActionEvent event) {
        boolean visible = checkBox1.isSelected();
        PasswordTextField.setVisible(!visible);
        PasswordTextField.setManaged(!visible);
        VisiblePasswordField.setVisible(visible);
        VisiblePasswordField.setManaged(visible);
        if (visible) {
            VisiblePasswordField.setText(PasswordTextField.getText());
            System.out.println(visible);
        } else {
            PasswordTextField.setText(VisiblePasswordField.getText());
            System.out.println(visible);
        }
    }
    @FXML
    private void handleSignUpButtonClick(ActionEvent event) throws Exception {
        Parent SignRoot= FXMLLoader.load(getClass().getResource("/com/AddressBookClient/User/FXML/SignPage.fxml"));
        Scene SignScene = new Scene(SignRoot);
        // 获取当前 Stage，设置场景为主界面
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(SignScene);
        stage.setTitle("注册");
    }
    @FXML
    private void handleLoginButtonClick(ActionEvent event) throws Exception {
        // 在这里编写按钮点击事件的处理逻辑
        String Account=AccountTextField.getText();
        boolean isSelect=checkBox1.isSelected();
        String Password;
        if(isSelect){
            Password=VisiblePasswordField.getText();
        }
        else {
            Password=PasswordTextField.getText();
        }
        if(Account.isEmpty()&&Password.isEmpty()){
            new CreateAlert("账号和密码不能为空");
        }
        else if(Account.isEmpty()){
            new CreateAlert("账号不能为空");
        }
        else if(Password.isEmpty()){
            new CreateAlert("密码不能为空");
        }
        else {
            if(Pattern.matches("[1-9]\\d{5,}",Account)==false){
                new CreateAlert("账号不合格至少要为6位数字，开头不能为0");
            }
            else if(Pattern.matches("[0-9,a-z,A-Z,.,@]{5,}",Password)==false){
                new CreateAlert("密码至少为5位字符，只能为数字和字母以及.和@");
            }
            else {
                if(!checkBox2.isSelected()){
                    try{
                        int statusAccount= HttpUtils.isExistAccount(Account);
                        System.out.println(statusAccount);
                        String rightPassword=HttpUtils.selectPassword(Account);
                        if(statusAccount==1){
                            if(rightPassword.equals(Password)){
                                setTransmit(Account);
                                setPassword(Password);
                                FXMLLoader HomePage=  new FXMLLoader(getClass().getResource("/com/AddressBookClient/User/FXML/HomePageLayout.fxml"));
                                Parent root=HomePage.load();
                                Scene HomePageScene = new Scene(root);
                                // 获取当前 Stage，设置场景为主界面
                                Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
                                stage.setScene(HomePageScene);
                                stage.setTitle("用户主界面");
                                stage.setX(ScreenSize.suitHomePageX);
                                stage.setY(ScreenSize.suitHomepageY);
                            }
                            else {
                                new CreateAlert("密码错误");
                            }
                        }//登陆成功
                        else{
                            new CreateAlert("账号不存在");
                        }
                    }
                    catch (Exception e){
                        new CreateAlert(HttpUtils.errMsg);
                    }
                }//ordinary User Login
                else{
                    try{
                        String AdminPassword=HttpUtils.getPasswordOfAdmin();
                        if(!AdminPassword.equals(Password)||!Account.equals("127001")){
                            new CreateAlert("管理员密码或账号错误,请检查");
                        }
                        else{
                            setTransmit(Account);
                            setPassword(Password);
                            FXMLLoader HomePage=  new FXMLLoader(getClass().getResource("/com/AddressBookClient/Admin/FXML/AdminHomePageLayout.fxml"));
                            Parent root=HomePage.load();
                            Scene HomePageScene = new Scene(root);
                            // 获取当前 Stage，设置场景为主界面
                            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
                            stage.setScene(HomePageScene);
                            stage.setTitle("管理员主界面");
                            stage.setX(ScreenSize.suitHomePageX);
                            stage.setY(ScreenSize.suitHomepageY);
                        }//load admin Home page
                    }
                    catch (Exception e){
                        new CreateAlert(HttpUtils.errMsg);
                    }
                } //Admin Login
            }
        }
    }
    @FXML
    public void initialize() {
    }
}