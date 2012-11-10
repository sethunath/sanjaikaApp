/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package banking;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sethu
 */
public class DBConnect {

    private static final String accessDBURLPrefix = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ=";
    private static final String accessDBURLSuffix = ";DriverID=22;READONLY=false}";
    Connection con;
    Statement stmt;
    public DBConnect() {
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        } catch(ClassNotFoundException e) {
            System.err.println("JdbcOdbc Bridge Driver not found!");
        }
        try {
            con = DBConnect.getAccessDBConnection("bank.mdb");
            stmt = con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static Connection getAccessDBConnection(String filename) throws SQLException {
        filename = filename.trim();
        String databaseURL = accessDBURLPrefix + filename + accessDBURLSuffix;
        return DriverManager.getConnection(databaseURL, "", "");
    }
    public void close(){
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
