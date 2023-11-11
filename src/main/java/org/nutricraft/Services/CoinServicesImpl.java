package org.nutricraft.Services;

import org.nutricraft.Database.Database;
import org.nutricraft.Model.Coins;
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

@WebService(endpointInterface = "org.nutricraft.Services.CoinServices")
public class CoinServicesImpl implements CoinServices{

    @Resource
    private WebServiceContext wsContext;
    @WebMethod
    public List<Coins> getAllCoins() {
        if(!validateApiKey()){
            System.out.println("API KEY INVALID");
            return null;
        }
        List<Coins> coins = new ArrayList<Coins>();
        try{
            Database db = new Database();
            Connection connection = db.getConn();
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM coins";
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                coins.add(new Coins(result.getString("id"), result.getInt("coin")));
            }
            log("Get All Coins");
        }catch (Exception e){
            e.printStackTrace();
        }
        for (Coins coin : coins) {
            System.out.print(coin.getId()+" ");
            System.out.println(coin.getCoins());
        }

        return coins;
    }

    @WebMethod
    public Integer getCoins(String id) {
        if(!validateApiKey()){
            System.out.println("API KEY INVALID");
            return null;
        }
        int coin=0;
        try {
            Database db = new Database();
            Connection connection = db.getConn();
            Statement statement = connection.createStatement();
            String query = "SELECT coin FROM coins JOIN subscribers ON coins.id = subscribers.id_creator WHERE coins.id = '" + id + "'";
            ResultSet result = statement.executeQuery(query);
            if(result.next()){
                coin = result.getInt("coin");
            }
            log("Get Coins");
        }catch (Exception e){
            e.printStackTrace();
        }
        return coin;
    }

    @WebMethod
    public String addCoins(String id, int coins) {
        if(!validateApiKey()){
            System.out.println("API KEY INVALID");
            return null;
        }
        try{
            Database db = new Database();
            Connection connection = db.getConn();
            Statement statement = connection.createStatement();
            String query = "UPDATE coins SET coin = coin + " + coins + " WHERE id = '" + id + "'";
            statement.executeUpdate(query);
            log("Add Coins");
            return "Successfully added coins";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "Failed to add coins";
    }

    @WebMethod
    public String substractCoins(String id, int coins) {
        if(!validateApiKey()){
            System.out.println("API KEY INVALID");
            return null;
        }
        try{
            Database db = new Database();
            Connection connection = db.getConn();
            Statement statement = connection.createStatement();
            String query = "UPDATE coins SET coin = coin - " + coins + " WHERE id = '" + id + "'";
            statement.executeUpdate(query);
            log("Substract Coins");
            return "Successfully removed coins";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "Failed to remove coins";
    }

    @WebMethod
    public String deleteCoins(String id) {
        if(!validateApiKey()){
            System.out.println("API KEY INVALID");
            return null;
        }
        try{
            Database db = new Database();
            Connection connection = db.getConn();
            Statement statement = connection.createStatement();
            String query = "DELETE FROM coins WHERE id = '" + id + "'";
            statement.executeUpdate(query);
            log("Delete Coins");
            return "Successfully deleted coins";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "Failed to delete coins";
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
