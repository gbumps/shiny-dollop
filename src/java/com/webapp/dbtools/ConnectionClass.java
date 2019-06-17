/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webapp.dbtools;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author stephen
 */
public class ConnectionClass implements Serializable{
	 private static Connection c;
	 
   public static Connection GetConnection() throws SQLException,ClassNotFoundException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url = "jdbc:sqlserver://localhost:1433;databasename=Products;user=sa;password=Thong841269@";
        c = DriverManager.getConnection(url);
        return c;
        //return an instance of database connection
        //throw Exception if database resource not found
    }
}
