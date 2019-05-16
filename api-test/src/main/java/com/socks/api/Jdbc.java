package com.socks.api;

import com.lukspay.api.utils.*;
import java.sql.*;

/**
 * @author Vitalii Smokov 05.02.2019
 */
public class Jdbc {

  public static void main(String[] args) throws SQLException {
//    String url="jdbc:mysql://lp-mysql-dev.clj9ptckan5v.eu-central-1.rds.amazonaws.com:3306/";
//    String username="qa";
//    String password="nvQdzyStTSETG7yk8UnHQCUU";
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
//      Connection connection= DriverManager.getConnection(url, username, password);
//      Statement statement=connection.createStatement();
//      ResultSet resultSet=statement.executeQuery(
//          "SELECT * FROM lukspay_user.lp_user_identity WHERE user_email='Rer5t5Mlkyle.mcdermott@hotmail.com';");
//      while (resultSet.next()){
//        for (int i = 1; i < 8; i++) {
//          System.out.println(resultSet.getString(i));
//        }
//      }
//      resultSet.close();
//      statement.close();
//      connection.close();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    ResultSet resultSet = new DbUtil()
        .executeQuery(
            "SELECT * FROM lukspay_user.lp_user_identity WHERE user_email='Rer5t5Mlkyle.mcdermott@hotmail.com';");
    System.out.println(resultSet.getString(1));
  }

}
