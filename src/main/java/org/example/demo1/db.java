package org.example.demo1;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;

public class db {
    public static Connection connectdb(){

        try{
            Class.forName("com.postgres.jdbc.Driver");
            Connection connect = DriverManager.getConnection("jdbc:postgresql://localhost:5432/");
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
