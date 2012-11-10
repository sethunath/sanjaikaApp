/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package banking;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sethu
 */
public class Model {
    DBConnect dbcon;
    boolean validateUsername(String username){
        dbcon = new DBConnect();
        ResultSet rs = null;
        boolean val= false;
        try {
            rs = dbcon.stmt.executeQuery("select * from users where username = '" + username + "'");
            val = rs.next();
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return val;
    }
    public void transaction(String username,int amount){
        String sql = "insert into transaction(username,amount) values ('"+username+"',"+amount+")";
        dbcon = new DBConnect();
        try {
            dbcon.stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public ResultSet getTransaction(String username){
        String sql = "select * from transaction where username ='"+username+"'";
        dbcon = new DBConnect();
        ResultSet rs = null;
        try {
            rs = dbcon.stmt.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return rs;
    }
    public String myBalance(String username){
        String sql = "select sum(amount) from transaction where username ='"+username+"'";
        dbcon = new DBConnect();
        ResultSet rs = null;
        try {
            rs = dbcon.stmt.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            rs.next();
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        String bal = "0";
        try {
            bal = rs.getString(1);
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bal;
    }
    public void addNewUser(String username,String password){
        String sql = "insert into users(username,password) values ('"+username+"','"+password+"')";
        dbcon = new DBConnect();
        try {
            dbcon.stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        dbcon.close();
    }
    public void changePassword(String username,String password){
        String sql = "update users set password='"+password+"' where username='"+username+"'";
        dbcon = new DBConnect();
        try {
            dbcon.stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        dbcon.close();
    }
    public ResultSet getUsernamesStartsWith(String username){
        String sql = "select * from users where username like'"+username+"%'";
        dbcon = new DBConnect();
        ResultSet rs = null;
        try {
            rs = dbcon.stmt.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return rs;
    }
}