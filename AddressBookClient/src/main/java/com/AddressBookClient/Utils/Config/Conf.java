package com.AddressBookClient.Utils.Config;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
public class Conf {
    public static String ip;
    public static String port;
    public static String project;
    static {
        Properties properties = new Properties();
        InputStream fileInputStream = null;
        try {
            // 加载 Properties 文件
            File file=new File("conf/ip.properties");
            System.out.println("File : "+file);
            fileInputStream =new FileInputStream(file);
//            fileInputStream =Conf.class.getResourceAsStream("/com/AddressBookClient/conf/ip.properties");
            properties.load(fileInputStream);

            // 读取属性值
             ip= properties.getProperty("ip");
             port=properties.getProperty("port");
             project=properties.getProperty("project");
            // 打印属性值
            System.out.println("ip = " + ip);
            System.out.println("port = " + port);
            System.out.println("project = " + project);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("Conf init");
    }
}
