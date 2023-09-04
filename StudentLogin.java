package Classroom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.text.*;

public class StudentLogin extends JFrame implements ActionListener, KeyListener {

    JLabel background, Name, Contact, Email, background1, RollNo, nlabel, elabel, rlabel, clabel;
    JTextField Name_, Contact_, Email_, RollNo_;
    JButton Save, Back, Exit;
    int flag = 0;

    public StudentLogin() {
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1050, 550);
        setLocation(300, 150);
        setLayout(null);
        getContentPane().setBackground(Color.gray);
        setResizable(false);

        ImageIcon first1 = new ImageIcon(ClassLoader.getSystemResource("Classroom/icons/studentIcon.png"));
        Image second1 = first1.getImage().getScaledInstance(512, 512, Image.SCALE_DEFAULT);
        ImageIcon third1 = new ImageIcon(second1);
        background1 = new JLabel(third1);
        background1.setBounds(0, 0, 512, 512);
        add(background1);

        RollNo = new JLabel("Roll No");
        RollNo.setBounds(560, 200, 100, 30);
        RollNo.setFont(new Font("System", Font.CENTER_BASELINE, 20));
        add(RollNo);

        RollNo_ = new JTextField(40);
        RollNo_.setBounds(650, 200, 200, 30);
        RollNo_.setFont(new Font("System", Font.CENTER_BASELINE, 20));
        RollNo_.addKeyListener(this);
        add(RollNo_);

        rlabel = new JLabel("");
        rlabel.setBounds(860, 200, 150, 30);
        rlabel.setForeground(new Color(128, 0, 0));
        add(rlabel);

        elabel = new JLabel("");
        elabel.setBounds(860, 280, 150, 30);
        elabel.setForeground(new Color(128, 0, 0));
        add(elabel);

        nlabel = new JLabel("");
        nlabel.setBounds(860, 240, 150, 30);
        nlabel.setForeground(new Color(128, 0, 0));
        add(nlabel);

        clabel = new JLabel("");
        clabel.setBounds(860, 320, 150, 30);
        clabel.setForeground(new Color(128, 0, 0));
        add(clabel);

        Name = new JLabel("Name");
        Name.setBounds(560, 240, 100, 30);
        Name.setFont(new Font("System", Font.CENTER_BASELINE, 20));
        add(Name);

        Contact = new JLabel("Contact");
        Contact.setBounds(560, 320, 100, 30);
        Contact.setFont(new Font("System", Font.CENTER_BASELINE, 20));
        add(Contact);

        Email = new JLabel("Email");
        Email.setBounds(560, 280, 100, 30);
        Email.setFont(new Font("System", Font.CENTER_BASELINE, 20));
        add(Email);

        Name_ = new JTextField(40);
        Name_.setBounds(650, 240, 200, 30);
        Name_.setFont(new Font("System", Font.CENTER_BASELINE, 20));
        Name_.addKeyListener(this);
        add(Name_);

        Contact_ = new JTextField(40);
        Contact_.setBounds(650, 320, 200, 30);
        Contact_.setFont(new Font("System", Font.CENTER_BASELINE, 20));
        Contact_.addKeyListener(this);
        add(Contact_);

        Email_ = new JTextField(40);
        Email_.setBounds(650, 280, 200, 30);
        Email_.setFont(new Font("System", Font.CENTER_BASELINE, 20));
        Email_.addKeyListener(this);
        add(Email_);

        Save = new JButton("Save");
        Save.setBounds(610, 380, 140, 35);
        Save.setFont(new Font("Courier", Font.BOLD, 20));
        Save.setBackground(Color.BLACK);
        Save.setForeground(Color.GRAY);
        Save.addActionListener(this);
        add(Save);

        ImageIcon Back1 = new ImageIcon(ClassLoader.getSystemResource("Classroom/icons/back.png"));
        Image Back2 = Back1.getImage().getScaledInstance(25, 30, Image.SCALE_DEFAULT);
        ImageIcon Back3 = new ImageIcon(Back2);

        Back = new JButton("", Back3);
        Back.setBounds(5, 5, 25, 30);
        Back.setBackground(Color.GRAY);
        Back.setFont(new Font("Courier", Font.BOLD, 15));
        Back.addActionListener(this);
        add(Back);

        ImageIcon Exit1 = new ImageIcon(ClassLoader.getSystemResource("Classroom/icons/cancel.png"));
        Image Exit2 = Exit1.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        ImageIcon Exit3 = new ImageIcon(Exit2);

        Exit = new JButton("", Exit3);
        Exit.setBounds(1005, 5, 25, 25);
        Exit.setFont(new Font("Courier", Font.BOLD, 15));
        Exit.setBackground(Color.GRAY);
        Exit.addActionListener(this);
        add(Exit);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Exit) {
            System.exit(0);
        } else if (e.getSource() == Back) {
            this.setVisible(false);
            new OpeningPage().setVisible(true);
        } else if (e.getSource() == Save) {

            String roll = RollNo_.getText();
            String name = Name_.getText();
            String email = Email_.getText();
            String contact = Contact_.getText();

            if (roll.equals("") || name.equals("") || email.equals("") || contact.equals("")) {
                JOptionPane.showMessageDialog(null, "All fields must be filled");
                return;
            }
            if (flag == 1) {
                return;
            }
            try {
                ConnectionDB connect = new ConnectionDB();
                int rollno = Integer.parseInt(roll);
                connect.statement.executeUpdate("Insert into studentlogin values('" + rollno + "','" + Name_.getText() + "','" + Email_.getText() + "','" + Contact_.getText() + "','" + 0 + "')");
                this.setVisible(false);
                new StudentOptionChoose(name, email, rollno).setVisible(true);

            } catch (Exception exception) {
                
            }
        }
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
        try {
            Pattern pattern;
            if (e.getSource() == Email_) {
                pattern = Pattern.compile("^[a-zA-z_](.+){0,30}[@][a-zA-z]{4,10}[.][a-zA-z]{2,6}[.]{0,1}[a-zA-z]{0,5}$");
                if (!pattern.matcher(Email_.getText()).matches()) {
                    elabel.setText("*Incorrect Email");
                    flag = 1;
                } else {
                    elabel.setText("");
                    flag = 0;
                }
            } else if (e.getSource() == Name_) {
                pattern = Pattern.compile("^[a-zA-Z]{1,15}$");
                if (!pattern.matcher(Name_.getText()).matches()) {

                    nlabel.setText("*Incorrect Naming");
                    flag = 1;
                } else {
                    nlabel.setText("");
                    flag = 0;
                }
            } else if (e.getSource() == Contact_) {
                pattern = Pattern.compile("^[0-9]{10}$");
                if (!pattern.matcher(Contact_.getText()).matches()) {
                    clabel.setText("*Incorrect Contact");
                    flag = 1;
                } else {
                    clabel.setText("");
                    flag = 0;
                }
            } else if (e.getSource() == RollNo_) {
                pattern = Pattern.compile("^[0-9]{1,15}$");
                if (!pattern.matcher(RollNo_.getText()).matches()) {
                    rlabel.setText("*Incorrect Roll no");
                    flag = 1;
                } else {
                    rlabel.setText("");
                    flag = 0;
                }
            }

        } catch (Exception exception) {
            
        }
    }
    public static void main(String []args)
    {
    	new StudentLogin().setVisible(true);
    }
}
