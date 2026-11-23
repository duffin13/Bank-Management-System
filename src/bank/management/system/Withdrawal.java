package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Date;

/**
 * Withdrawal - lets the user type an amount and take it out of their account.
 * Checks the balance first, then records the withdrawal in the bank table.
 */
public class Withdrawal extends JFrame implements ActionListener {

    // Amount input and the two buttons
    JTextField amount;
    JButton withdraw, back;
    // The logged-in user's pin, used to find their rows in the bank table
    String pinnumber;

    Withdrawal(String pinnumber) {
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
        JLabel text = new JLabel("Enter the amount you want to withdraw");
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
        withdraw = new JButton("Withdraw");
        withdraw.setBounds(355, 530, 150, 30);
        withdraw.addActionListener(this); // clicking calls actionPerformed
        image.add(withdraw);

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
        if (ae.getSource() == withdraw) {
            String number = amount.getText();

            // Make sure something was typed
            if (number.equals("")) {
                JOptionPane.showMessageDialog(null, "Please enter the amount you want to withdraw");
                return;
            }

            try {
                Conn conn = new Conn();

                // Work out the current balance: deposits add, withdrawals subtract
                ResultSet rs = conn.s.executeQuery("select * from bank where pin = '" + pinnumber + "'");
                int balance = 0;
                while (rs.next()) {
                    if (rs.getString("type").equals("Deposit")) {
                        balance += Integer.parseInt(rs.getString("amount"));
                    } else {
                        balance -= Integer.parseInt(rs.getString("amount"));
                    }
                }

                // Refuse the withdrawal if the balance doesn't cover it
                if (balance < Integer.parseInt(number)) {
                    JOptionPane.showMessageDialog(null, "Insufficient Balance");
                    return;
                }

                // Record the withdrawal: pin, date, type, amount
                Date date = new Date();
                String query = "insert into bank values('" + pinnumber + "', '" + date + "', 'Withdrawal', '" + number + "')";
                conn.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "€" + number + " Debited Successfully");

                // Go back to the main menu
                setVisible(false);
                new Transactions(pinnumber).setVisible(true);
            } catch (NumberFormatException e) {
                // The amount wasn't a whole number, e.g. "abc" or "10.5"
                JOptionPane.showMessageDialog(null, "Please enter a valid whole number");
            } catch (Exception e) {
                System.out.println(e);
            }
        } else if (ae.getSource() == back) {
            // Return to the main transactions menu
            setVisible(false);
            new Transactions(pinnumber).setVisible(true);
        }
    }

    // Entry point for testing this screen on its own
    public static void main(String args[]) {
        new Withdrawal("");
    }
}