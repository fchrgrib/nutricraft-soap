package org.nutricraft;


import org.nutricraft.Services.CoinServicesImpl;
import org.nutricraft.Services.LevelCreatorServicesImpl;
import org.nutricraft.Services.LevelUserServicesImpl;
import org.nutricraft.Services.SubscriptionServicesImpl;
import javax.xml.ws.Endpoint;


public class Main {
    public static void main(String[] args) {
        try {

            Endpoint.publish("http://nutricraft-soap:8081/ws/subscription", new SubscriptionServicesImpl());
            Endpoint.publish("http://nutricraft-soap:8081/ws/userLevels", new LevelUserServicesImpl());
            Endpoint.publish("http://nutricraft-soap:8081/ws/creatorLevels", new LevelCreatorServicesImpl());
            Endpoint.publish("http://nutricraft-soap:8081/ws/coins", new CoinServicesImpl());
            System.out.println("Server is ready...");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Something went wrong....");
        }
    }
}
