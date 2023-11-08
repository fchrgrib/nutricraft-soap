package org.nutricraft.Database;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {



    public Database(){
        try{
            String url = System.getenv("DATABASE_URL");
            String username = System.getenv("DB_USER");
            String password = System.getenv("DB_PASSWORD");
            Connection connection = DriverManager.getConnection(url, username, password);

            System.out.println("create database");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Something went wrong.....");
        }
    }
    
}
