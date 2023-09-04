package Classroom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class TeacherLogin extends JFrame implements ActionListener {

    JLabel background, background1;
    JTextField Email;
    JPasswordField Password;
    JButton Login, Back, Exit;
    JCheckBox select;

    public TeacherLogin() {
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 550);
        setLocation(300, 150);
        setLayout(null);
        getContentPane().setBackground(Color.gray);
        setResizable(false);

        ImageIcon first = new ImageIcon(ClassLoader.getSystemResource("Classroom/icons/Teacher.png"));
        Image second = first.getImage().getScaledInstance(512, 512, Image.SCALE_DEFAULT);
        ImageIcon third = new ImageIcon(second);
        background = new JLabel(third);
        background.setBounds(0, 0, 512, 512);
        add(background);

        ImageIcon first1 = new ImageIcon(ClassLoader.getSystemResource("Classroom/icons/FTeacher.png"));
        Image second1 = first1.getImage().getScaledInstance(512, 512, Image.SCALE_DEFAULT);
        ImageIcon third1 = new ImageIcon(second1);
        background1 = new JLabel(third1);
        background1.setBounds(250, 0, 512, 512);
        add(background1);

        Password = new JPasswordField("Password");
        Password.setBounds(650, 290, 200, 30);
        Password.setEchoChar((char) 0);
        add(Password);
        Password.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                Password.setText("");
                Password.setEchoChar('*');
            }

            public void focusLost(FocusEvent e) {
            }
        });

        Email = new JTextField("Email");
        Email.setBounds(650, 240, 200, 30);
        add(Email);
        Email.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                Email.setText("");
            }

            public void focusLost(FocusEvent e) {
            }
        });

        Login = new JButton("Login");
        Login.setBounds(680, 380, 140, 35);
        Login.setFont(new Font("Courier", Font.BOLD, 20));
        Login.setBackground(Color.BLACK);
        Login.setForeground(Color.GRAY);
        Login.addActionListener(this);
        add(Login);

        ImageIcon Back1 = new ImageIcon(ClassLoader.getSystemResource("Classroom/icons/back.png"));
        Image Back2 = Back1.getImage().getScaledInstance(25, 30, Image.SCALE_DEFAULT);
        ImageIcon Back3 = new ImageIcon(Back2);

        Back = new JButton("", Back3);
        Back.setBounds(5, 5, 23, 23);
        Back.setBackground(Color.GRAY);
        Back.setFont(new Font("Courier", Font.BOLD, 15));
        Back.addActionListener(this);
        add(Back);

        ImageIcon Exit1 = new ImageIcon(ClassLoader.getSystemResource("Classroom/icons/cancel.png"));
        Image Exit2 = Exit1.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        ImageIcon Exit3 = new ImageIcon(Exit2);

        Exit = new JButton("", Exit3);
        Exit.setBounds(955, 5, 25, 25);
        Exit.setBackground(Color.GRAY);
        Exit.setFont(new Font("Courier", Font.BOLD, 15));
        Exit.addActionListener(this);
        add(Exit);

        select = new JCheckBox("Show Password");
        select.setBounds(650, 340, 200, 30);
        select.setBackground(Color.GRAY);
        add(select);
        select.addActionListener(this);

    }

    public void actionPerformed(ActionEvent e) {
        String password = Password.getText();
        try {
            if (select.isSelected()) {
                Password.setEchoChar((char) 0);
            } else if (!select.isSelected() && !password.equals("Password")) {
                Password.setEchoChar('*');
            }
            if (e.getSource() == Exit) {
                System.exit(0);
            } else if (e.getSource() == Back) {
                this.setVisible(false);
                new OpeningPage().setVisible(true);
            } else if (e.getSource() == Login) {
                String email = Email.getText();

                if (email.equals("") || password.equals("Password") || password.equals("")) {
                    JOptionPane.showMessageDialog(null, "Please Enter Email and Password");
                    return;
                } else {
                    ConnectionDB connect = new ConnectionDB();
                    ResultSet rs = connect.statement.executeQuery("Select * from AdminLogin where Email='" + email + "' and Password='" + password + "'");
                    if (rs.next()) {
                        this.setVisible(false);
                        new TeacherOptionChoose().setVisible(true);
                    } else {
                        Password.setText("");
                        Email.setText("");
                        JOptionPane.showMessageDialog(null, "Email or Password is incorrect");
                        return;
                    }
                }

            }
        } catch (Exception exception) {
            
        }
    }
    public static void main(String []args)
    {
    	new TeacherLogin().setVisible(true);
    }
}
