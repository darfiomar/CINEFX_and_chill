package controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnection {
	final String host = "localhost";
	final String user = "root";
	final String passwd = "";
	final String dbname = "cinefx";
	
	public Connection getConnection(){
		Connection connect = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://"+host+"/"+dbname+"?user="+user+"&password="+passwd+"&characterEncoding=utf8&useSSL=false");
 
        } catch (ClassNotFoundException e) {
 
            e.printStackTrace();
             
        } catch (SQLException e) {
             
            e.printStackTrace();
             
        }
        
		return connect;
    }
}
