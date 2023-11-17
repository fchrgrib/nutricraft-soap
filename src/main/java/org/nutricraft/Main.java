package org.nutricraft;


import org.nutricraft.Services.*;

import javax.xml.ws.Endpoint;


public class Main {
    public static void main(String[] args) {
        try {

            Endpoint.publish("http://nutricraft-soap:8081/ws/subscription", new SubscriptionServicesImpl());
            Endpoint.publish("http://nutricraft-soap:8081/ws/userLevels", new LevelUserServicesImpl());
            Endpoint.publish("http://nutricraft-soap:8081/ws/creatorLevels", new LevelCreatorServicesImpl());
            Endpoint.publish("http://nutricraft-soap:8081/ws/coins", new CoinServicesImpl());
            Endpoint.publish("http://nutricraft-soap:8081/ws/emails", new EmailServicesImpl());
            System.out.println("Server is ready...");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Something went wrong....");
        }
    }
}
