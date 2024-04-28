package com.AddressBookClient.User;
import com.AddressBookClient.Utils.HttpSendUtils.HttpUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import com.AddressBookClient.Utils.*;
import com.AddressBookClient.pojo.*;
import java.util.regex.Pattern;
public class ModifyContactInformationController {
    @FXML
    private TextField idTextField;
    @FXML
    private TextField NameTextField;
    @FXML
    private TextField AgeTextField;
    @FXML
    private TextField TelTextField;
    @FXML
    private RadioButton radioButton1;
    @FXML
    void handleSubmitButtonClick(ActionEvent event) {
        String Account=LoginController.transmit;
        String index = idTextField.getText();
        String name=NameTextField.getText();
        String gender;
        if(radioButton1.isSelected()){
            gender="男";
        }
        else gender="女";
        String Age=AgeTextField.getText();
        String tel=TelTextField.getText();

        if(index.isEmpty()){
            new CreateAlert("编号不能为空");
        }
        else if(Pattern.matches("[0-9]+",index)==false){//编号不合法
            new CreateAlert("编号不合法，编号必须是数字,请检查是否有空格");
        }
        else{
            Person person=new Person();
            int at=Integer.parseInt(index);
            person.setIndex(at);
//            boolean exist= HomePageController.tableData.contains(person);
            boolean exist=false;
            if(at<=HomePageController.tableData.size())exist=true;
            if(!exist){
                new CreateAlert("编号未找到请输入正确的编号");
            }
            else{//编号存在有找到,不用管编号
//                int pos=HomePageController.tableData.indexOf(person);
                Person p=HomePageController.tableData.get(at-1);
                String name_dataBase =p.getName();
                String sex_dataBase =p.getSex();
                String age_dataBase =((Integer)p.getAge()).toString();
                String tel_dataBase =p.getTel();
                if(name.isEmpty()){
                    name=name_dataBase;
                }
                if(gender.isEmpty()){
                    gender=sex_dataBase;
                }
                if(Age.isEmpty()){
                    Age=age_dataBase;
                }

                if(tel.isEmpty()){
                    tel=tel_dataBase;
                }
                try {
                    int realAge = Integer.parseInt(Age);
                    if (realAge >= 1 && realAge <= 150) {
                        if (Pattern.matches("1[3456789]\\d{9}", tel) == false) {
                            new CreateAlert("请输入合法的手机号码,请检查是否有空格");
                        } else {
                            //如果字段放空则相同默认不修改
                            try {
                                Person person1 = new Person(name, gender, Integer.parseInt(Age), tel);
                                int id=HomePageController.tableData.get(at-1).getId();
                                person1.setId(id);
                                person1.setIndex(at);
                                HttpUtils.updateContacts(person1, Account, Integer.valueOf(id).toString());
//                                int index = HomePageController.tableData.indexOf(person1);
                                HomePageController.tableData.set(at-1, person1);
                                new CreateTips("修改成功");
                                idTextField.setText("");
                                NameTextField.setText("");
                                AgeTextField.setText("");
                                TelTextField.setText("");
                            }
                            catch (Exception e){
                                e.printStackTrace();
                                new CreateAlert(HttpUtils.errMsg);
                            }
                        }
                    }
                    else{
                        new CreateAlert("年龄不合法不符合实际");
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                    new CreateAlert("年龄不合法请勿输入空格");
                }
        }
    }
}
}
