package com.AddressBookClient.pojo;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
public class User {
    @JSONField(name = "account",ordinal = 1)
    private String Account;
    @JSONField(name = "password",ordinal = 2)
    private String Password;
    public User(){}
    public User(String Account,String Password){
        this.Account=Account;
        this.Password=Password;
    }
    @Override
    public boolean equals(Object o){
        return Account.equals(((User)o).Account);
    }

    public String getAccount() {
        return Account;
    }

    public void setAccount(String account) {
        Account = account;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
