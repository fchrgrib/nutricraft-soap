package org.nutricraft.Services;


import org.nutricraft.Database.Database;
import org.nutricraft.Model.CreatorLevels;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@WebService(endpointInterface = "org.nutricraft.Services.LevelCreatorServices")
public class LevelCreatorServicesImpl extends Services implements LevelCreatorServices{

    @WebMethod
    public String newCreator(String id){
        if(!validateApiKey()){
            System.out.println("API KEY INVALID");
            return null;
        }
        try{
            Database db = new Database();
            Connection connection = db.getConn();
            Statement statement = connection.createStatement();
            String query = "INSERT INTO creatorlevels (id, exp) VALUES ('" + id + "', 0)";
            statement.executeUpdate(query);
            log("New Creator");
            return "Successfully created new creator";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "Failed to create new creator";
    }

    @WebMethod
    public List<CreatorLevels> getAllLevelCreator(){
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
            log("Get All Levels Creators");
        }catch (Exception e){
            e.printStackTrace();
        }
        return creator;
    }

    @WebMethod
    public Integer getExpCreator(String id){
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
    public String addExpCreator(String id, int exp){
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
            return "Successfully add exp creator";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "Failed to add exp creator";
    }

    @WebMethod
    public String substractExpCreator(String id, int exp){
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
            return "Successfully substract exp creator";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "Failed to substract exp creator";
    }

    @WebMethod
    public String deleteExpCreator(String id){
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
            return "Successfully delete exp creator";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "Failed to delete exp creator";
    }

}
