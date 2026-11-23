package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

/**
 * BalanceEnquiry - shows the user's current account balance.
 * The balance is calculated from every row in the bank table for their pin.
 */
public class BalanceEnquiry extends JFrame implements ActionListener {

    // Only one button on this screen
    JButton back;
    // The logged-in user's pin, used to find their rows in the bank table
    String pinnumber;

    BalanceEnquiry(String pinnumber) {
        this.pinnumber = pinnumber;

        // null layout = every component is positioned manually with setBounds
        setLayout(null);

        // ----- Background image (ATM screen) -----
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 900);
        add(image);

        // ----- Back button (added to the image so it shows on top of it) -----
        back = new JButton("Back");
        back.setBounds(355, 565, 150, 30);
        back.addActionListener(this); // clicking calls actionPerformed
        image.add(back);

        // ----- Work out the balance from the database -----
        Conn c = new Conn();
        int balance = 0;
        try {
            // Deposits add to the balance, withdrawals subtract from it
            ResultSet rs = c.s.executeQuery("select * from bank where pin = '" + pinnumber + "'");
            while (rs.next()) {
                if (rs.getString("type").equals("Deposit")) {
                    balance += Integer.parseInt(rs.getString("amount"));
                } else {
                    balance -= Integer.parseInt(rs.getString("amount"));
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        // ----- Show the balance on screen -----
        JLabel text = new JLabel("Your Current Account balance is €" + balance);
        text.setForeground(Color.WHITE);
        text.setBounds(170, 345, 400, 30);
        image.add(text);

        // ----- Window settings -----
        setSize(900, 900);
        setLocationRelativeTo(null); // centre the window on screen
        setUndecorated(true);        // no title bar, so it looks like an ATM screen
        setVisible(true);
    }

    // Back is the only button, so any click returns to the main menu
    public void actionPerformed(ActionEvent ae) {
        setVisible(false);
        new Transactions(pinnumber).setVisible(true);
    }

    // Entry point for testing this screen on its own
    public static void main(String args[]) {
        new BalanceEnquiry("");
    }
}