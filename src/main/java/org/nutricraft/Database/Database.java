package org.nutricraft.Database;
import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {



    public Database(){
        try{
            Dotenv dotenv = Dotenv.load();
            String url = dotenv.get("DATABASE_URL_SOAP");
            String username = dotenv.get("DB_USER");
            String password = dotenv.get("DB_PASSWORD");
            String dbname = dotenv.get("DB_NAME");
            String port = dotenv.get("DB_PORT_SOAP");
            System.out.println(url);
            System.out.println(username);
            System.out.println(password);
            System.out.println(dbname);
            System.out.println(port);
            Connection connection = DriverManager.getConnection(url, username, password);

            System.out.println("create database");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Something went wrong.....");
        }
    }
    
}
