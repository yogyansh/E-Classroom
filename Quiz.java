package Classroom;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;

public class Quiz extends JFrame implements ActionListener {

    JLabel que, background, Welcome, Name, Email, TotalQue, QuestionNo;
    JRadioButton First, Second, Third, Fourth;
    ButtonGroup buttongroup;
    JButton Submit, Next;
    int total = 0, value = 0, Id;
    static int timer = 15, Count = 1, Result = 0;
    String name, email;

    public Quiz(String nam, String mail, int Id) {
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1050, 600);
        setLocation(300, 100);
        setLayout(null);
        setResizable(false);

        name = nam;
        email = mail;
        this.Id = Id;

        ImageIcon first = new ImageIcon(ClassLoader.getSystemResource("Classroom/icons/questionMark.jpg"));
        Image second = first.getImage().getScaledInstance(1050, 600, Image.SCALE_DEFAULT);
        ImageIcon third = new ImageIcon(second);
        background = new JLabel(third);
        background.setBounds(0, 0, 1050, 600);
        add(background);

        Welcome = new JLabel("Welcome " + nam);
        Welcome.setBounds(30, 10, 200, 30);
        Welcome.setFont(new Font("Courier", Font.BOLD, 20));
        Welcome.setForeground(Color.WHITE);
        background.add(Welcome);

        Email = new JLabel(mail);
        Email.setBounds(30, 30, 300, 30);
        Email.setFont(new Font("Courier", Font.BOLD, 13));
        Email.setForeground(Color.WHITE);
        background.add(Email);

        try {
            ConnectionDB connect = new ConnectionDB();

            ResultSet rs = connect.statement.executeQuery("Select count(*) from question");
            if (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (Exception exception) {
            
        }

        TotalQue = new JLabel("Total Question:" + total);
        TotalQue.setBounds(200, 380, 200, 30);
        TotalQue.setFont(new Font("Courier", Font.BOLD, 15));
        TotalQue.setForeground(Color.WHITE);
        background.add(TotalQue);

        QuestionNo = new JLabel("");
        QuestionNo.setBounds(200, 420, 200, 30);
        QuestionNo.setFont(new Font("Courier", Font.BOLD, 15));
        QuestionNo.setForeground(Color.WHITE);
        background.add(QuestionNo);

        que = new JLabel("");
        que.setBounds(200, 140, 800, 30);
        que.setFont(new Font("Courier", Font.BOLD, 15));
        que.setForeground(Color.WHITE);
        background.add(que);

        First = new JRadioButton("");
        First.setBounds(200, 190, 300, 30);
        First.setForeground(Color.BLACK);
        First.setBackground(Color.WHITE);
        background.add(First);

        Second = new JRadioButton("");
        Second.setBounds(200, 230, 300, 30);
        Second.setForeground(Color.BLACK);
        Second.setBackground(Color.WHITE);
        background.add(Second);

        Third = new JRadioButton("");
        Third.setBounds(200, 270, 300, 30);
        Third.setForeground(Color.BLACK);
        Third.setBackground(Color.WHITE);
        background.add(Third);

        Fourth = new JRadioButton("");
        Fourth.setBounds(200, 310, 300, 30);
        Fourth.setForeground(Color.BLACK);
        Fourth.setBackground(Color.WHITE);
        background.add(Fourth);

        buttongroup = new ButtonGroup();
        buttongroup.add(First);
        buttongroup.add(Second);
        buttongroup.add(Third);
        buttongroup.add(Fourth);

        Next = new JButton("Next");
        Next.setBounds(700, 410, 100, 30);
        Next.setForeground(Color.BLACK);
        Next.setBackground(Color.WHITE);
        background.add(Next);
        Next.addActionListener(this);

        Submit = new JButton("Submit");
        Submit.setBounds(700, 450, 100, 30);
        Submit.setEnabled(false);
        Submit.setForeground(Color.BLACK);
        Submit.setBackground(Color.WHITE);
        background.add(Submit);
        Submit.addActionListener(this);

        DisplayQue();
        repaint();
    }

    public void DisplayQue() {
        if (Count > total) {
            this.setVisible(false);
            new QuizLastFrame("" + value, Result, name, email, Id).setVisible(true);
            return;
        } else if (Count == total) {
            Next.setEnabled(false);
            Submit.setEnabled(true);
        }
        QuestionNo.setText("Question No:" + Count);
        try {
            ConnectionDB connect = new ConnectionDB();

            ResultSet rs = connect.statement.executeQuery("Select * from question where QuestionId='" + Count + "'");
            if (rs.next()) {
                que.setText(rs.getString("Question"));
                First.setText(rs.getString("Option1"));
                First.setActionCommand(rs.getString("Option1"));
                Second.setText(rs.getString("Option2"));
                Second.setActionCommand(rs.getString("Option2"));
                Third.setText(rs.getString("Option3"));
                Third.setActionCommand(rs.getString("Option3"));
                Fourth.setText(rs.getString("Option4"));
                Fourth.setActionCommand(rs.getString("Option4"));
                buttongroup.clearSelection();

                timer = 15;
            } else {
                JOptionPane.showMessageDialog(null, "Exam has not Started yet");
                System.exit(0);
            }
        } catch (Exception exception) {
            
        }

    }

    public void paint(Graphics graphics) {
        super.paint(graphics);
        String time = "Time Left -" + timer;
        if (timer > 0) {
            graphics.setColor(Color.WHITE);
            graphics.setFont(new Font("Courier", Font.CENTER_BASELINE, 18));
            graphics.drawString(time, 200, 100);
        } else {
            Count++;
            DisplayQue();
        }
        timer--;
        try {
            Thread.sleep(1000);
            repaint();
        } catch (Exception exception) {
            
        }

    }

    public void Marks() {
        if (buttongroup.getSelection() != null) {
            value++;
            String ans1, ans2 = null;
            ans1 = buttongroup.getSelection().getActionCommand();
            try {
                ConnectionDB connect = new ConnectionDB();

                ResultSet rs = connect.statement.executeQuery("Select Answer from question where QuestionId='" + Count + "'");
                if (rs.next()) {
                    ans2 = rs.getString("Answer");
                }
            } catch (Exception exception) {
                
            }
            if (ans1.equalsIgnoreCase(ans2)) {
                Result++;
            }
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Next) {
            Marks();
            Count++;
            DisplayQue();
            repaint();
        } else if (e.getSource() == Submit) {
            Marks();
            try {
                ConnectionDB connect = new ConnectionDB();
                connect.statement.executeUpdate("insert into quizans values ('" + Id + "','" + Result + "')");
                 connect.statement.executeUpdate("Update Studentlogin set Result='"+Result+"' where Id='"+Id+"'");
            } catch (Exception exception) {
                
            }
            this.setVisible(false);
            new QuizLastFrame("" + value, Result, name, email, Id).setVisible(true);
        }
    }
    public static void main(String []args)
    {
    	new Quiz("","",0).setVisible(true);
    }
}
