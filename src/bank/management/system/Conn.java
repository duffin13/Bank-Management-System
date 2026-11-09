package bank.management.system;

import java.sql.*;
/**
 *
 * @author Matthew Duffin
 */
public class Conn {
    
    Connection c;
    Statement s;
    
    public Conn() {
        try {

            c = DriverManager.getConnection("jdbc:mysql:///bankmanagementsystem", "root", "javaproj");
            s = c.createStatement();
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
}
