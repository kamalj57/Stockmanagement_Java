package com.example.demo1;
import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
   public Connection databaseLink;

    public Connection getConnection () {
        String dbuser= "root";
        String dbpassword="@20731Jul#jk";
        String url="jdbc:mysql://localhost:3306/sky";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url,dbuser,dbpassword);
        } catch (Exception e){
            e.printStackTrace();
        }
        return databaseLink;
    }
}
