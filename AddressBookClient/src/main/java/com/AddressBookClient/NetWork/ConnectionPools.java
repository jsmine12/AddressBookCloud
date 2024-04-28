package com.AddressBookClient.NetWork;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
public class ConnectionPools {
    public static CloseableHttpClient httpclient = HttpClients.createDefault();
}
