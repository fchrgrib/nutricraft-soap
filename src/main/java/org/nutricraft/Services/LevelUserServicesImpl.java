package org.nutricraft.Services;

import org.nutricraft.Database.Database;
import org.nutricraft.Model.UserLevels;

import javax.jws.WebService;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


@WebService(endpointInterface = "org.nutricraft.Services.LevelUserServices")
public class LevelUserServicesImpl implements LevelUserServices {

    @Override
    public List<UserLevels> getAllLevel() {
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
        }catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public int getExp(int id) {
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
        }catch (Exception e){
            e.printStackTrace();
        }
        return exp;
    }

    @Override
    public String addExp(int id, int exp) {
        try{
            Database db = new Database();
            Connection connection = db.getConn();
            Statement statement = connection.createStatement();
            String query = "UPDATE userlevels SET exp = exp + " + exp + " WHERE id = '" + id + "'";
            statement.executeUpdate(query);
            return "Successfully added exp";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "Failed to add exp";
    }

    @Override
    public String substractExp(int id, int exp) {
        try{
            Database db = new Database();
            Connection connection = db.getConn();
            Statement statement = connection.createStatement();
            String query = "UPDATE userlevels SET exp = exp - " + exp + " WHERE id = '" + id + "'";
            statement.executeUpdate(query);
            return "Successfully substract exo";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "Failed to subsctract exp";
    }

    @Override
    public String deleteExp(int id) {
        try{
            Database db = new Database();
            Connection connection = db.getConn();
            Statement statement = connection.createStatement();
            String query = "DELETE FROM userlevels WHERE id = '" + id + "'";
            statement.executeUpdate(query);
            return "Successfully deleted userlevels";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "Failed to delete userlevels";
    }
}
