package org.nutricraft.Model;

import org.nutricraft.Database.Database;

import java.sql.Statement;

public class LogModel extends Database {

    public LogModel() {
        super();
    }

    public String InsertLog(String desc, String endpoint, String ip, String requested_at) {
        try {
            Statement statement = this.connection.createStatement();
            String query = "INSERT INTO logging (description, endpoint, IP, requested_at) VALUES ('" + desc + "', '" + endpoint + "', '" + ip + "','" + requested_at +"')";
            statement.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to insert log";
        }
        return "Successfully inserted log";
    }

}
