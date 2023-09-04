package Classroom;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class AssignmentLastFrame extends JFrame implements ActionListener {

    JLabel background, Submission, AssignmentAttempt, TotalAssignment;
    JButton Exit, Back;
    String name, email;
    int rollno;

    public AssignmentLastFrame(int Total, int Attempt, String name, String email, int rollno) {
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1050, 550);
        setLocation(300, 150);
        setLayout(null);
        getContentPane().setBackground(Color.gray);
        setResizable(false);

        ImageIcon first = new ImageIcon(ClassLoader.getSystemResource("Classroom/icons/assignment.png"));
        Image second = first.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon third = new ImageIcon(second);
        background = new JLabel(third);
        background.setBounds(170, 120, 100, 100);
        add(background);

        Submission = new JLabel(" Assignment has been submitted succesfully");
        Submission.setBounds(260, 160, 750, 30);
        Submission.setFont(new Font("Viner Hand ITC", Font.BOLD, 30));
        Submission.setForeground(Color.WHITE);
        add(Submission);

        AssignmentAttempt = new JLabel("Assignment Attempted: " + Attempt);
        AssignmentAttempt.setBounds(200, 260, 400, 30);
        AssignmentAttempt.setForeground(Color.WHITE);
        AssignmentAttempt.setFont(new Font("Viner Hand ITC", Font.BOLD, 30));
        add(AssignmentAttempt);

        TotalAssignment = new JLabel("Total Assignment: " + Total);
        TotalAssignment.setBounds(200, 300, 350, 30);
        TotalAssignment.setForeground(Color.WHITE);
        TotalAssignment.setFont(new Font("Viner Hand ITC", Font.BOLD, 30));
        add(TotalAssignment);

        ImageIcon Exit1 = new ImageIcon(ClassLoader.getSystemResource("Classroom/icons/cancel.png"));
        Image Exit2 = Exit1.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon Exit3 = new ImageIcon(Exit2);
        Exit = new JButton("", Exit3);
        Exit.setBounds(1000, 5, 30, 30);
        Exit.setFont(new Font("Courier", Font.BOLD, 15));
        Exit.setBackground(Color.GRAY);
        Exit.addActionListener(this);
        add(Exit);

        ImageIcon Back1 = new ImageIcon(ClassLoader.getSystemResource("Classroom/icons/back.png"));
        Image Back2 = Back1.getImage().getScaledInstance(25, 30, Image.SCALE_DEFAULT);
        ImageIcon Back3 = new ImageIcon(Back2);
        Back = new JButton("", Back3);
        Back.setBounds(5, 5, 23, 23);
        Back.setBackground(Color.GRAY);
        Back.setFont(new Font("Courier", Font.BOLD, 15));
        Back.addActionListener(this);
        add(Back);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == Exit) {
            System.exit(0);
        } else if (e.getSource() == Back) {
            this.setVisible(false);
            new StudentOptionChoose(name, email, rollno).setVisible(true);
        }
    }
    public static void main(String []args)
    {
    	new AssignmentLastFrame(0,0,"","",0).setVisible(true);
    }
}
