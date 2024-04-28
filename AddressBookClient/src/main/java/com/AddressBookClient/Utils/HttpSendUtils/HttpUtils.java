package com.AddressBookClient.Utils.HttpSendUtils;
import com.AddressBookClient.NetWork.ConnectionPools;
import com.AddressBookClient.Utils.Config.Conf;
import com.AddressBookClient.pojo.Person;
import com.AddressBookClient.pojo.Result;
import com.AddressBookClient.pojo.User;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.*;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
public class HttpUtils {
    //Contacts
    public static CloseableHttpClient client=ConnectionPools.httpclient;
    public static String getPath="/select";
    public static String addPath="/put";
    public static String modifyPath="/post";
    public static String deletePath="/delete";
    public static String getLargeMounts="/large";
    public static String getSingleMounts="/single";
    public static String typeUser="/user";
    public static String typeContacts="/contact";
    public static String typeAdmin="/admin";
    public static String typeAccount="/account";
    public static String typePassword="/password";
    public static String typeID="/id";
    public static String typeCnt="/cnt";
    public static String protocolPathHead="http://";
    public static String Destinctoin= Conf.ip+":"+Conf.port+Conf.project;
    public static String errMsg="网络错误请检查你的网络，待网络顺畅之后，请尝试重启程序";
    public static String CommonSendHttpOperator(String path, HttpRequestBase sendMethod,String json)throws Exception{
        // 处理响应
        String responseString=null;
        sendMethod.setURI(new URI(path));
        System.out.println(path);
        HttpResponse response=null;
        if(json==null){
            response=client.execute(sendMethod);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                responseString = EntityUtils.toString(entity);
            }
            // 关闭响应实体
            EntityUtils.consume(entity);
        }
        else{
            HttpEntityEnclosingRequest send=(HttpEntityEnclosingRequest) sendMethod;
            send.addHeader("Content-Type", "application/json");
            StringEntity requestEntity = new StringEntity(json, ContentType.APPLICATION_JSON);
            send.setEntity(requestEntity);
            response=client.execute(sendMethod);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                responseString = EntityUtils.toString(entity);
            }
            // 关闭响应实体
            EntityUtils.consume(entity);
        }
        return responseString;
    }
    public static List<User> ParseJSONUserArray(String JSONRes){
        Result result= JSONObject.parseObject(JSONRes,Result.class);
        JSONArray jsonArray = JSON.parseArray(JSON.toJSONString(result.getData()));
        List<User> usrList = new ArrayList<>();
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            User usr = JSON.toJavaObject(jsonObject,User.class);
            usrList.add(usr);
        }
        return usrList;
    }
    public static List<Person> ParseJSONPersonArray(String JSONRes){
        Result result= JSONObject.parseObject(JSONRes,Result.class);
        JSONArray jsonArray = JSON.parseArray(JSON.toJSONString(result.getData()));
        List<Person> personList = new ArrayList<>();
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            Person person = JSON.toJavaObject(jsonObject, Person.class);
            person.setIndex(i+1);
            personList.add(person);
        }
        return personList;
    }
    public static int isExistAccount(String account) throws Exception {
        //http://localhost:8080/AddressBookServer/select/single/account?account=123456
        String res=CommonSendHttpOperator(protocolPathHead+Destinctoin+getPath+getSingleMounts+typeAccount+"?"+"account="+account,new HttpGet(),null);
        System.out.println(res);//////////////
        Result result=JSON.parseObject(res,Result.class);
        if(result.getMsg().equals("Exist")){
            return 1;
        }
        else {
            return 0;
        }
    }
    public static int judgeWhetherContactIsRepeat(String account,String name,String tel) throws Exception{
        String res=CommonSendHttpOperator(protocolPathHead+Destinctoin+getPath+getSingleMounts+typeCnt+"?"+"account="+account+"&"+"name="+name+"&"+"tel="+tel,new HttpGet(),null);
        System.out.println(res);//////////////
        Result result=JSON.parseObject(res,Result.class);
        return (Integer) result.getData();
    }
    public static String selectPassword(String account)throws Exception {
        String res=CommonSendHttpOperator(protocolPathHead+Destinctoin+getPath+getSingleMounts+typePassword+"?"+"account="+account,new HttpGet(),null);
        Result result=JSON.parseObject(res,Result.class);
        System.out.println(res);//////////////
        return (String) result.getData();
    }
    public static List<Person> getResultTableQueryAllContacts(String account)throws Exception{
        String res=CommonSendHttpOperator(protocolPathHead+Destinctoin+getPath+getLargeMounts+typeContacts+"?"+"account="+account,new HttpGet(),null);
        System.out.println(res);//////////////
        return ParseJSONPersonArray(res);
    }
    public static List<Person> getResultTableQueryAllDataSuitConfident(String name,String account) throws Exception {
        String res=CommonSendHttpOperator(protocolPathHead+Destinctoin+getPath+getLargeMounts+typeContacts+"/ByName"+"?"+"account="+account+"&"+"name="+name,new HttpGet(),null);
        System.out.println(res);//////////////
        return ParseJSONPersonArray(res);
    }
    public static List<User> getResultTableQueryAllUsers() throws Exception {
        String res=CommonSendHttpOperator(protocolPathHead+Destinctoin+getPath+getLargeMounts+typeUser,new HttpGet(),null);
        System.out.println(res);//////////////
        return ParseJSONUserArray(res);
    }
    public static List<User> getResultTableQueryAllUserSuitConfident(String account) throws Exception{
        String res=CommonSendHttpOperator(protocolPathHead+Destinctoin+getPath+getSingleMounts+typeUser+"?"+"account="+account,new HttpGet(),null);
        System.out.println(res);//////////////
        return ParseJSONUserArray(res);
    }
    public static int getIDThroughNameAndTel(String account,String name,String tel) throws Exception{
        String res=CommonSendHttpOperator(protocolPathHead+Destinctoin+getPath+getSingleMounts+typeID+"?"+"account="+account+"&"+"name="+name+"&"+"tel="+tel,new HttpGet(),null);
        System.out.println(res);
        Result r=JSONObject.parseObject(res,Result.class);
        return (Integer)r.getData();
    }
    public static String getPasswordOfAdmin() throws Exception {
        String res=CommonSendHttpOperator(protocolPathHead+Destinctoin+getPath+getSingleMounts+typeAdmin+typePassword,new HttpGet(),null);
        System.out.println(res);
        Result r=JSONObject.parseObject(res,Result.class);
        return (String) r.getData();
    }
    public static void addAccountAndPasswordToDataBase(String account,String pwd) throws Exception{
        String res=CommonSendHttpOperator(protocolPathHead+Destinctoin+addPath+typePassword+typeUser+"?"+"account="+account+"&"+"password="+pwd,new HttpPut(),null);
        System.out.println(res);
    }
    public static void AddContactsInformationToNewContact(Person p,String account) throws Exception {
        String res=CommonSendHttpOperator(protocolPathHead+Destinctoin+addPath+typeContacts+"?"+"account="+account,new HttpPut(),JSON.toJSONString(p));
        System.out.println(res);
    }
    public static void updateContacts(Person p,String account,String id) throws Exception {
        String res=CommonSendHttpOperator(protocolPathHead+Destinctoin+modifyPath+typeContacts+"?"+"account="+account+"&"+"id="+id,new HttpPost(),JSON.toJSONString(p));
        System.out.println(res);
    }
    public static void updatePasswordOfAccount(String account,String password) throws Exception {
        String res=CommonSendHttpOperator(protocolPathHead+Destinctoin+modifyPath+typePassword+typeUser+"?"+"account="+account+"&"+"password="+password,new HttpPost(),null);
        System.out.println(res);
    }
    public static void updateAdminPasswordOfAccount(String password)throws Exception{
        String res=CommonSendHttpOperator(protocolPathHead+Destinctoin+modifyPath+typePassword+typeAdmin+"?"+"password="+password,new HttpPost(),null);
        System.out.println(res);
    }
    public static void deleteRowDataById(String id)throws Exception{
        String res=CommonSendHttpOperator(protocolPathHead+Destinctoin+deletePath+typeContacts+"?"+"id="+id,new HttpDelete(),null);
        System.out.println(res);
    }
    public static void deleteRowDataByAccount(String account) throws Exception{
        String res=CommonSendHttpOperator(protocolPathHead+Destinctoin+deletePath+typeUser+"?"+"account="+account,new HttpDelete(),null);
        System.out.println(res);
    }
    public static void main(String[] args) {
        try{
            judgeWhetherContactIsRepeat("1649453887","鑫浩","17156331512");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
