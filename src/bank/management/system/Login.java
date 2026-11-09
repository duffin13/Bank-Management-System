package bank.management.system;
 
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
 
/**
 * Login - the first screen of the ATM application.
 * Shows the card number and pin fields with Sign In, Clear and Sign Up buttons.
 * Sign Up opens the new account application form (SignupOne).
 *
 * @author Matthew Duffin
 */
public class Login extends JFrame implements ActionListener{
 
    // Buttons: sign in, sign up (new account) and clear (empties the fields)
    JButton login, signup, clear;
    // Card number input, and a password field for the pin (hides the typed characters)
    JTextField cardTextField;
    JPasswordField pinTextField;
 
    Login() { // Code for login page UI
 
        // Text shown in the window's title bar
        setTitle("AUTOMATED TELLER MACHINE");
 
        // null layout = every component is positioned manually with setBounds(x, y, width, height)
        setLayout(null);
 
        // ----- Logo image -----
        // Load the image from the icons folder on the classpath, then scale it to 100x100
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/logo.jpg"));
        Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel label = new JLabel(i3);
        label.setBounds(70, 10, 100, 100);
        add(label);
 
        // ----- Welcome heading -----
        JLabel text = new JLabel("Welcome to ATM");
        text.setFont(new Font("Osward", Font.BOLD, 38));
        text.setBounds(200, 40, 400, 40);
        add(text);
 
        // ----- Card number -----
        JLabel cardno = new JLabel("Card No:");
        cardno.setFont(new Font("Raleway", Font.BOLD, 28));
        cardno.setBounds(120, 150, 150, 30);
        add(cardno);
 
        cardTextField = new JTextField();
        cardTextField.setBounds(300, 150, 230, 30);
        cardTextField.setFont(new Font("Arial", Font.BOLD, 14));
        add(cardTextField);
 
        // ----- Pin -----
        JLabel pin = new JLabel("Pin:");
        pin.setFont(new Font("Raleway", Font.BOLD, 28));
        pin.setBounds(120, 220, 250, 30);
        add(pin);
 
        pinTextField = new JPasswordField();
        pinTextField.setBounds(300, 220, 230, 30);
        pinTextField.setFont(new Font("Arial", Font.BOLD, 14));
        add(pinTextField);
 
        // ----- Buttons (black background, white text) -----
        login = new JButton("SIGN IN");
        login.setBounds(300, 300, 100, 30);
        login.setBackground(Color.BLACK);
        login.setForeground(Color.WHITE);
        login.addActionListener(this); // clicking calls actionPerformed
        add(login);
 
        clear = new JButton("CLEAR");
        clear.setBounds(430, 300, 100, 30);
        clear.setBackground(Color.BLACK);
        clear.setForeground(Color.WHITE);
        clear.addActionListener(this);
        add(clear);
 
        signup = new JButton("SIGN UP");
        signup.setBounds(300, 350, 230, 30);
        signup.setBackground(Color.BLACK);
        signup.setForeground(Color.WHITE);
        signup.addActionListener(this);
        add(signup);
 
        // ----- Window settings -----
        getContentPane().setBackground(Color.WHITE);
 
        setSize(800, 480);
        setVisible(true);
        setLocationRelativeTo(null); // centre the window on screen
        setVisible(true);
    }
 
    // Runs when any of the three buttons is clicked
    public void actionPerformed(ActionEvent ae){
        // ae.getSource() tells us which button was pressed
        if (ae.getSource() == clear) {
            // Empty both input fields
            cardTextField.setText("");
            pinTextField.setText("");
        } else if (ae.getSource() == login) {
            // TODO: check the card number and pin against the database
 
        } else if (ae.getSource() == signup) {
            // Hide the login window and open page 1 of the new account form
            setVisible(false);
            new SignupOne().setVisible(true);
        }
    }
 
    // Entry point: starts the application at the login screen
    public static void main(String args[]) {
        new Login();
 
    }
 
}
 