package uk.ac.dundee.computing.djb.instagrim.models;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import uk.ac.dundee.computing.djb.instagrim.lib.AeSimpleSHA1;

/**
 *
 * @author Daniel Brereton
 * @version 1.0
 * @since 23-10-2016
 */
public class User {
    private Cluster cluster;
    
    public User() {

    }

    /**
     * Returns a boolean value signalling whether a username already exists in the database
     * and is therefore unavailable for a new user to use as their own username
     * 
     * @param username user-entered username
     * @return true (if username exists) OR false (if username doesn't exist)
     */  
    public boolean checkUsernameExists(String username) {
        //Convert parameter username to lowercase (prevents conflict between otherwise identical usernames)
        String lowercaseUsername = username.toLowerCase();
        Session session = cluster.connect("instagrim");
        //CQL statement which looks for the parameter 'username' in the userprofiles table 
        PreparedStatement ps = session.prepare("SELECT username FROM userprofiles WHERE username = ?");
        ResultSet rs = null;
        BoundStatement bs = new BoundStatement(ps);
        //execute CQL statement using lowercaseUsername
        rs = session.execute(bs.bind(lowercaseUsername));
        if (rs.isExhausted()) {
            System.out.println("username not found");
            return false;
        } else {
            System.out.println("username already exists");
            return true;
        }

    }

    /**
     * Returns a boolean value signalling whether the user hasn't entered a value
     * into the password field
     * 
     * @param password user-entered password
     * @return true (if password field is null) OR false (if password field is empty)
     */
    public boolean IsPasswordNull(String password) {
        return password.equals("");
    }

    /**
     * Returns an integer value which displays the strength of a user's password,
     * Score is increased (to a maximum of 4) for at least one instance of:
     * a lowercase letter; an uppercase letter; a number; a symbol.
     * 
     * @param password user-entered password
     * @return integer value ranging from 0-4
     *         (0 = password is too weak, 2 = weak password but valid, 4 = strong password)
     */
    public int IsPasswordStrong(String password) {

        int score = 0;

        //check password contains an uppercase letter
        if (password.matches(".*[A-Z].*")) {
            score += 1;
            System.out.println(score + "uppercase");
        }

        //check password contains a lowercase letter
        if (password.matches(".*[a-z].*")) {
            score += 1;
            System.out.println(score + "lowercase");
        }

        //check password contains a number
        if (password.matches(".*\\d.*")) {
            score += 1;
            System.out.println(score + "number");
        }

        //check password contains a symbol
        if (password.matches(".*[~!.......].*")) {
            score += 1;
            System.out.println(score + "symbol");
        }

        //display password strength score to console
        System.out.println("pw strength: " + score);
        return score;
    }

    /**
     * Returns a boolean value determining whether or not the user has
     * entered two matching values into the password and password confirmation fields
     * 
     * @param password user-entered password
     * @param confirmpassword user-entered password confirmation
     * @return true (if password does match confirmpassword) OR false (if passwords don't match)
     */
    public boolean doPasswordsMatch(String password, String confirmpassword) {
        return password.equals(confirmpassword);
    }

    /**
     * Returns a boolean value signalling whether an email already exists in the database
     * and is therefore unavailable for a new user to use as their own email
     * 
     * @param email user-entered email
     * @return true (if email exists) OR false (if email doesn't exist)
     */
    public boolean checkEmailExists(String email) {
        //check username exists, false = good, true = bad
        Session session = cluster.connect("instagrim");
        //CQL statement which looks for the parameter 'email' in the userprofiles table 
        ResultSet rs = session.execute("SELECT email FROM userprofiles");
        if (rs.isExhausted()) {
            System.out.println("email not found");
            return false;
        } else {
            for (Row row : rs) {
                //for each row in the Result Set, grab the email as a String
                String StoredEmail = row.getString("email");
                //if this grabbed email (StoredEmail) is equal to the parameter 'email'...
                if (StoredEmail.compareTo(email) == 0) {
                    //...then the email already exists
                    System.out.println("email already exists");
                    return true;
                }
            }
            return false;
        }
    }

    /**
     * Returns a boolean value signalling whether a user has been successfully registered
     * 
     * @param username user-entered username
     * @param password user-entered password
     * @param email user-entered email
     * @param first_name user-entered first name
     * @param last_name user-entered last name
     * @param address1 user-entered address
     * @param city user-entered city
     * @param country user-entered country
     * @param postcode user-entered post-code
     * @return true (when the user has been successfully registered) OR false (if the password fails to encrypt)
     */
    public boolean RegisterUser(String username, String password, String email, String first_name, String last_name, String address1, String city, String country, String postcode) {
        //Setup password encryption
        AeSimpleSHA1 sha1handler = new AeSimpleSHA1();
        String EncodedPassword = null;
        //try to encode the password
        try {
            EncodedPassword = sha1handler.SHA1(password);
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException et) {
            System.out.println("Can't check your password");
            return false;
        }
        Session session = cluster.connect("instagrim");
        //CQL statement which inserts the above parameters into the userprofiles table to create a new user account
        PreparedStatement ps = session.prepare("insert into userprofiles (username, password, email, first_name, last_name, address1, city, country, postcode) Values(?,?,?,?,?,?,?,?,?)");
        BoundStatement boundStatement = new BoundStatement(ps);
        session.execute(
        boundStatement.bind(username, EncodedPassword, email, first_name, last_name, address1, city, country, postcode)
        );
        //We are assuming this always works.  Also a transaction would be good here !
        return true;
    }
    
    /**
     * 
     * @param email
     * @param first_name
     * @param last_name
     * @param country
     * @param username
     * @return 
     */
    public boolean updateUserInfo(String email, String first_name, String last_name, String country, String username){
        Session session = cluster.connect("instagrim");
        //CQL statement which updates the above parameters into the userprofiles table to create a new user account
        PreparedStatement ps = session.prepare("update userprofiles SET email = ?, first_name = ?, last_name = ?, country = ? WHERE username = ?");
        BoundStatement boundStatement = new BoundStatement(ps);
        session.execute(
        boundStatement.bind(email, first_name, last_name, country, username)
        );
        //We are assuming this always works
        return true;
    }
    
    public String[] getUserInfo(String username){
       
        String[] userInfo = new String[]{"","","",""};
        Session session = cluster.connect("instagrim");
        //CQL statement which looks for the parameter 'username' in the userprofiles table 
        PreparedStatement ps = session.prepare("SELECT email, first_name, last_name, country FROM userprofiles WHERE username = ?");        
        ResultSet rs = null;
        BoundStatement bs = new BoundStatement(ps);
        //execute CQL statement uing username
        rs = session.execute(bs.bind(username));
        if (rs.isExhausted()) {
            System.out.println("user not found");
            return userInfo;
        } else {
            for (Row row : rs) {
                //for each row in the Result Set, grab the details as a String
                String StoredEmail = row.getString("email");
                String StoredFirstName = row.getString("first_name");
                String StoredLastName = row.getString("last_name");
                String StoredCountry = row.getString("country");
                
                
                userInfo[0] = StoredFirstName;
                userInfo[1] = StoredLastName;
                userInfo[2] = StoredCountry;
                userInfo[3] = StoredEmail;
                    return userInfo;
                }
            }
        return userInfo;
        }
       
    /**
     * Returns a boolean value which enables the user to log in
     * 
     * @param username current username
     * @param password current user's password
     * @return boolean value 
     */
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
        //CQL Statement which uses the password and username parameters as an indicator of which record to select
        PreparedStatement ps = session.prepare("select password from userprofiles where username =?");
        ResultSet rs = null;
        BoundStatement boundStatement = new BoundStatement(ps);
        //Execute CQL statement using the username parameter as the value
        rs = session.execute(
                boundStatement.bind(username)
        );
        if (rs.isExhausted()) {
            System.out.println("No Images returned");
            return false;
        } else {
            //Compare each row's password against the encoded password
            for (Row row : rs) {         
                String StoredPass = row.getString("password");
                if (StoredPass.compareTo(EncodedPassword) == 0) {
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * Returns a boolean value confirming the deletion of a specific user account
     * from the userprofiles table
     * 
     * @param username username of the account which is to be deleted
     * @return boolean value true (if account is successfully removed)
     */
    public boolean removeUser(String username) {
        Session session = cluster.connect("instagrim");
        //CQL Statement which uses the username parameter as an indicator of which record to delete from the userprofiles table
        PreparedStatement ps = session.prepare("delete from userprofiles where username =?");
        BoundStatement bs = new BoundStatement(ps);
        //Execute CQL statement using the username parameter as the value
        session.execute(bs.bind(username));
        return true;
    }

    /**
     * Sets the Cassandra cluster
     * 
     * @param cluster 
     */
    public void setCluster(Cluster cluster) {
        this.cluster = cluster;
    }

}
