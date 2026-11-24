package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

// Shows the card number, a list of transactions and the balance for one pin
public class MiniStatement extends JFrame {

    MiniStatement(String pinnumber) {
        setTitle("Mini Statement");

        setLayout(null); // manual positioning with setBounds

        // Label that will hold the list of transactions (filled in below)
        JLabel mini = new JLabel();
        add(mini);

        // Bank name at the top
        JLabel bank = new JLabel("Irish Bank");
        bank.setBounds(150, 20, 100, 20);
        add(bank);

        // Masked card number (text set from the database below)
        JLabel card = new JLabel();
        card.setBounds(20, 80, 300, 20);
        add(card);

        // Balance at the bottom (text set from the database below)
        JLabel balance = new JLabel();
        balance.setBounds(20, 400, 300, 20);
        add(balance);

        // ----- Get the card number from the login table -----
        try {
            Conn conn = new Conn();
            ResultSet rs = conn.s.executeQuery("select * from login where pin = '"+pinnumber+"'");
            while(rs.next()) {
                // Show first 4 and last 4 digits only, hide the middle
                card.setText("Card Number: " + rs.getString("cardnumber").substring(0, 4) + "XXXXXXXX" +  rs.getString("cardnumber").substring(12));
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        // ----- Get the transactions and work out the balance -----
        try {
            Conn conn = new Conn();
            int bal = 0;
            ResultSet rs = conn.s.executeQuery("select * from bank where pin = '"+pinnumber+"'");
            while(rs.next()) {
                // Add one line (date, type, amount) to the transaction list
                mini.setText(mini.getText() + "<html>" + rs.getString("date") + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + rs.getString("type") + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + rs.getString("amount") + "<br><br><html>");
                // Deposits add to the balance, anything else subtracts
                if (rs.getString("type").equals("Deposit")) {
                    bal += Integer.parseInt(rs.getString("amount"));
                } else {
                    bal-= Integer.parseInt(rs.getString("amount"));
                }
            }
            balance.setText("Your current account balance is € " + bal);
        } catch (Exception e) {
            System.out.println(e);
        }

        mini.setBounds(20, 140, 400, 200); // position the transaction list

        // ----- Window settings -----
        setSize(400, 600);
        setLocationRelativeTo(null); // centre the window on screen
        getContentPane().setBackground(Color.WHITE);
        setVisible(true);
    }

    // Entry point for testing this screen on its own
    public static void main(String args[]) {
        new MiniStatement("");
    }

}