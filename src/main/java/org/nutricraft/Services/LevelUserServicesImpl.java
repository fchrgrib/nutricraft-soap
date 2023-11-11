package org.nutricraft.Services;

import org.nutricraft.Database.Database;
import org.nutricraft.Model.LogModel;
import org.nutricraft.Model.UserLevels;

import javax.annotation.Resource;
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


@WebService(endpointInterface = "org.nutricraft.Services.LevelUserServices")
public class LevelUserServicesImpl implements LevelUserServices {

    @Resource
    private WebServiceContext wsContext;
    @Override
    public List<UserLevels> getAllLevel() {
        if(!validateApiKey()){
            System.out.println("API KEY INVALID");
            return null;
        }
        List<UserLevels> user = new ArrayList<UserLevels>();
        try{
            Database db = new Database();
            Connection connection = db.getConn();
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM userlevels";
            ResultSet result = statement.executeQuery(query);
            while (result.next()){
                user.add(new UserLevels(result.getInt("id"),result.getInt("exp")));
            }
            log("Get All Levels User");
        }catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public Integer getExp(int id) {
        if(!validateApiKey()){
            System.out.println("API KEY INVALID");
            return null;
        }
        int exp=0;
        try {
            Database db = new Database();
            Connection connection = db.getConn();
            Statement statement = connection.createStatement();
            String query = "SELECT exp FROM userlevels WHERE id = '" + id + "'";
            ResultSet result = statement.executeQuery(query);
            if(result.next()){
                exp = result.getInt("exp");
            }
            log("Get Exp User");
        }catch (Exception e){
            e.printStackTrace();
        }
        return exp;
    }

    @Override
    public String addExp(int id, int exp) {
        if(!validateApiKey()){
            System.out.println("API KEY INVALID");
            return null;
        }
        try{
            Database db = new Database();
            Connection connection = db.getConn();
            Statement statement = connection.createStatement();
            String query = "UPDATE userlevels SET exp = exp + " + exp + " WHERE id = '" + id + "'";
            statement.executeUpdate(query);
            log("Add Exp User");
            return "Successfully added exp";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "Failed to add exp";
    }

    @Override
    public String substractExp(int id, int exp) {
        if(!validateApiKey()){
            System.out.println("API KEY INVALID");
            return null;
        }
        try{
            Database db = new Database();
            Connection connection = db.getConn();
            Statement statement = connection.createStatement();
            String query = "UPDATE userlevels SET exp = exp - " + exp + " WHERE id = '" + id + "'";
            statement.executeUpdate(query);
            log("Substract Exp User");
            return "Successfully substract exo";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "Failed to subsctract exp";
    }

    @Override
    public String deleteExp(int id) {
        if(!validateApiKey()){
            System.out.println("API KEY INVALID");
            return null;
        }
        try{
            Database db = new Database();
            Connection connection = db.getConn();
            Statement statement = connection.createStatement();
            String query = "DELETE FROM userlevels WHERE id = '" + id + "'";
            statement.executeUpdate(query);
            log("Delete Exp User");
            return "Successfully deleted userlevels";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "Failed to delete userlevels";
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
