package org.example.demo1;

import java.sql.Connection;
import java.sql.DriverManager;

public class db {
    public static void connectdb(){

        try{
            Class.forName("com.postgres.jdbc.Driver");
            Connection connect = DriverManager.getConnection("jdbc:postgresql://localhost:5432/");
        } catch(Exception e){
            e.printStackTrace();
        }
        return;
    }
}
