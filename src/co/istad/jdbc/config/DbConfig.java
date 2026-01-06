package co.istad.jdbc.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConfig {

    //Create singleton object
    private static Connection conn;

    public static Connection getInstance(){
        return conn;
    }

    //Initialize singleton object (Only 1 time)
    public static void init(){
        if (conn == null){
            try{
                Class.forName("org.postgresql.Driver");

                String url = "jdbc:postgresql://localhost:5432/day2";
                String user = "ichigo";
                String password = "11020315";
                conn = DriverManager.getConnection(url , user, password);
            }catch (ClassNotFoundException e){
                System.out.print("Class " + e.getMessage() + " Not Found.");
            }catch (SQLException e){
                System.out.println("SQL Error: " + e.getMessage());
            }
        }
    }
}
