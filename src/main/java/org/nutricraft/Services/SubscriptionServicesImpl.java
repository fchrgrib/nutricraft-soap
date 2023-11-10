package org.nutricraft.Services;

import org.nutricraft.Database.Database;
import org.nutricraft.Model.LogModel;
import org.nutricraft.Model.Subscibers;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebService
public class SubscriptionServicesImpl implements SubscriptionServices{

    @Resource
    public WebServiceContext wsContext;
    @WebMethod
    public String newSubscription(String idCreator, int idSubscriber){
        try {
            Database db = new Database();
            Connection connection = db.getConn();
            Statement statement = connection.createStatement();
            String query = "INSERT INTO subscribers (id_creator, id_user) VALUES ('" + idCreator + "', '" + idSubscriber + "')";
            statement.executeUpdate(query);
            return "Successfully inserted new subscription";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "newSubs";
    }
    @WebMethod
    public Boolean checkSubscription(String idCreator, int idSubscriber){
        try {
            Database db = new Database();
            Connection connection = db.getConn();
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM subscribers WHERE id_creator = '" + idCreator + "' AND id_user = '" + idSubscriber + "'";
            ResultSet result = statement.executeQuery(query);
            if (result.next()) {
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @WebMethod
    public List<Integer> getSubscribers(String idCreator){
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
        }catch (Exception e){
            e.printStackTrace();
        }
        return listSubscribers;
    }

    @WebMethod
    public List<String> getCreators(int idSubscriber){
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
        }catch (Exception e){
            e.printStackTrace();
        }
        return listCreators;
    }

    @WebMethod
    public List<Subscibers> getAllSubscription() {
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

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("==================");
        for(Subscibers sub : listSubscribers){
            System.out.println("id: " + sub.getId() + " id_creator: " + sub.getId_creator() + " id_user: " + sub.getId_user());
        }
        return listSubscribers;
    }

//    public Boolean validateApiKey() {
//        String[] API_KEYS = { "PremiumApp", "Postman", "RestClient", "RegularApp" };
//        MessageContext mc = wsContext.getMessageContext();
//        HttpExchange exchange = (HttpExchange) mc.get("com.sun.xml.ws.http.exchange");
//        String apiKey = exchange.getRequestHeaders().getFirst("X-API-KEY");
//        if (apiKey == null) {
//            return false;
//        } else if (apiKey.equals(API_KEYS[0]) || apiKey.equals(API_KEYS[1]) || apiKey.equals(API_KEYS[2])
//                || apiKey.equals(API_KEYS[3])) {
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//    public void log(String description) {
//        MessageContext msgContext = wsContext.getMessageContext();
//        HttpExchange httpExchange = (HttpExchange) msgContext.get("com.sun.xml.ws.http.exchange");
//        String ip = httpExchange.getRemoteAddress().getAddress().getHostAddress();
//        String endpoint = httpExchange.getRequestURI().toString();
//        LogModel logModel = new LogModel();
//        String apiKey = httpExchange.getRequestHeaders().getFirst("X-API-KEY");
//        String desc = apiKey + ": " + description;
//        logModel.InsertLog(desc, endpoint, ip);
//    }
}

// Database db = new Database();
// Connection connection = db.getConn();
// try{
//     Statement statement = connection.createStatement();
//     String query = "SELECT * FROM logging";
//     ResultSet result = statement.executeQuery(query);
//     while (result.next()) {
//         int id = result.getInt("id");
//         String desc = result.getString("description");
//         String ip = result.getString("ip");
//         String endpoint = result.getString("endpoint");
//         String date = result.getString("date");

//         System.out.println("id: " + id+ " desc: " + desc + " ip: " + ip + " endpoint: " + endpoint + " date: " + date);
//     }
// }catch (Exception e){
//     e.printStackTrace();
// }