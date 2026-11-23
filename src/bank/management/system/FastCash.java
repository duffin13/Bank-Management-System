package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Date;

/**
 * FastCash - lets the user withdraw a preset amount with one click.
 * Checks the balance first, then records the withdrawal in the bank table.
 */
public class FastCash extends JFrame implements ActionListener {

    // One button per preset amount, plus a back button
    JButton cash10, cash20, cash50, cash100, cash200, cash500, back;
    // The logged-in user's pin, used to find their rows in the bank table
    String pinnumber;

    FastCash(String pinnumber) {

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
        JLabel text = new JLabel("SELECT WITHDRAWAL AMOUNT");
        text.setBounds(235, 300, 700, 35);
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System", Font.BOLD, 16));
        image.add(text);

        // ----- Amount buttons: two per row -----
        cash10 = new JButton("€10");
        cash10.setBounds(170, 450, 150, 30);
        cash10.addActionListener(this);
        image.add(cash10);

        cash20 = new JButton("€20");
        cash20.setBounds(355, 450, 150, 30);
        cash20.addActionListener(this);
        image.add(cash20);

        cash50 = new JButton("€50");
        cash50.setBounds(170, 485, 150, 30);
        cash50.addActionListener(this);
        image.add(cash50);

        cash100 = new JButton("€100");
        cash100.setBounds(355, 485, 150, 30);
        cash100.addActionListener(this);
        image.add(cash100);

        cash200 = new JButton("€200");
        cash200.setBounds(170, 520, 150, 30);
        cash200.addActionListener(this);
        image.add(cash200);

        cash500 = new JButton("€500");
        cash500.setBounds(355, 520, 150, 30);
        cash500.addActionListener(this);
        image.add(cash500);

        // ----- Back button -----
        back = new JButton("BACK");
        back.setBounds(355, 560, 150, 30);
        back.addActionListener(this);
        image.add(back);

        // ----- Window settings -----
        setSize(900, 900);
        setLocationRelativeTo(null); // centre the window on screen
        setUndecorated(true);        // no title bar, so it looks like an ATM screen
        setVisible(true);
    }

    // Runs when any button is clicked
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == back) {
            // Return to the main transactions menu
            setVisible(false);
            new Transactions(pinnumber).setVisible(true);
        } else {
            // The button text is e.g. "€50", so drop the € to get "50"
            String amount = ((JButton) ae.getSource()).getText().substring(1);
            Conn c = new Conn();
            try {
                // Work out the current balance: deposits add, withdrawals subtract
                ResultSet rs = c.s.executeQuery("select * from bank where pin = '" + pinnumber + "'");
                int balance = 0;
                while (rs.next()) {
                    if (rs.getString("type").equals("Deposit")) {
                        balance += Integer.parseInt(rs.getString("amount"));
                    } else {
                        balance -= Integer.parseInt(rs.getString("amount"));
                    }
                }

                // Refuse the withdrawal if the balance doesn't cover it
                if (balance < Integer.parseInt(amount)) {
                    JOptionPane.showMessageDialog(null, "Insufficient Balance");
                    return;
                }

                // Record the withdrawal: pin, date, type, amount
                Date date = new Date();
                String query = "insert into bank values ('" + pinnumber + "', '" + date + "', 'Withdrawal', '" + amount + "')";
                c.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "€" + amount + " Debited Successfully");

                // Go back to the main menu
                setVisible(false);
                new Transactions(pinnumber).setVisible(true);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    // Entry point for testing this screen on its own
    public static void main(String args[]) {
        new FastCash("");
    }
}