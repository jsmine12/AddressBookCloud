package com.AddressBookClient.pojo;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
public class Person {
    @JSONField(name="ID",ordinal = 1)
    private int id;
    @JSONField(name="Name",ordinal = 2)
    private String name;
    @JSONField(name="Sex",ordinal =3)
    private String sex;
    @JSONField(name="Age",ordinal =4)
    private int age;
    @JSONField(name="Tel",ordinal = 5)
    private String tel;
    private int index;
    public Person() {
        // 空的无参构造函数
    }
    public Person(String name, String sex, int age, String tel) {
        this.name = name;
        this.age = age;
        this.sex=sex;
        this.tel=tel;
    }
    @Override
    public boolean equals(Object o){
        if(this.id==((Person)o).id)return true;
        return false;
    }
    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id=id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getSex() {
        return sex;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getTel() {
        return tel;
    }
    public void setTel(String tel) {
        this.tel = tel;
    }
    public int getIndex() {return index;}
    public void setIndex(int index) {this.index = index;}
}
