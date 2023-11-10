package org.nutricraft.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Database {

    protected Connection connection;

    public Database(){
        try{
            String url = System.getenv("DATABASE_URL");
            String username = System.getenv("DB_USER");
            String password = System.getenv("DB_PASSWORD");
            System.out.println("url: " + url);
            this.connection = DriverManager.getConnection(url, username, password);

            System.out.println("Connected to database");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Something went wrong when connecting to database");
        }
    }
    
    public Connection getConn(){
        return connection;
    }

}
