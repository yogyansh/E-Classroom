package Classroom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class OpeningPage extends JFrame implements ActionListener {

    JButton Student, Admin, Exit;
    JLabel background;

    public OpeningPage() {
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(626, 430);
        setLocation(300, 150);
        setLayout(null);
        setResizable(false);

        ImageIcon first = new ImageIcon(ClassLoader.getSystemResource("Classroom/icons/mainpage.jpg"));
        Image second = first.getImage().getScaledInstance(626, 430, Image.SCALE_DEFAULT);
        ImageIcon third = new ImageIcon(second);
        background = new JLabel(third);
        background.setBounds(0, 0, 626, 430);
        add(background);

        ImageIcon Student1 = new ImageIcon(ClassLoader.getSystemResource("Classroom/icons/studentlogin.png"));
        Image Student2 = Student1.getImage().getScaledInstance(64, 55, Image.SCALE_DEFAULT);
        ImageIcon Student3 = new ImageIcon(Student2);

        Student = new JButton("Student", Student3);
        Student.setBounds(190, 100, 230, 55);
        Student.setFont(new Font("Courier", Font.BOLD, 15));
        Student.setBackground(Color.WHITE);
        background.add(Student);
        Student.addActionListener(this);

        ImageIcon Admin1 = new ImageIcon(ClassLoader.getSystemResource("Classroom/icons/TeacherIcon.png"));
        Image Admin2 = Admin1.getImage().getScaledInstance(64, 55, Image.SCALE_DEFAULT);
        ImageIcon Admin3 = new ImageIcon(Admin2);

        Admin = new JButton("Admin", Admin3);
        Admin.setBounds(190, 165, 230, 55);
        Admin.setFont(new Font("Courier", Font.BOLD, 15));
        Admin.setBackground(Color.WHITE);
        background.add(Admin);
        Admin.addActionListener(this);

        ImageIcon Exit1 = new ImageIcon(ClassLoader.getSystemResource("Classroom/icons/cancel.png"));
        Image Exit2 = Exit1.getImage().getScaledInstance(64, 55, Image.SCALE_DEFAULT);
        ImageIcon Exit3 = new ImageIcon(Exit2);

        Exit = new JButton("Exit", Exit3);
        Exit.setBounds(190, 230, 230, 55);
        Exit.setFont(new Font("Courier", Font.BOLD, 15));
        Exit.setBackground(Color.WHITE);
        Exit.addActionListener(this);
        background.add(Exit);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Student) {
            new StudentLogin().setVisible(true);
            this.setVisible(false);
        } else if (e.getSource() == Admin) {
            new TeacherLogin().setVisible(true);
            this.setVisible(false);
        } else if (e.getSource() == Exit) {
            System.exit(0);
        }

    }
    public static void main(String []args)
    {
    	new OpeningPage().setVisible(true);
    }
}
