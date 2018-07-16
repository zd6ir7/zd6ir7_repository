/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author haibou
 */
public class MySQLFalseDBAccess {
    public static void main(String[] args) {
        System.out.println("***** main method start *****");
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        
        try {
            String url = "jdbc:mysql://localhost/mydb";
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, "detarame", "detarame");
            
            // Statementを用意する。
            statement = connection.createStatement();
            // SQLを宣言する。可変値idとpasswordを入れてSQLを構成する。
            String sql = "select * from usertable where id = 11111 and password = '' OR 'A' = 'A'";
            // 結果を取得する。
            resultSet = statement.executeQuery(sql);
            
            while(resultSet.next()) {
                System.out.println("ID：　" + resultSet.getInt(1));
                System.out.println("氏名：　" + resultSet.getString(3));
                System.out.println("メールアドレス：　" + resultSet.getString(4));
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(FalseDBAccess.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
                try {
                    if(resultSet != null) { resultSet.close();}
                    if(statement != null) { statement.close();}
                    if(connection != null) { connection.close();}
                    System.out.println("***** main method end *****");
                } catch(SQLException sqle) {
                    Logger.getLogger(FalseDBAccess.class.getName()).log(Level.SEVERE, null, sqle);
                }
            }
        }
        
    }

