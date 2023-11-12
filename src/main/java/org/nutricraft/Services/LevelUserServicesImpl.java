package org.nutricraft.Services;

import org.nutricraft.Database.Database;
import org.nutricraft.Model.UserLevels;


import javax.jws.WebMethod;
import javax.jws.WebService;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


@WebService(endpointInterface = "org.nutricraft.Services.LevelUserServices")
public class LevelUserServicesImpl extends Services implements LevelUserServices {

    @WebMethod
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

    @WebMethod
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

    @WebMethod
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

    @WebMethod
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

    @WebMethod
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


}
