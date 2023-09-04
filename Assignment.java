package Classroom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Assignment extends JFrame implements ActionListener {

    JLabel Welcome, Email, TotalAssignment, AssignmentNo, que, ans;
    JButton Exit, Back, Submit, Next;
    JTextArea Ans;
    String name, email;
    int rollno;
    static int total = 0, Count = 1, attempt = 0;

    public Assignment(String name, String email, int rollno) {
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

        Welcome = new JLabel("Welcome " + name);
        Welcome.setBounds(30, 30, 200, 30);
        Welcome.setFont(new Font("Courier", Font.BOLD, 20));
        Welcome.setForeground(Color.WHITE);
        add(Welcome);

        Email = new JLabel(email);
        Email.setBounds(30, 60, 300, 30);
        Email.setFont(new Font("Courier", Font.BOLD, 13));
        Email.setForeground(Color.WHITE);
        add(Email);

        try {
            ConnectionDB connect = new ConnectionDB();

            ResultSet rs = connect.statement.executeQuery("Select count(*) from Assignment");
            if (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (Exception exception) {
            
        }

        TotalAssignment = new JLabel("Total Assignment:" + total);
        TotalAssignment.setBounds(200, 380, 200, 30);
        TotalAssignment.setFont(new Font("Courier", Font.BOLD, 15));
        TotalAssignment.setForeground(Color.WHITE);
        add(TotalAssignment);

        AssignmentNo = new JLabel("");
        AssignmentNo.setBounds(200, 420, 200, 30);
        AssignmentNo.setFont(new Font("Courier", Font.BOLD, 15));
        AssignmentNo.setForeground(Color.WHITE);
        add(AssignmentNo);

        que = new JLabel("");
        que.setBounds(200, 140, 550, 30);
        que.setFont(new Font("Courier", Font.BOLD, 15));
        que.setForeground(Color.WHITE);
        add(que);

        Ans = new JTextArea();
        Ans.setBounds(200, 210, 550, 150);
        add(Ans);

        ans = new JLabel("Answer:");
        ans.setBounds(200, 180, 100, 30);
        ans.setForeground(Color.WHITE);
        add(ans);

        Next = new JButton("Next");
        Next.setBounds(700, 410, 100, 30);
        Next.setForeground(Color.BLACK);
        Next.setBackground(Color.WHITE);
        add(Next);
        Next.addActionListener(this);

        Submit = new JButton("Submit");
        Submit.setBounds(700, 450, 100, 30);
        Submit.setForeground(Color.BLACK);
        Submit.setBackground(Color.WHITE);
        add(Submit);
        Submit.addActionListener(this);

        ImageIcon Back1 = new ImageIcon(ClassLoader.getSystemResource("Classroom/icons/back.png"));
        Image Back2 = Back1.getImage().getScaledInstance(25, 30, Image.SCALE_DEFAULT);
        ImageIcon Back3 = new ImageIcon(Back2);

        Back = new JButton("", Back3);
        Back.setBounds(5, 5, 23, 23);
        Back.setBackground(Color.GRAY);
        Back.setFont(new Font("Courier", Font.BOLD, 15));
        Back.addActionListener(this);
        add(Back);

        DisplayQue();

    }

    public void DisplayQue() {
        if (Count > total) {
            this.setVisible(false);
            new AssignmentLastFrame(total, attempt, name, email, rollno).setVisible(true);
            return;
        } else if (Count == total) {
            Next.setEnabled(false);
        }
        AssignmentNo.setText("Assignment No:" + Count);
        try {
            ConnectionDB connect = new ConnectionDB();

            ResultSet rs = connect.statement.executeQuery("Select * from Assignment where AssignmentId='" + Count + "'");
            if (rs.next()) {
                que.setText(rs.getString("Assignment"));

            } else {
                JOptionPane.showMessageDialog(null, "No Assignment is given");
                System.exit(0);
            }
        } catch (Exception exception) {
            
        }

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Back) {
            this.setVisible(false);
            new StudentOptionChoose(name, email, rollno).setVisible(true);
        }
        String answer = Ans.getText();
        if (!answer.equals("")) {
            attempt++;
        }
        try {
            ConnectionDB connect = new ConnectionDB();
            connect.statement.executeUpdate("insert into AssignmentAns values('" + rollno + "','" + answer + "','" + Count + "','" + 0 + "')");

        } catch (Exception exception) {
            
        }

        if (e.getSource() == Next) {
            Ans.setText("");
            Count++;
            DisplayQue();
        } else if (e.getSource() == Submit) {
            this.setVisible(false);
            new AssignmentLastFrame(Count, attempt, name, email, rollno).setVisible(true);
        }
    }
    public static void main(String []args)
    {
    	new Assignment("","",0).setVisible(true);
    }
}
