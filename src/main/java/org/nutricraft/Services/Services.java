package org.nutricraft.Services;

import org.nutricraft.Database.Database;

import javax.annotation.Resource;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import java.sql.Timestamp;
import java.util.Date;

public class Services {
    @Resource
    private WebServiceContext wsContext;
    protected Boolean validateApiKey() {
        MessageContext messageContext = wsContext.getMessageContext();
        String queryString = (String) messageContext.get("javax.xml.ws.http.request.querystring");
        System.out.println("messageContext: " + queryString);
        String[] keyValue = queryString.split("=");
        if(keyValue.length==0 || !keyValue[0].equals("APIkey")){
            return false;
        }
        String apiKey = keyValue[1];
        System.out.println("API KEY: " + apiKey);
        if(apiKey.equals("lalala")||apiKey.equals("hahaha")){
            return true;
        }else{
            return false;
        }
    }
    //
    protected void log(String description) {
        MessageContext messageContext = wsContext.getMessageContext();
        String queryString = (String) messageContext.get("javax.xml.ws.http.request.querystring");
        System.out.println("messageContext: " + queryString);
        String[] keyValue = queryString.split("=");
        String apiKey = keyValue[1];
        System.out.println("API KEY: " + apiKey);
        String ip = "123";
        String endpoint = (String) messageContext.get("javax.xml.ws.service.endpoint.address");
        Timestamp timestamp = new Timestamp(new Date().getTime());
        Database db = new Database();
        db.InsertLog(description, endpoint, ip, timestamp.toString());
    }
}
