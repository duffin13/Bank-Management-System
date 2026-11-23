package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Date;

/**
 * Deposit - lets the user type an amount and add it to their account.
 * The deposit is recorded as a new row in the bank table.
 */
public class Deposit extends JFrame implements ActionListener {

    // Amount input and the two buttons
    JTextField amount;
    JButton deposit, back;
    // The logged-in user's pin, used to tag the deposit with their account
    String pinnumber;

    Deposit(String pinnumber) {
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

        // ----- Heading (added to the image so it shows on top of it) -----
        JLabel text = new JLabel("Enter the amount you want to deposit");
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System", Font.BOLD, 16));
        text.setBounds(170, 300, 400, 20);
        image.add(text);

        // ----- Amount input -----
        amount = new JTextField();
        amount.setFont(new Font("Raleway", Font.BOLD, 22));
        amount.setBounds(170, 350, 320, 25);
        image.add(amount);

        // ----- Buttons -----
        deposit = new JButton("Deposit");
        deposit.setBounds(355, 530, 150, 30);
        deposit.addActionListener(this); // clicking calls actionPerformed
        image.add(deposit);

        back = new JButton("Back");
        back.setBounds(355, 565, 150, 30);
        back.addActionListener(this);
        image.add(back);

        // ----- Window settings -----
        setSize(900, 900);
        setLocationRelativeTo(null); // centre the window on screen
        setUndecorated(true);        // no title bar, so it looks like an ATM screen
        setVisible(true);
    }

    // Runs when either button is clicked
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == deposit) {
            String number = amount.getText();
            Date date = new Date();

            // Make sure something was typed
            if (number.equals("")) {
                JOptionPane.showMessageDialog(null, "Please enter the amount you want to deposit");
            } else {
                try {
                    // Record the deposit: pin, date, type, amount
                    Conn conn = new Conn();
                    String query = "insert into bank values('" + pinnumber + "', '" + date + "', 'Deposit', '" + number + "')";
                    conn.s.executeUpdate(query);
                    JOptionPane.showMessageDialog(null, "€" + number + " Deposited Successfully");

                    // Go back to the main menu
                    setVisible(false);
                    new Transactions(pinnumber).setVisible(true);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        } else if (ae.getSource() == back) {
            // Return to the main transactions menu
            setVisible(false);
            new Transactions(pinnumber).setVisible(true);
        }
    }

    // Entry point for testing this screen on its own
    public static void main(String args[]) {
        new Deposit("");
    }
}