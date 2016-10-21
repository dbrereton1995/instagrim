/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.ac.dundee.computing.aec.instagrim.models;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import uk.ac.dundee.computing.aec.instagrim.lib.AeSimpleSHA1;
import uk.ac.dundee.computing.aec.instagrim.stores.*;

/**
 *
 * @author Administrator
 */
public class User {

    private Cluster cluster;
    
    

    public User() {
        
        
    }

  
    
    
    public boolean checkUsernameExists(String username) {
        String lowercaseUsername = username.toLowerCase();
        //check username exists, false = good, true = bad
        Session session = cluster.connect("instagrim");
        PreparedStatement ps = session.prepare("SELECT username FROM userprofiles WHERE username = ?");
        ResultSet rs = null;
        BoundStatement bs = new BoundStatement(ps);
        rs = session.execute(bs.bind(lowercaseUsername));
        if (rs.isExhausted()) {
            System.out.println("username not found");
            return false;

        } else {
            System.out.println("username already exists");
            return true;

        }

    }

    public boolean IsPasswordNull(String password) {
        return password.equals("");
    }

    public int IsPasswordStrong(String password) {

        int score = 0;

        if (password.matches(".*[A-Z].*")) {
            score += 1;
            System.out.println(score + "uppercase");
        }

        if (password.matches(".*[a-z].*")) {
            score += 1;
            System.out.println(score + "lowercase");
        }

        if (password.matches(".*\\d.*")) {
            score += 1;
            System.out.println(score + "number");
        }

        if (password.matches(".*[~!.......].*")) {
            score += 1;
            System.out.println(score + "symbol");
        }

        System.out.println("pw strength: " + score);
        return score;
    }

    public boolean doPasswordsMatch(String password, String confirmpassword) {
        System.out.println(password);
        System.out.println(confirmpassword);
        return password.equals(confirmpassword);
    }

    public boolean checkEmailExists(String email) {
        //check username exists, false = good, true = bad
        Session session = cluster.connect("instagrim");

        ResultSet rs = session.execute("SELECT email FROM userprofiles");
        if (rs.isExhausted()) {
            System.out.println("email not found");
            return false;

        } else {
            for (Row row : rs) {

                String StoredEmail = row.getString("email");
                if (StoredEmail.compareTo(email) == 0) {

                    System.out.println("email already exists");
                    return true;
                }

            }
            return false;
        }
    }

    public boolean RegisterUser(String username, String password, String email, String first_name, String last_name, String address1, String city, String country, String postcode) {
        AeSimpleSHA1 sha1handler = new AeSimpleSHA1();
        String EncodedPassword = null;
        try {
            EncodedPassword = sha1handler.SHA1(password);
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException et) {
            System.out.println("Can't check your password");
            return false;
        }
        Session session = cluster.connect("instagrim");
        PreparedStatement ps = session.prepare("insert into userprofiles (username, password, email, first_name, last_name, address1, city, country, postcode) Values(?,?,?,?,?,?,?,?,?)");

        BoundStatement boundStatement = new BoundStatement(ps);
        session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        username, EncodedPassword, email, first_name, last_name, address1, city, country, postcode));
        //We are assuming this always works.  Also a transaction would be good here !

        return true;
    }

    public boolean IsValidUser(String username, String password) {
        AeSimpleSHA1 sha1handler = new AeSimpleSHA1();
        String EncodedPassword = null;
        try {
            EncodedPassword = sha1handler.SHA1(password);
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException et) {
            System.out.println("Can't check your password");
            return false;
        }
        Session session = cluster.connect("instagrim");
        PreparedStatement ps = session.prepare("select password from userprofiles where username =?");
        ResultSet rs = null;
        BoundStatement boundStatement = new BoundStatement(ps);
        rs = session.execute( // this is where the query is executed
                boundStatement.bind( // here you are binding the 'boundStatement'
                        username));
        if (rs.isExhausted()) {
            System.out.println("No Images returned");
            return false;
        } else {
            for (Row row : rs) {

                String StoredPass = row.getString("password");
                if (StoredPass.compareTo(EncodedPassword) == 0) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean removeUser(String username){
        Session session = cluster.connect("instagrim");
        PreparedStatement ps = session.prepare("delete from userprofiles where username =?");
        BoundStatement bs = new BoundStatement(ps);
        session.execute(bs.bind(username));
        return true;
    }
    
    public void setCluster(Cluster cluster) {
        this.cluster = cluster;
    }

}
