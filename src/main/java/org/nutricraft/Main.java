package org.nutricraft;

import org.nutricraft.Database.Database;
//import org.nutricraft.Services.CoinServicesImpl;
import org.nutricraft.Services.SubscriptionServicesImpl;
//import java.lang.reflect.Field;
import javax.xml.ws.Endpoint;
import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        try {

            Endpoint.publish("http://nutricraft-soap:8081/ws/subscription", new SubscriptionServicesImpl());
//            Endpoint.publish("http://nutricraft-soap:8081/ws/userLevels", new SubscriptionServicesImpl());
//            Endpoint.publish("http://nutricraft-soap:8081/ws/creatorLevels", new SubscriptionServicesImpl());
//            Endpoint.publish("http://nutricraft-soap:8081/ws/coins", new CoinServicesImpl());
//            Thread.sleep(10000);
//            Database db = new Database();
//            Connection connection = db.getConn();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("woiii");
        }
    }
}
