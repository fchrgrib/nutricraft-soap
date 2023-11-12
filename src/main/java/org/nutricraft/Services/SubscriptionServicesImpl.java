package org.nutricraft.Services;

import org.nutricraft.Database.Database;
import org.nutricraft.Model.Subscibers;


import javax.jws.WebMethod;
import javax.jws.WebService;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebService (endpointInterface = "org.nutricraft.Services.SubscriptionServices")
public class SubscriptionServicesImpl extends Services implements SubscriptionServices{

    @WebMethod
    public String newSubscription(String idCreator, int idSubscriber){
        if(!validateApiKey()){
            System.out.println("API KEY INVALID");
            return "API KEY INVALID";
        }
        try {
            Database db = new Database();
            Connection connection = db.getConn();
            Statement statement = connection.createStatement();
            String query = "INSERT INTO subscribers (id_creator, id_user) VALUES ('" + idCreator + "', '" + idSubscriber + "')";
            statement.executeUpdate(query);
            log("New Subscription");
            return "Successfully inserted new subscription";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Failed to insert new subscription";
    }
    @WebMethod
    public Boolean checkSubscription(String idCreator, int idSubscriber){
        if(!validateApiKey()){
            System.out.println("API KEY INVALID");
            return null;
        }
        try {
            Database db = new Database();
            Connection connection = db.getConn();
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM subscribers WHERE id_creator = '" + idCreator + "' AND id_user = '" + idSubscriber + "'";
            ResultSet result = statement.executeQuery(query);
            if (result.next()) {
                log("Check Subscription");
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @WebMethod
    public List<Integer> getSubscribers(String idCreator){
        if(!validateApiKey()){
            System.out.println("API KEY INVALID");
            return null;
        }
        List<Integer> listSubscribers = new ArrayList<Integer>();
        try {
            Database db = new Database();
            Connection connection = db.getConn();
            Statement statement = connection.createStatement();
            String query = "SELECT id_user FROM subscribers WHERE id_creator = '" + idCreator + "'";
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                int id = result.getInt("id_user");
                System.out.println("id: " + id);
                listSubscribers.add(id);
            }
            log("Get Subscribers");
        }catch (Exception e){
            e.printStackTrace();
        }
        return listSubscribers;
    }

    @WebMethod
    public List<String> getCreators(int idSubscriber){
        if(!validateApiKey()){
            System.out.println("API KEY INVALID");
            return null;
        }
        List<String> listCreators = new ArrayList<String>();
        try {
            Database db = new Database();
            Connection connection = db.getConn();
            Statement statement = connection.createStatement();
            String query = "SELECT id_creator FROM subscribers WHERE id_user = '" + idSubscriber + "'";
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                String id = result.getString("id_creator");
                System.out.println("id: " + id);
                listCreators.add(id);
            }
            log("Get Creators");
        }catch (Exception e){
            e.printStackTrace();
        }
        return listCreators;
    }

    @WebMethod
    public List<Subscibers> getAllSubscription() {
        if(!validateApiKey()){
            System.out.println("API KEY INVALID");
            return null;
        }
        List<Subscibers> listSubscribers = new ArrayList<Subscibers>();

        try {
            Database db = new Database();
            Connection connection = db.getConn();
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM subscribers";
            ResultSet result = statement.executeQuery(query);

            while (result.next()) {
                int id = result.getInt("id");
                String idCreator = result.getString("id_creator");
                int idSubscriber = result.getInt("id_user");
                listSubscribers.add(new Subscibers(id,idCreator,idSubscriber));
            }
            log("Get All Subscription");

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("==================");
        for(Subscibers sub : listSubscribers){
            System.out.println("id: " + sub.getId() + " id_creator: " + sub.getId_creator() + " id_user: " + sub.getId_user());
        }
        return listSubscribers;
    }

}

