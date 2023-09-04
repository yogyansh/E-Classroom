package Classroom;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;

public class Exam extends JFrame implements ActionListener {

    JLabel Welcome, Email, TotalQuestion, QuestionNo, que, background, TotalQue, ans;
    JTextArea Ans;
    int Id;
    String nam, mail;
    JButton Next, Submit;
    static int Count = 1, total = 0, timer = 90, attempt = 0;

    public Exam(String nam, String mail, int Id) {
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1050, 600);
        setLocation(300, 100);
        setLayout(null);
        setResizable(false);

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

            ResultSet rs = connect.statement.executeQuery("Select count(*) from Exam");
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
        que.setBounds(200, 140, 550, 30);
        que.setFont(new Font("Courier", Font.BOLD, 15));
        que.setForeground(Color.WHITE);
        background.add(que);

        Ans = new JTextArea();
        Ans.setBounds(200, 210, 550, 150);
        JScrollPane scroll = new JScrollPane(Ans, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setBounds(200, 210, 550, 150);
        background.add(scroll);

        ans = new JLabel("Answer:");
        ans.setBounds(200, 180, 100, 30);
        ans.setForeground(Color.WHITE);
        background.add(ans);

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
            new ExamLastFrame(total, attempt, nam, mail, Id).setVisible(true);
            return;
        } else if (Count == total) {
            Next.setEnabled(false);
            Submit.setEnabled(true);
        }
        QuestionNo.setText("Question No:" + Count);
        try {
            ConnectionDB connect = new ConnectionDB();

            ResultSet rs = connect.statement.executeQuery("Select * from Exam where QuestionId='" + Count + "'");
            if (rs.next()) {
                que.setText(rs.getString("Question"));

                timer = 90;
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
            Ans.setText("");
            Count++;
            DisplayQue();
        }
        timer--;
        try {
            Thread.sleep(100);
            repaint();
        } catch (Exception exception) {
            
        }

    }

    public void actionPerformed(ActionEvent e) {
        String answer = Ans.getText();
        if (!answer.equals("")) {
            attempt++;
        }
        try {
            ConnectionDB connect = new ConnectionDB();
            connect.statement.executeUpdate("insert into examAns values('" + Id + "','" + answer + "','" + Count + "','" + 0 + "')");

        } catch (Exception exception) {
            
        }

        if (e.getSource() == Next) {
            Ans.setText("");
            Count++;
            DisplayQue();
            repaint();
        } else if (e.getSource() == Submit) {
            this.setVisible(false);
            new ExamLastFrame(Count, attempt, nam, mail, Id).setVisible(true);
        }
    }
    public static void main(String []args)
    {
    	new Exam("","",0).setVisible(true);
    }
}
