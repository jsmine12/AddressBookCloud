package com.AddressBookClient.User;
import com.AddressBookClient.Utils.HttpSendUtils.HttpUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import com.AddressBookClient.Utils.*;
import com.AddressBookClient.pojo.*;
import java.util.regex.Pattern;
public class AddContactPageController {
    @FXML
    private TextField NameTextField;
    @FXML
    private TextField AgeTextField;
    @FXML
    private TextField TelTextField;
    @FXML
    private RadioButton radioButton1;
    @FXML
    void handleSubmitButtonClick(ActionEvent event){
        String Account=LoginController.transmit;
        String name=NameTextField.getText();
        String gender;
        if(radioButton1.isSelected()){
            gender="男";
        }
        else gender="女";
        String _age=AgeTextField.getText();
        String tel=TelTextField.getText();
        if(tel.isEmpty()||name.isEmpty()||_age.isEmpty()){
            new CreateAlert("信息不完备，不能出现空的输入");
        }
        else {
            try {
                int realAge = Integer.parseInt(_age);
                if(realAge<=150&&realAge>=1){
                    if(Pattern.matches("1[3456789]\\d{9}",tel)==false){
                        new CreateAlert("请输入合法的手机号码,请检查是否有空格");
                    }
                    else{
                        try{
                            int cnt=HttpUtils.judgeWhetherContactIsRepeat(Account,name,tel);
                            if(cnt!=0){
                                new CreateAlert("联系人姓名和电话号码唯一请重试,该联系人已存在");
                            }
                            else {
                                Person p=new Person(name,gender,Integer.parseInt(_age),tel);
                                HttpUtils.AddContactsInformationToNewContact(p,Account);
                                new CreateTips("新建成功");
                                int id= HttpUtils.getIDThroughNameAndTel(Account,name,tel);
                                p.setId(id);
                                HomePageController.tableData.add(p);
                                p.setIndex(HomePageController.tableData.size());
                                NameTextField.setText("");
                                TelTextField.setText("");
                                AgeTextField.setText("");
                            }
                        }//这个地方有一个很严重的Bug新建之前一定要加上判断，姓名电话号码不能重复，否者将来返回来的id不止一个，将会出现异常
                        catch (Exception e){
                            System.err.println("新建失败");
                            e.printStackTrace();
                            new CreateAlert(HttpUtils.errMsg);
                        }
                    }
                }
                else{
                    new CreateAlert("年龄不合法请输入实际年龄");
                }
            }
            catch (Exception e){
                e.printStackTrace();
                new CreateAlert("年龄不能有空格");
            }
        }
    }
}
