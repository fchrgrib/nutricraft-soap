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
public class LevelCreatorServicesImpl implements LevelCreatorServices{
    @WebMethod
    public List<CreatorLevels> getAllLevels(){
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
        }catch (Exception e){
            e.printStackTrace();
        }
        return creator;
    }

    @WebMethod
    public int getExp(String id){
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
        }catch (Exception e){
            e.printStackTrace();
        }
        return exp;
    }

    @WebMethod
    public String addExp(String id, int exp){
        try{
            Database db = new Database();
            Connection connection = db.getConn();
            Statement statement = connection.createStatement();
            String query = "UPDATE creatorlevels SET exp = exp + " + exp + " WHERE id = '" + id + "'";
            statement.executeUpdate(query);
            return "Successfully added exp";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "Failed to add exp";
    }

    @WebMethod
    public String substractExp(String id, int exp){
        try{
            Database db = new Database();
            Connection connection = db.getConn();
            Statement statement = connection.createStatement();
            String query = "UPDATE creatorlevels SET exp = exp - " + exp + " WHERE id = '" + id + "'";
            statement.executeUpdate(query);
            return "Successfully substract exo";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "Failed to subsctract exp";
    }

    @WebMethod
    public String deleteExp(String id){
        try{
            Database db = new Database();
            Connection connection = db.getConn();
            Statement statement = connection.createStatement();
            String query = "DELETE FROM creatorlevels WHERE id = '" + id + "'";
            statement.executeUpdate(query);
            return "Successfully deleted creatorlevels";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "Failed to delete creatorlevels";
    }
}
