package org.nutricraft.Services;

import com.sun.net.httpserver.HttpExchange;
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
        System.out.println("queryString: " + queryString);
        String[] keyValue = queryString.split("=");
        if(keyValue.length==0 || !keyValue[0].equals("APIkey")){
            return false;
        }
        String apiKey = keyValue[1];
        System.out.println("API KEY: " + apiKey);
        String rest = System.getenv("SOAP_KEY_REST");
        String app = System.getenv("SOAP_KEY_APP");
        return apiKey.equals(rest) || apiKey.equals(app);
    }
    //
    protected void log(String description) {
        MessageContext messageContext = wsContext.getMessageContext();
        HttpExchange exchange = (HttpExchange) messageContext.get("com.sun.xml.ws.http.exchange");
        String ip = exchange.getRemoteAddress().getAddress().getHostAddress();
        String[] url = (exchange.getRequestURI().toString()).split("\\?APIkey=");
        String endpoint = url[0];
        String apiKey = url[1];
        String requested="";
        String rest = System.getenv("SOAP_KEY_REST");
        String app = System.getenv("SOAP_KEY_APP");
        if(apiKey.equals(rest)){
            requested = "REST";
        } else if(apiKey.equals(app)) {
            requested = "APP";
        }
        Timestamp timestamp = new Timestamp(new Date().getTime());
        Database db = new Database();
        db.InsertLog(description, endpoint, ip, requested, timestamp.toString());
    }
}
