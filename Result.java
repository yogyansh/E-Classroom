package Classroom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Result extends JFrame implements ActionListener {

    JLabel background1, Title, Welcome, Email, Assignment, Quiz, Exam, Question, AttemptQuestion;
    JTable table1, table2, table3;
    JButton Back;
    ResultSet rs, rs2, rs3;
    String Total, name, email;
    int Attempt, rollno;

    public Result(String name, String email, int rollno) {

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

        Title = new JLabel(" Result ");
        Title.setBounds(450, 30, 200, 30);
        Title.setFont(new Font("Courier", Font.BOLD, 20));
        Title.setForeground(Color.WHITE);
        add(Title);

        Welcome = new JLabel("Welcome " + name);
        Welcome.setBounds(55, 10, 200, 30);
        Welcome.setFont(new Font("Courier", Font.BOLD, 20));
        Welcome.setForeground(Color.WHITE);
        add(Welcome);

        Email = new JLabel(email);
        Email.setBounds(55, 30, 300, 30);
        Email.setFont(new Font("Courier", Font.BOLD, 13));
        Email.setForeground(Color.WHITE);
        add(Email);

        Assignment = new JLabel("Assignment");
        Assignment.setBounds(50, 80, 200, 30);
        Assignment.setFont(new Font("Courier", Font.BOLD, 20));
        Assignment.setForeground(Color.WHITE);
        add(Assignment);

        Quiz = new JLabel("Quiz");
        Quiz.setBounds(50, 380, 150, 30);
        Quiz.setFont(new Font("Courier", Font.BOLD, 20));
        Quiz.setForeground(Color.WHITE);
        add(Quiz);

        Question = new JLabel("");
        Question.setBounds(90, 420, 250, 30);
        Question.setFont(new Font("Courier", Font.BOLD, 17));
        Question.setForeground(Color.WHITE);
        add(Question);

        AttemptQuestion = new JLabel("");
        AttemptQuestion.setBounds(90, 460, 250, 30);
        AttemptQuestion.setFont(new Font("Courier", Font.BOLD, 17));
        AttemptQuestion.setForeground(Color.WHITE);
        add(AttemptQuestion);

        Exam = new JLabel("Exam");
        Exam.setBounds(50, 230, 150, 30);
        Exam.setFont(new Font("Courier", Font.BOLD, 20));
        Exam.setForeground(Color.WHITE);
        add(Exam);

        ImageIcon Back1 = new ImageIcon(ClassLoader.getSystemResource("Classroom/icons/back.png"));
        Image Back2 = Back1.getImage().getScaledInstance(25, 30, Image.SCALE_DEFAULT);
        ImageIcon Back3 = new ImageIcon(Back2);

        Back = new JButton("", Back3);
        Back.setBounds(5, 5, 23, 23);
        Back.setBackground(Color.GRAY);
        Back.setFont(new Font("Courier", Font.BOLD, 15));
        Back.addActionListener(this);
        add(Back);

        try {
            ConnectionDB connect = new ConnectionDB();
            rs = connect.statement.executeQuery("Select count(*) from AssignmentAns where StudentId='" + rollno + "'");
            rs.next();
            int count = rs.getInt(1);
            if (count != 0) {
                String data[][] = new String[count][2];
                String ColName[] = {"Assignment ID", "Marks"};
                rs = connect.statement.executeQuery("Select * from AssignmentAns where StudentId='" + rollno + "'");

                int i = 0;
                while (rs.next()) {
                    data[i][0] = rs.getString("AssignmentId");
                    data[i][1] = rs.getString("Marks");

                    i++;
                }
                table1 = new JTable(data, ColName);
                table1.setBackground(Color.GRAY);
                table1.setForeground(Color.WHITE);
                JScrollPane scroll = new JScrollPane(table1);
                scroll.setBounds(0, 110, 1020, 80);
                add(scroll);
            }
            rs2 = connect.statement.executeQuery("Select count(*) from ExamAns where StudentId='" + rollno + "'");
            rs2.next();
            int count2 = rs2.getInt(1);
            String data1[][] = new String[count2][2];
            String ColName1[] = {"Question ID", "Marks"};
            rs2 = connect.statement.executeQuery("Select QuestionId,Marks from ExamAns where StudentId='" + rollno + "'");
            int k = 0;
            while (rs2.next()) {
                data1[k][0] = rs2.getString("QuestionId");
                data1[k][1] = rs2.getString("Marks");

                k++;
            }
            table2 = new JTable(data1, ColName1);
            table2.setBackground(Color.GRAY);
            table2.setForeground(Color.WHITE);
            JScrollPane scroll1 = new JScrollPane(table2);
            scroll1.setBounds(0, 260, 1020, 80);
            add(scroll1);

            rs3 = connect.statement.executeQuery("Select * from quizAns where StudentId='" + rollno + "'");
            if (rs3.next()) {
                AttemptQuestion.setText("Marks gained:" + rs3.getString("Result"));
            }
            rs3 = connect.statement.executeQuery("Select count(*) from Question");
            rs3.next();
            int a = rs3.getInt(1);

            Question.setText("Total Questions:" + a);

        } catch (Exception exception) {
            
        }
    }

    public void actionPerformed(ActionEvent e) {
        this.setVisible(false);
        new StudentOptionChoose(name, email, rollno).setVisible(true);

    }
    public static void main(String []args)
    {
    	new Result("","",0).setVisible(true);
    }
    
}
