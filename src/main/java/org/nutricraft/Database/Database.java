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

    public void InsertLog(String desc, String endpoint, String ip, String requested, String requested_at) {
        try {
            Statement statement = this.connection.createStatement();
            String query = "INSERT INTO logging (description, endpoint, IP, request, requested_at) VALUES ('" + desc + "', '" + endpoint + "', '" + ip + "','"+ requested+ "','" + requested_at +"')";
            statement.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to insert log");
        }
        System.out.println("Successfully inserted log");
    }

}
