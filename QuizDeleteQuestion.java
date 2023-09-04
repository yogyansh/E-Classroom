package Classroom;

import javax.swing.*;
import java.awt.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class QuizDeleteQuestion extends JFrame implements ActionListener {

    JLabel background, Question, Option1, Option2, Option3, Option4, Answer, QuestionId, DeleteQuestions;
    JTextField que, opt1, opt2, opt3, opt4, ans, ID;
    JButton Delete, Reset, Exit, Search, Back;

    public QuizDeleteQuestion() {
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(875, 535);
        setLocation(300, 100);
        setLayout(null);
        setResizable(false);

        ImageIcon first = new ImageIcon(ClassLoader.getSystemResource("Classroom/icons/questionMark.jpg"));
        Image second = first.getImage().getScaledInstance(875, 500, Image.SCALE_DEFAULT);
        ImageIcon third = new ImageIcon(second);
        background = new JLabel(third);
        background.setBounds(0, 0, 875, 500);
        add(background);

        DeleteQuestions = new JLabel("Delete Question");
        DeleteQuestions.setBounds(350, 30, 250, 30);
        DeleteQuestions.setForeground(Color.WHITE);
        DeleteQuestions.setFont(new Font("Courier", Font.BOLD, 20));
        background.add(DeleteQuestions);

        QuestionId = new JLabel("Question ID:");
        QuestionId.setBounds(225, 120, 150, 30);
        QuestionId.setForeground(Color.WHITE);
        QuestionId.setFont(new Font("Courier", Font.BOLD, 20));
        background.add(QuestionId);

        ID = new JTextField(30);
        ID.setBounds(355, 120, 150, 30);
        ID.setFont(new Font("Courier", Font.BOLD, 20));
        background.add(ID);

        Search = new JButton("Search");
        Search.setBounds(520, 120, 120, 30);
        Search.setFont(new Font("Courier", Font.BOLD, 15));
        Search.setBackground(Color.WHITE);
        Search.setForeground(Color.BLACK);
        Search.addActionListener(this);
        background.add(Search);

        Question = new JLabel("Question:");
        Question.setBounds(155, 160, 100, 30);
        Question.setForeground(Color.WHITE);
        Question.setFont(new Font("Courier", Font.BOLD, 20));
        background.add(Question);

        que = new JTextField();
        que.setBounds(250, 160, 390, 30);
        background.add(que);

        Option1 = new JLabel("Option1:");
        Option1.setBounds(155, 200, 100, 30);
        Option1.setForeground(Color.WHITE);
        Option1.setFont(new Font("Courier", Font.BOLD, 20));
        background.add(Option1);

        opt1 = new JTextField();
        opt1.setBounds(250, 200, 390, 30);
        background.add(opt1);

        Option2 = new JLabel("Option2:");
        Option2.setBounds(155, 240, 100, 30);
        Option2.setForeground(Color.WHITE);
        Option2.setFont(new Font("Courier", Font.BOLD, 20));
        background.add(Option2);

        opt2 = new JTextField();
        opt2.setBounds(250, 240, 390, 30);
        background.add(opt2);

        Option3 = new JLabel("Option3:");
        Option3.setBounds(155, 280, 100, 30);
        Option3.setForeground(Color.WHITE);
        Option3.setFont(new Font("Courier", Font.BOLD, 20));
        background.add(Option3);

        opt3 = new JTextField();
        opt3.setBounds(250, 280, 390, 30);
        background.add(opt3);

        Option4 = new JLabel("Option4:");
        Option4.setBounds(155, 320, 100, 30);
        Option4.setForeground(Color.WHITE);
        Option4.setFont(new Font("Courier", Font.BOLD, 20));
        background.add(Option4);

        opt4 = new JTextField();
        opt4.setBounds(250, 320, 390, 30);
        background.add(opt4);

        Answer = new JLabel("Answer:");
        Answer.setBounds(155, 360, 100, 30);
        Answer.setForeground(Color.WHITE);
        Answer.setFont(new Font("Courier", Font.BOLD, 20));
        background.add(Answer);

        ans = new JTextField();
        ans.setBounds(250, 360, 390, 30);
        background.add(ans);

        Delete = new JButton("Delete");
        Delete.setBounds(250, 450, 170, 30);
        Delete.setFont(new Font("Courier", Font.BOLD, 15));
        Delete.setBackground(Color.WHITE);
        Delete.setForeground(Color.BLACK);
        Delete.addActionListener(this);
        background.add(Delete);

        ImageIcon Exit1 = new ImageIcon(ClassLoader.getSystemResource("Classroom/icons/cancel.png"));
        Image Exit2 = Exit1.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon Exit3 = new ImageIcon(Exit2);
        Exit = new JButton("", Exit3);
        Exit.setBounds(825, 5, 30, 30);
        Exit.setFont(new Font("Courier", Font.BOLD, 15));
        Exit.setBackground(Color.BLACK);
        Exit.setForeground(Color.GRAY);
        Exit.addActionListener(this);
        background.add(Exit);

        Reset = new JButton("Reset");
        Reset.setBounds(430, 450, 170, 30);
        Reset.setFont(new Font("Courier", Font.BOLD, 15));
        Reset.setBackground(Color.WHITE);
        Reset.setForeground(Color.BLACK);
        Reset.addActionListener(this);
        background.add(Reset);

        ImageIcon Back1 = new ImageIcon(ClassLoader.getSystemResource("Classroom/icons/back.png"));
        Image Back2 = Back1.getImage().getScaledInstance(25, 30, Image.SCALE_DEFAULT);
        ImageIcon Back3 = new ImageIcon(Back2);

        Back = new JButton("", Back3);
        Back.setBounds(5, 5, 23, 23);
        Back.setFont(new Font("Courier", Font.BOLD, 15));
        Back.addActionListener(this);
        add(Back);

    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == Reset) {
            ID.setText("");
            que.setText("");
            opt1.setText("");
            opt2.setText("");
            opt3.setText("");
            opt4.setText("");
            ans.setText("");
        } else if (e.getSource() == Back) {
            this.setVisible(false);
            new QuizMainFrame().setVisible(true);
        } else if (e.getSource() == Exit) {
            System.exit(0);
        } else {
            try {
                ConnectionDB connect = new ConnectionDB();
                String Id = ID.getText();
                if (Id.equals("")) {
                    JOptionPane.showMessageDialog(null, "Enter Question Id");
                    return;
                }
                ResultSet rs = connect.statement.executeQuery("select * from question where QuestionId='" + Id + "'");
                if (rs.next()) {
                    if (e.getSource() == Search) {

                        {

                            que.setText(rs.getString("Question"));
                            opt1.setText(rs.getString("Option1"));
                            opt2.setText(rs.getString("Option2"));
                            opt3.setText(rs.getString("Option3"));
                            opt4.setText(rs.getString("Option4"));
                            ans.setText(rs.getString("Answer"));
                        }
                    } else if (e.getSource() == Delete) {

                        int confirm = JOptionPane.showConfirmDialog(this, "Do you sure you want to delete", "Confirm", JOptionPane.YES_NO_OPTION);
                        if (confirm == 0) {
                            connect.statement.executeUpdate("Delete from Question where QuestionId='" + Id + "'");
                            connect.statement.executeUpdate("Delete from Quizans where QuestionId='" + Id + "'");
                            JOptionPane.showMessageDialog(null, "Sucessfully deleted");

                            rs = connect.statement.executeQuery("Select count(QuestionId) from Question where QuestionId >'" + Id + "'");
                            rs.next();
                            int nextValue = rs.getInt(1);

                            int id = Integer.parseInt(Id);
                            int nextid;
                            while (nextValue != 0) {
                                nextid = id + 1;
                                connect.statement.executeUpdate("Update Question set QuestionId='" + id + "' where QuestionId='" + nextid + "'");
                                id = id + 1;
                                nextValue--;
                            }
                            this.setVisible(false);
                            new QuizMainFrame().setVisible(true);
                        }

                    }
                } else {
                    ID.setText("");
                    que.setText("");
                    opt1.setText("");
                    opt2.setText("");
                    opt3.setText("");
                    opt4.setText("");
                    ans.setText("");
                    JOptionPane.showMessageDialog(null, "Question Id does not exist");
                }

            } catch (Exception exception) {
                
            }
        }
    }
    public static void main(String []args)
    {
    	new QuizDeleteQuestion().setVisible(true);
    }

}
