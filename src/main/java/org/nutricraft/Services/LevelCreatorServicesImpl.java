package org.nutricraft.Services;


import org.nutricraft.Database.Database;
import org.nutricraft.Model.CreatorLevels;
import org.nutricraft.Model.LogModel;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebService(endpointInterface = "org.nutricraft.Services.LevelCreatorServices")
public class LevelCreatorServicesImpl implements LevelCreatorServices{

    @Resource
    private WebServiceContext wsContext;
    @WebMethod
    public List<CreatorLevels> getAllLevels(){
        if(!validateApiKey()){
            System.out.println("API KEY INVALID");
            return null;
        }
        List<CreatorLevels> creator = new ArrayList<CreatorLevels>();
        try{
            Database db = new Database();
            Connection connection = db.getConn();
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM creatorlevels";
            ResultSet result = statement.executeQuery(query);
            while (result.next()){
                creator.add(new CreatorLevels(result.getString("id"),result.getInt("exp")));
            }
            log("Get All Levels");
        }catch (Exception e){
            e.printStackTrace();
        }
        return creator;
    }

    @WebMethod
    public Integer getExp(String id){
        if(!validateApiKey()){
            System.out.println("API KEY INVALID");
            return null;
        }
        int exp=0;
        try {
            Database db = new Database();
            Connection connection = db.getConn();
            Statement statement = connection.createStatement();
            String query = "SELECT exp FROM creatorlevels WHERE id = '" + id + "'";
            ResultSet result = statement.executeQuery(query);
            if(result.next()){
                exp = result.getInt("exp");
            }
            log("Get Exp");
        }catch (Exception e){
            e.printStackTrace();
        }
        return exp;
    }

    @WebMethod
    public String addExp(String id, int exp){
        if(!validateApiKey()){
            System.out.println("API KEY INVALID");
            return null;
        }
        try{
            Database db = new Database();
            Connection connection = db.getConn();
            Statement statement = connection.createStatement();
            String query = "UPDATE creatorlevels SET exp = exp + " + exp + " WHERE id = '" + id + "'";
            statement.executeUpdate(query);
            log("Add Exp");
            return "Successfully added exp";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "Failed to add exp";
    }

    @WebMethod
    public String substractExp(String id, int exp){
        if(!validateApiKey()){
            System.out.println("API KEY INVALID");
            return null;
        }
        try{
            Database db = new Database();
            Connection connection = db.getConn();
            Statement statement = connection.createStatement();
            String query = "UPDATE creatorlevels SET exp = exp - " + exp + " WHERE id = '" + id + "'";
            statement.executeUpdate(query);
            log("Substract Exp");
            return "Successfully substract exo";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "Failed to subsctract exp";
    }

    @WebMethod
    public String deleteExp(String id){
        if(!validateApiKey()){
            System.out.println("API KEY INVALID");
            return null;
        }
        try{
            Database db = new Database();
            Connection connection = db.getConn();
            Statement statement = connection.createStatement();
            String query = "DELETE FROM creatorlevels WHERE id = '" + id + "'";
            statement.executeUpdate(query);
            log("Delete Exp");
            return "Successfully deleted creatorlevels";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "Failed to delete creatorlevels";
    }

    public Boolean validateApiKey() {
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
    public void log(String description) {
        MessageContext messageContext = wsContext.getMessageContext();
        String queryString = (String) messageContext.get("javax.xml.ws.http.request.querystring");
        System.out.println("messageContext: " + queryString);
        String[] keyValue = queryString.split("=");
        String apiKey = keyValue[1];
        System.out.println("API KEY: " + apiKey);
        String ip = "123";
        String endpoint = (String) messageContext.get("javax.xml.ws.service.endpoint.address");
        Timestamp timestamp = new Timestamp(new Date().getTime());
        LogModel logModel = new LogModel();
        logModel.InsertLog(description, endpoint, ip, timestamp.toString());
    }
}
