package org.nutricraft.Services;

import org.nutricraft.Database.Database;
import org.nutricraft.Model.Coins;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@WebService(endpointInterface = "org.nutricraft.Services.CoinServices")
public class CoinServicesImpl extends Services implements CoinServices{

    @WebMethod
    public String newCoins(String id){
        if(!validateApiKey()){
            System.out.println("API KEY INVALID");
            return null;
        }
        try{
            Database db = new Database();
            Connection connection = db.getConn();
            Statement statement = connection.createStatement();
            String query = "INSERT INTO coins (id, coin) VALUES ('" + id + "', 0)";
            statement.executeUpdate(query);
            log("New Coins");
            return "Successfully created new coins";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "Failed to create new coins";
    }

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

}
