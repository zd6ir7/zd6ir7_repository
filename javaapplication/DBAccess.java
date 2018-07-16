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
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author haibou
 */
public class DBAccess {
    public static void main(String[] args) {
        System.out.println("***** main method start *****");
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        try {
            String url = "jdbc:derby://localhost:1527/mydb";
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            connection = DriverManager.getConnection(url, "detarame", "detarame");
            
            // SQLを宣言する。カラムidとpasswordに対して「?」を入れることで、これらはバインド変数であることを宣言する。
            String sql = "select * from app.usertable where id = ? and password = ?";
            // バインド変数を格納したSQLを発行するため、PreparedStatementを用意する。
            preparedStatement = connection.prepareStatement(sql);
            // idに対して値を代入する。この「1」というのは、1番目のバインド変数idを指す。
            preparedStatement.setInt(1, 11111);
            // passwordに対して値を代入する。この「2」というのは、2番目のバインド変数passwordを指す。
            preparedStatement.setString(2, "'' OR 'A' = 'A'");
            // 結果を取得する。
            resultSet = preparedStatement.executeQuery();
            
            while(resultSet.next()) {
                System.out.println("ID：　" + resultSet.getInt(1));
                System.out.println("氏名：　" + resultSet.getString(3));
                System.out.println("メールアドレス：　" + resultSet.getString(4));
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
                try {
                    if(resultSet != null) { resultSet.close();}
                    if(preparedStatement != null) { preparedStatement.close();}
                    if(connection != null) { connection.close();}
                    System.out.println("***** main method end *****");
                } catch(SQLException sqle) {
                    Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null, sqle);
                }
            }
        }
        
    }

