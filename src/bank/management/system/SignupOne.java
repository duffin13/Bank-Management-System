package bank.management.system;
 
 
import javax.swing.*;
import java.awt.*;
import java.util.*;
import com.toedter.calendar.JDateChooser;
import java.awt.event.*;
 
/**
 * SignupOne - Page 1 of the new account application form.
 * Collects personal details (name, father's name, date of birth, gender,
 * email, marital status, address, city, state, pin code), saves them to the
 * "signup" table, then opens page 2 (SignupTwo) using the same form number.
 *
 * @author Matthew Duffin
 */
 
 
public class SignupOne extends JFrame implements ActionListener{
 
    // Randomly generated application form number (links page 1 and page 2 in the database)
    long random;
    // Text fields for the form inputs
    JTextField nameTextField, fnameTextField , dobTextField, emailTextField,  addressTextField, cityTextField, stateTextField, pinTextField;
    JButton next;
    // Radio buttons: gender (male/female) and marital status (married/unmarried/other)
    JRadioButton male, female, other, married, unmarried;
    // Calendar date picker from the JCalendar library (com.toedter.calendar)
    JDateChooser dateChooser;
    SignupOne() { // Code for sign up page UI
 
        // null layout = every component is positioned manually with setBounds(x, y, width, height)
        setLayout(null);
 
        // Generate a random 4-digit form number (1000-9999)
        Random ran = new Random();
        random =(Math.abs(ran.nextLong() % 9000L) + 1000L);
 
        // ----- Page headings -----
        JLabel formno = new JLabel("APPLICATION FORM NO. " + random );
        formno.setFont(new Font("Raleway", Font.BOLD, 38));
        formno.setBounds(140, 20, 600, 40);
        add(formno);
 
        JLabel personDetails = new JLabel("Page 1: Personal Details");
        personDetails.setFont(new Font("Raleway", Font.BOLD, 22));
        personDetails.setBounds(290, 80, 400, 30);
        add(personDetails);
 
        // ----- Name -----
        JLabel name = new JLabel("Name:");
        name.setFont(new Font("Raleway", Font.BOLD, 20));
        name.setBounds(100, 140, 100, 30);
        add(name);
 
        nameTextField = new JTextField();
        nameTextField.setFont(new Font("Raleway", Font.BOLD, 14));
        nameTextField.setBounds(300, 140, 400, 30);
        add(nameTextField);
 
        // ----- Father's name -----
        JLabel fname = new JLabel("Father's Name:");
        fname.setFont(new Font("Raleway", Font.BOLD, 20));
        fname.setBounds(100, 190, 200, 30);
        add(fname);
 
        fnameTextField = new JTextField();
        fnameTextField.setFont(new Font("Raleway", Font.BOLD, 14));
        fnameTextField.setBounds(300, 190, 400, 30);
        add(fnameTextField);
 
        // ----- Date of birth (calendar picker) -----
        JLabel dob = new JLabel("Date of Birth:");
        dob.setFont(new Font("Raleway", Font.BOLD, 20));
        dob.setBounds(100, 240, 200, 30);
        add(dob);
 
        dateChooser = new JDateChooser();
        dateChooser.setBounds(300, 240, 200, 30);
        dateChooser.setForeground(new Color(105, 105, 105));
        add(dateChooser);
 
        // ----- Gender radio buttons -----
        JLabel gender = new JLabel("Gender:");
        gender.setFont(new Font("Raleway", Font.BOLD, 20));
        gender.setBounds(100, 290, 200, 30);
        add(gender);
 
        male = new JRadioButton("Male");
        male.setBounds(300, 290, 60, 30);
        male.setBackground(Color.WHITE);
        add(male);
 
        female = new JRadioButton("Female");
        female.setBounds(450, 290, 120, 30);
        female.setBackground(Color.WHITE);
        add(female);
 
        // ButtonGroup makes sure only one gender can be selected at a time
        ButtonGroup gendergroup = new ButtonGroup();
        gendergroup.add(male);
        gendergroup.add(female);
 
        // ----- Email -----
        JLabel email = new JLabel("Email:");
        email.setFont(new Font("Raleway", Font.BOLD, 20));
        email.setBounds(100, 340, 200, 30);
        add(email);
 
        emailTextField = new JTextField();
        emailTextField.setFont(new Font("Raleway", Font.BOLD, 14));
        emailTextField.setBounds(300, 340, 400, 30);
        add(emailTextField);
 
        // ----- Marital status radio buttons -----
        JLabel marital = new JLabel("Marital Status:");
        marital.setFont(new Font("Raleway", Font.BOLD, 20));
        marital.setBounds(100, 390, 200, 30);
        add(marital);
 
        married = new JRadioButton("Married");
        married.setBounds(300, 390, 100, 30);
        married.setBackground(Color.WHITE);
        add(married);
 
        unmarried = new JRadioButton("Unmarried");
        unmarried.setBounds(450, 390, 120, 30);
        unmarried.setBackground(Color.WHITE);
        add(unmarried);
 
        other = new JRadioButton("Other");
        other.setBounds(630, 390, 100, 30);
        other.setBackground(Color.WHITE);
        add(other);
 
 
        // Only one marital status can be selected at a time
        ButtonGroup maritalgroup = new ButtonGroup();
        maritalgroup.add(married);
        maritalgroup.add(unmarried);
        maritalgroup.add(other);
 
        // ----- Address -----
        JLabel address = new JLabel("Address:");
        address.setFont(new Font("Raleway", Font.BOLD, 20));
        address.setBounds(100, 440, 200, 30);
        add(address);
 
        addressTextField = new JTextField();
        addressTextField.setFont(new Font("Raleway", Font.BOLD, 14));
        addressTextField.setBounds(300, 440, 400, 30);
        add(addressTextField);
 
        // ----- City -----
        JLabel city = new JLabel("City:");
        city.setFont(new Font("Raleway", Font.BOLD, 20));
        city.setBounds(100, 490, 200, 30);
        add(city);
 
        cityTextField = new JTextField();
        cityTextField.setFont(new Font("Raleway", Font.BOLD, 14));
        cityTextField.setBounds(300, 490, 400, 30);
        add(cityTextField);
 
 
        // ----- State -----
        JLabel state = new JLabel("State:");
        state.setFont(new Font("Raleway", Font.BOLD, 20));
        state.setBounds(100, 540, 200, 30);
        add(state);
 
        stateTextField = new JTextField();
        stateTextField.setFont(new Font("Raleway", Font.BOLD, 14));
        stateTextField.setBounds(300, 540, 400, 30);
        add(stateTextField);
 
 
        // ----- Pin code -----
        JLabel pincode = new JLabel("Pin Code:");
        pincode.setFont(new Font("Raleway", Font.BOLD, 20));
        pincode.setBounds(100, 590, 200, 30);
        add(pincode);
 
        pinTextField = new JTextField();
        pinTextField.setFont(new Font("Raleway", Font.BOLD, 14));
        pinTextField.setBounds(300, 590, 400, 30);
        add(pinTextField);
 
        // ----- Next button -----
        next = new JButton("Next");
        next.setBackground(Color.WHITE);
        next.setFont(new Font("Raleway", Font.BOLD, 14));
        next.setBounds(620, 660, 80, 30);
        next.addActionListener(this); // clicking Next calls actionPerformed
        add(next);
 
        // ----- Window settings -----
        getContentPane().setBackground(Color.WHITE);
 
        setSize(850, 800);
        setLocationRelativeTo(null); // centre the window on screen
        setVisible(true);
    }
 
    // Runs when the Next button is clicked: reads the form, saves it, opens page 2
    public void actionPerformed(ActionEvent ae){
        // Form number as a String so it can go into the SQL query
        String formno = "" + random;
        String name = nameTextField.getText();
        String fname = fnameTextField.getText();
        // Read the chosen date as text from the date chooser's editor field
        String dob =((JTextField)dateChooser.getDateEditor().getUiComponent()).getText();
        // Gender: stays null if neither button is selected
        String gender = null;
        if (male.isSelected()){
            gender = "Male";
         }
            else if (female.isSelected()) {
                    gender = "Female";
         }
 
        String email = emailTextField.getText();
        // Marital status: stays null if none is selected
        String marital = null;
 
        if (married.isSelected()) {
            marital = "Married";
        }
        else if (unmarried.isSelected()) {
            marital = "Unmarried";
        }
        else if (other.isSelected()) {
            marital = "Other";
        }
 
            // Read the address fields
            String address = addressTextField.getText();
            String city = cityTextField.getText();
            String state = stateTextField.getText();
            String pin = pinTextField.getText();
 
            try{
                // Name is the only required field
                if (name.equals("")) {
                    JOptionPane.showMessageDialog(null, "Name is Required");
                } else {
                    // Open the database connection (Conn class holds the JDBC setup)
                    Conn c = new Conn();
                    // Build the insert statement; value order must match the signup table columns
                    String query = "insert into signup values('" + formno + "', '" + name + "', '" + fname + "', '" + dob + "', '" + gender + "', '" + email + "', '" + marital + "', '" + address + "', '" + city + "', '" + pin + "', '" + state + "')";
                    // Run the insert (c.s is the Statement object in Conn)
                    c.s.executeUpdate(query);
 
                    // Hide this page and open page 2, passing the form number along
                    setVisible(false);
                    new SignupTwo(formno).setVisible(true);
                }
            } catch (Exception e) {
                // Print any database/connection error to the NetBeans Output window
                System.out.println(e);
            }
        }
 
 
    // Entry point: starts the application at page 1
    public static void main(String args[]) {
        new SignupOne();
 
    }
 
}