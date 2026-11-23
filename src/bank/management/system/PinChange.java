package bank.management.system;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * PinChange - lets the user set a new pin.
 * Updates the pin in the bank, login and signup3 tables.
 */
public class PinChange extends JFrame implements ActionListener {

    // New pin and re-entered pin (password fields hide the typed characters)
    JPasswordField t1, t2;
    JButton b1, b2; // b1 = change, b2 = back
    JLabel l1, l2, l3;
    // The current pin, used to find this user's rows in the tables
    String pinnumber;

    PinChange(String pinnumber) {
        this.pinnumber = pinnumber;

        // null layout = every component is positioned manually with setBounds
        setLayout(null);

        // ----- Background image (ATM screen) -----
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(960, 1080, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l4 = new JLabel(i3);
        l4.setBounds(0, 0, 960, 1080);
        add(l4);

        // ----- Labels (added to the image so they show on top of it) -----
        l1 = new JLabel("CHANGE YOUR PIN");
        l1.setFont(new Font("System", Font.BOLD, 16));
        l1.setForeground(Color.WHITE);

        l2 = new JLabel("New PIN:");
        l2.setFont(new Font("System", Font.BOLD, 16));
        l2.setForeground(Color.WHITE);

        l3 = new JLabel("Re-Enter New PIN:");
        l3.setFont(new Font("System", Font.BOLD, 16));
        l3.setForeground(Color.WHITE);

        // ----- Pin input fields -----
        t1 = new JPasswordField();
        t1.setFont(new Font("Raleway", Font.BOLD, 25));

        t2 = new JPasswordField();
        t2.setFont(new Font("Raleway", Font.BOLD, 25));

        // ----- Buttons -----
        b1 = new JButton("CHANGE");
        b2 = new JButton("BACK");

        b1.addActionListener(this); // clicking calls actionPerformed
        b2.addActionListener(this);

              // ----- Positions -----
        l1.setBounds(280, 375, 800, 35);
        l4.add(l1);

        l2.setBounds(180, 435, 150, 35);
        l4.add(l2);

        l3.setBounds(180, 485, 200, 35);
        l4.add(l3);

        t1.setBounds(350, 435, 180, 25);
        l4.add(t1);

        t2.setBounds(350, 485, 180, 25);
        l4.add(t2);

        b1.setBounds(390, 633, 150, 35);
        l4.add(b1);

        b2.setBounds(390, 678, 150, 35);
        l4.add(b2);

        // ----- Window settings -----
        setSize(960, 1080);
        setLocation(500, 0);
        setLocationRelativeTo(null); // centre the window on screen
        setUndecorated(true); // no title bar, so it looks like an ATM screen
        setVisible(true);
    }

    // Runs when either button is clicked
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b1) {
            try {
                // Read both pins (getPassword is the non-deprecated way)
                String npin = new String(t1.getPassword());
                String rpin = new String(t2.getPassword());

                // Validate: both filled in, and both the same
                if (npin.equals("")) {
                    JOptionPane.showMessageDialog(null, "Enter New PIN");
                    return;
                }
                if (rpin.equals("")) {
                    JOptionPane.showMessageDialog(null, "Re-Enter new PIN");
                    return;
                }
                if (!npin.equals(rpin)) {
                    JOptionPane.showMessageDialog(null, "Entered PIN does not match");
                    return;
                }

                // Update the pin everywhere it is stored
                Conn c1 = new Conn();
                String q1 = "update bank set pin = '" + rpin + "' where pin = '" + pinnumber + "' ";
                String q2 = "update login set pin = '" + rpin + "' where pin = '" + pinnumber + "' ";
                String q3 = "update signupthree set pin = '" + rpin + "' where pin = '" + pinnumber + "' ";

                c1.s.executeUpdate(q1);
                c1.s.executeUpdate(q2);
                c1.s.executeUpdate(q3);

                JOptionPane.showMessageDialog(null, "PIN changed successfully");

                // Back to the main menu, now using the new pin
                setVisible(false);
                new Transactions(rpin).setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == b2) {
            // Return to the main menu with the unchanged pin
            setVisible(false);
            new Transactions(pinnumber).setVisible(true);
        }
    }

    // Entry point for testing this screen on its own
    public static void main(String[] args) {
        new PinChange("");
    }
}