package com.AddressBookClient.User;
import com.AddressBookClient.Utils.HttpSendUtils.HttpUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import com.AddressBookClient.Utils.*;
import java.util.regex.Pattern;
public class SignPageController {
    private static String[]  ImagesCollections=new String[]{"7yh6u","XIDI4","ZAqhg"};;
    @FXML
    private ImageView IdentifyImage;
    @FXML
    private TextField AccountTextField;
    @FXML
    private TextField PasswordField;
    @FXML
    private TextField VerifyTextField;
    @FXML
    private void handleSubmitButtonClick(ActionEvent event) throws Exception {
        String Account= AccountTextField.getText();
        String Password=PasswordField.getText();
        String VerifyCode=VerifyTextField.getText();
        int at=(int)getImagePathAndAt()[0];
        String AccessVerifyCode=ImagesCollections[at-1];
        if(Account.isEmpty()){
            new CreateAlert("账号不能为空");
        }
        else if(Password.isEmpty()){
            new CreateAlert("密码不能为空");
        }
        else if(VerifyCode.isEmpty()){
            new CreateAlert("验证码不能为空");
        }
        else if(!VerifyCode.equals(AccessVerifyCode)){
            new CreateAlert("验证码错误");
        }
        else {
            if(Pattern.matches("[1-9]\\d{5,}",Account)==false){
                new CreateAlert("账号不合格至少要为6位数字，开头不能为0");
            }
            else if(Pattern.matches("[0-9,a-z,A-Z,.,@]{5,}",Password)==false){
                new CreateAlert("密码至少为5位字符，只能为数字和字母以及.和@");
            }
            else{
                try{
                    int cnt=HttpUtils.isExistAccount(Account);
                    if(cnt==0){
                        HttpUtils.addAccountAndPasswordToDataBase(Account,Password);
                        new CreateTips("注册完成");
                    }
                    else{
                        new CreateAlert("账号已存在");
                    }
                }
                catch (Exception e){
                    new CreateAlert(HttpUtils.errMsg);
                }
            }
        }
    }
    @FXML
    private void handleBackButtonClick(ActionEvent event) throws Exception {
        // 在这里编写按钮点击事件的处理逻辑
        Parent LoginRoot= FXMLLoader.load(getClass().getResource("/com/AddressBookClient/User/FXML/Login.fxml"));
        Scene LoginScene = new Scene(LoginRoot);

        // 获取当前 Stage，设置场景为登录界面
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(LoginScene);
        stage.setTitle("登录");
    }
    private Object[] getImagePathAndAt(){
        Image image=IdentifyImage.getImage();
        String imageUrl = image.getUrl();
//        System.out.println(imageUrl);
        String[] strArr=imageUrl.split("/");
        StringBuilder ValuePath = new StringBuilder();
        for(int i=0;i<strArr.length-2;i++){
            if(i==0)ValuePath.append(strArr[i]);
            else ValuePath.append("/"+strArr[i]);
        }
        return new Object[]{strArr[strArr.length-1].charAt(0)-'0',ValuePath};
    }
    @FXML
    private void handleMouseClickToSwitchImages(MouseEvent event) throws Exception{
        Object[] value=getImagePathAndAt();
        int at=(int)value[0];
        at++;
        if(at==4)at=1;
        String imagePath=new String((StringBuilder) value[1])+"/"+"IMG/"+at+".png";
        System.out.println(imagePath);
        Image nextImage = new Image(imagePath);
        IdentifyImage.setImage(nextImage);
    }
    @FXML
    public void initialize() {
    }
}
