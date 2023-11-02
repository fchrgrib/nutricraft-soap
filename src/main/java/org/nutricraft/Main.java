package org.nutricraft;


import org.nutricraft.Database.Database;
import org.nutricraft.Services.SubscriptionServicesImplement;

import javax.xml.ws.Endpoint;

//import javax.xml.ws.Endpoint;


// import javax.xml.ws.Endpoint;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        try {
//            Endpoint.publish("http://localhost:8081/ws/subscription", new SubscriptionServicesImplement());
            Database a = new Database();

        }catch (Exception e){
            e.printStackTrace();
            System.out.println("woiii");
        }
    }
}