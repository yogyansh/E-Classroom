package Classroom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class StudentOptionChoose extends JFrame implements ActionListener {

    JLabel background1;
    JButton Exam, Quiz, Assignment, Exit, Result;
    String name, email;
    int rollno;

    public StudentOptionChoose(String name, String email, int rollno) {
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1050, 550);
        setLocation(300, 150);
        setLayout(null);
        getContentPane().setBackground(Color.gray);
        setResizable(false);

        this.name = name;
        this.email = email;
        this.rollno = rollno;

        ImageIcon first1 = new ImageIcon(ClassLoader.getSystemResource("Classroom/icons/studentIcon.png"));
        Image second1 = first1.getImage().getScaledInstance(512, 512, Image.SCALE_DEFAULT);
        ImageIcon third1 = new ImageIcon(second1);
        background1 = new JLabel(third1);
        background1.setBounds(0, 0, 512, 512);
        add(background1);

        Exam = new JButton("EXAM ");
        Exam.setBounds(690, 200, 220, 50);
        Exam.setFont(new Font("Courier", Font.BOLD, 15));
        Exam.setBackground(Color.BLACK);
        Exam.setForeground(Color.GRAY);
        Exam.addActionListener(this);
        add(Exam);

        Quiz = new JButton("QUIZ COMPETITION");
        Quiz.setBounds(690, 390, 220, 50);
        Quiz.setFont(new Font("Courier", Font.BOLD, 15));
        Quiz.setBackground(Color.BLACK);
        Quiz.setForeground(Color.GRAY);
        Quiz.addActionListener(this);
        add(Quiz);

        Assignment = new JButton("ASSIGNMENT");
        Assignment.setBounds(690, 330, 220, 50);
        Assignment.setFont(new Font("Courier", Font.BOLD, 15));
        Assignment.setBackground(Color.BLACK);
        Assignment.setForeground(Color.GRAY);
        Assignment.addActionListener(this);
        add(Assignment);

        Result = new JButton("Result");
        Result.setBounds(690, 270, 220, 50);
        Result.setFont(new Font("Courier", Font.BOLD, 15));
        Result.setBackground(Color.BLACK);
        Result.setForeground(Color.GRAY);
        Result.addActionListener(this);
        add(Result);

        ImageIcon Exit1 = new ImageIcon(ClassLoader.getSystemResource("Classroom/icons/cancel.png"));
        Image Exit2 = Exit1.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon Exit3 = new ImageIcon(Exit2);
        Exit = new JButton("", Exit3);
        Exit.setBounds(1000, 0, 30, 30);
        Exit.setBackground(Color.GRAY);
        Exit.setFont(new Font("Courier", Font.BOLD, 15));
        Exit.addActionListener(this);
        add(Exit);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Quiz) {
            try {
                ConnectionDB connect = new ConnectionDB();
                ResultSet rs = connect.statement.executeQuery("Select * from Question");
                if (rs.next()) {
                    rs = connect.statement.executeQuery("Select * from quizans where StudentId='" + rollno + "'");
                    if (!rs.next()) {

                        this.setVisible(false);
                        new Quiz_Terms(name, email, rollno).setVisible(true);

                    } else {
                        JOptionPane.showMessageDialog(null, "quiz is already done");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Quiz is not started yet");
                }
            } catch (Exception exception) {
                
            }

        } else if (e.getSource() == Assignment) {
            try {
                ConnectionDB connect = new ConnectionDB();
                ResultSet rs = connect.statement.executeQuery("Select * from Assignment");
                if (rs.next()) {
                    rs = connect.statement.executeQuery("Select * from Assignmentans where StudentId='" + rollno + "'");
                    if (!rs.next()) {
                        this.setVisible(false);
                        new Assignment(name, email, rollno).setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Assignment is already done");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No Assignment is given");

                }
            } catch (Exception exception) {
                
            }
        } else if (e.getSource() == Exam) {
            try {
                ConnectionDB connect = new ConnectionDB();
                ResultSet rs = connect.statement.executeQuery("Select * from exam");
                if (rs.next()) {
                    rs = connect.statement.executeQuery("Select * from examans where StudentId='" + rollno + "'");
                    if (!rs.next()) {
                        this.setVisible(false);
                        new Exam_Terms(name, email, rollno).setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Exam is already done");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No Exam is given");

                }
            } catch (Exception exception) {
                
            }
        } else if (e.getSource() == Result) {
            this.setVisible(false);
            new Result(name, email, rollno).setVisible(true);
        } else if (e.getSource() == Exit) {
            System.exit(0);
        }
    }
    public static void main(String []args)
    {
    	new StudentOptionChoose("","",0).setVisible(true);
    }
}
