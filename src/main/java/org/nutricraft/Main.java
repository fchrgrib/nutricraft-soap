package org.nutricraft;

import org.nutricraft.Database.Database;
import org.nutricraft.Services.SubscriptionServicesImplement;
import java.lang.reflect.Field;
import javax.xml.ws.Endpoint;

public class Main {
    public static void main(String[] args) {
        try {

            Endpoint.publish("http://nutricraft-soap:8081/ws/subscription", new SubscriptionServicesImplement());
            Database a = new Database();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("woiii");
        }
    }
}
