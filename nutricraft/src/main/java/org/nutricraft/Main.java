package org.nutricraft;

import org.nutricraft.Services.SubscriptionServicesImplement;

import javax.xml.ws.Endpoint;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        try {
            Endpoint.publish("http://localhost:8081/ws/subscription", new SubscriptionServicesImplement());
            System.out.println("iiiininimimimmi");
        }catch (Exception e){
            System.out.println(e);
            System.out.println("woiii");
        }
    }
}