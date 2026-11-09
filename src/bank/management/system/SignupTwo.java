package bank.management.system;
 
 
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
 
/**
 * SignupTwo - Page 2 of the new account application form.
 * Collects additional details (religion, category, income, education,
 * occupation, PAN, PPSN, senior citizen, existing account) and saves
 * them to the "signuptwo" table, linked to page 1 by the form number.
 *
 * @author Matthew Duffin
 */
 
 
public class SignupTwo extends JFrame implements ActionListener{
 
    // Text fields for PAN and PPSN numbers
    JTextField pan, ppsn;
    JButton next;
    // Radio buttons: senior citizen (syes/sno) and existing account (eyes/eno)
    JRadioButton syes, sno, eyes, eno;
    // Dropdown menus
    JComboBox religion, category, occupation, education, income;
    // Form number passed in from page 1, used to link both pages in the database
    String formno;
 
    SignupTwo(String formno) { // Code for sign up page UI
 
        // Store the form number received from SignupOne
        this.formno = formno;
        // null layout = every component is positioned manually with setBounds(x, y, width, height)
        setLayout(null);
 
        setTitle("NEW ACCOUNT APPLICATION FORM - PAGE 2");
 
        // ----- Page heading -----
        JLabel additionalDetails = new JLabel("Page 2: Additional Details");
        additionalDetails.setFont(new Font("Raleway", Font.BOLD, 22));
        additionalDetails.setBounds(290, 80, 400, 30);
        add(additionalDetails);
 
        // ----- Religion dropdown -----
        JLabel religionLabel = new JLabel("Religion:");
        religionLabel.setFont(new Font("Raleway", Font.BOLD, 20));
        religionLabel.setBounds(100, 140, 100, 30);
        add(religionLabel);
 
        String valReligion[] = {"Hindu", "Muslim", "Sikh", "Christian", "Other"};
        religion = new JComboBox(valReligion);
        religion.setBounds(300, 140, 400, 30);
        religion.setBackground(Color.WHITE);
        add(religion);
 
        // ----- Category dropdown -----
        JLabel categoryLabel = new JLabel("Category:");
        categoryLabel.setFont(new Font("Raleway", Font.BOLD, 20));
        categoryLabel.setBounds(100, 190, 200, 30);
        add(categoryLabel);
 
        String valcategory[] = {"General", "OBC", "SC", "ST", "Other"};
        category = new JComboBox(valcategory);
        category.setBounds(300, 190, 400, 30);
        category.setBackground(Color.WHITE);
        add(category);
 
        // ----- Income dropdown -----
        JLabel incomeLabel = new JLabel("Income:");
        incomeLabel.setFont(new Font("Raleway", Font.BOLD, 20));
        incomeLabel.setBounds(100, 240, 200, 30);
        add(incomeLabel);
 
        String incomecategory[] = {"NULL", "< 30,000", "> 40,000", "> 60,000", "> 100,000"};
        income = new JComboBox(incomecategory);
        income.setBounds(300, 240, 400, 30);
        income.setBackground(Color.WHITE);
        add(income);
 
        // ----- Education / Qualification dropdown -----
        JLabel educationLabel = new JLabel("Education:");
        educationLabel.setFont(new Font("Raleway", Font.BOLD, 20));
        educationLabel.setBounds(100, 290, 200, 30);
        add(educationLabel);
 
        JLabel qualificationLabel = new JLabel("Qualification:");
        qualificationLabel.setFont(new Font("Raleway", Font.BOLD, 20));
        qualificationLabel.setBounds(100, 315, 200, 30);
        add(qualificationLabel);
 
        String educationValues[] = {"Non-Graduate", "Graduate", "Post-Graduate", "Doctrate", "Other"};
        education = new JComboBox(educationValues);
        education.setBounds(300, 315, 400, 30);
        education.setBackground(Color.WHITE);
        add(education);
 
        // ----- Occupation dropdown -----
        JLabel occupationLabel = new JLabel("Occupation:");
        occupationLabel.setFont(new Font("Raleway", Font.BOLD, 20));
        occupationLabel.setBounds(100, 390, 200, 30);
        add(occupationLabel);
 
        String occupationValues[] = {"Salaried", "Self Employed", "Bussiness", "Student", "Retired", "Other"};
        occupation = new JComboBox(occupationValues);
        occupation.setBounds(300, 390, 400, 30);
        occupation.setBackground(Color.WHITE);
        add(occupation);
 
        // ----- PAN number text field -----
        JLabel panLabel = new JLabel("PAN Number:");
        panLabel.setFont(new Font("Raleway", Font.BOLD, 20));
        panLabel.setBounds(100, 440, 200, 30);
        add(panLabel);
 
        pan = new JTextField();
        pan.setFont(new Font("Raleway", Font.BOLD, 14));
        pan.setBounds(300, 440, 400, 30);
        add(pan);
 
        // ----- PPSN text field -----
        JLabel ppsnLabel = new JLabel("PPSN:");
        ppsnLabel.setFont(new Font("Raleway", Font.BOLD, 20));
        ppsnLabel.setBounds(100, 490, 200, 30);
        add(ppsnLabel);
 
        ppsn = new JTextField();
        ppsn.setFont(new Font("Raleway", Font.BOLD, 14));
        ppsn.setBounds(300, 490, 400, 30);
        add(ppsn);
 
        // ----- Senior citizen radio buttons -----
        JLabel seniorLabel = new JLabel("Senior Citizen:");
        seniorLabel.setFont(new Font("Raleway", Font.BOLD, 20));
        seniorLabel.setBounds(100, 540, 200, 30);
        add(seniorLabel);
 
        syes = new JRadioButton("Yes");
        syes.setBounds(300, 540, 100, 30);
        syes.setBackground(Color.WHITE);
        add(syes);
 
        sno = new JRadioButton("No");
        sno.setBounds(450, 540, 120, 30);
        sno.setBackground(Color.WHITE);
        add(sno);
 
        // ButtonGroup makes sure only one of Yes/No can be selected at a time
        ButtonGroup seniorgroup = new ButtonGroup();
        seniorgroup.add(syes);
        seniorgroup.add(sno);
 
        // ----- Existing account radio buttons -----
        JLabel existingAccountLabel = new JLabel("Exisiting Account:");
        existingAccountLabel.setFont(new Font("Raleway", Font.BOLD, 20));
        existingAccountLabel.setBounds(100, 590, 200, 30);
        add(existingAccountLabel);
 
        eyes = new JRadioButton("Yes");
        eyes.setBounds(300, 590, 100, 30);
        eyes.setBackground(Color.WHITE);
        add(eyes);
 
        eno = new JRadioButton("No");
        eno.setBounds(450, 590, 120, 30);
        eno.setBackground(Color.WHITE);
        add(eno);
 
        // Separate group for the existing account Yes/No buttons
        ButtonGroup existacc = new ButtonGroup();
        existacc.add(eyes);
        existacc.add(eno);
 
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
 
    // Runs when the Next button is clicked: reads the form and saves it to the database
    public void actionPerformed(ActionEvent ae){
        // Get the selected item from each dropdown
        String sreligion = (String) religion.getSelectedItem();
        String scategory = (String) category.getSelectedItem();
        String sincome = (String) income.getSelectedItem();
        String seducation = (String) education.getSelectedItem();
        String soccupation = (String) occupation.getSelectedItem();
 
        // Senior citizen: stays null if neither button is selected
        String seniorcitizen = null;
 
        if (syes.isSelected()){
            seniorcitizen = "Yes";
         }
            else if (sno.isSelected()) {
                    seniorcitizen = "No";
         }
 
        // Existing account: stays null if neither button is selected
        String exisitingaccount = null;
 
        if (eyes.isSelected()) {
            exisitingaccount = "Yes";
        }
        else if (eno.isSelected()) {
            exisitingaccount = "No";
        }
 
            // Read the text typed into the PAN and PPSN fields
            String span = pan.getText();
            String sppsn = ppsn.getText();
 
 
            try {
                    // Open the database connection (Conn class holds the JDBC setup)
                    Conn c = new Conn();
                    // Build the insert statement; value order must match the signuptwo table columns
                    String query = "insert into signuptwo values('" + formno + "', '" + sreligion + "', '" + scategory + "', '" + sincome + "', '" + seducation + "', '" + soccupation + "', '" + span + "', '" + sppsn + "', '" + seniorcitizen + "', '" + exisitingaccount + "')";
                    // Run the insert (c.s is the Statement object in Conn)
                    c.s.executeUpdate(query);
 
            //Signup3 Object
            } catch (Exception e) {
                // Print any database/connection error to the NetBeans Output window
                System.out.println(e);
            }
        }
 
 
    // Entry point for testing this page on its own (empty form number)
    public static void main(String args[]) {
        new SignupTwo("");
 
    }
}
 