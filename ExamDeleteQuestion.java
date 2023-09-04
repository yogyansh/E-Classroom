package Classroom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class ExamDeleteQuestion extends JFrame implements ActionListener {

    JLabel background, DeleteQuestion, QuestionId, Question;
    JButton Search, Delete, Exit, Reset, Back;
    JTextField ID;
    JTextArea que;

    public ExamDeleteQuestion() {
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(875, 535);
        setLocation(300, 100);
        setLayout(null);
        getContentPane().setBackground(Color.GRAY);
        setResizable(false);

        ImageIcon first = new ImageIcon(ClassLoader.getSystemResource("Classroom/icons/exam.png"));
        Image second = first.getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT);
        ImageIcon third = new ImageIcon(second);
        background = new JLabel(third);
        background.setBounds(0, 80, 300, 300);
        add(background);

        DeleteQuestion = new JLabel("DELETE QUESTION");
        DeleteQuestion.setBounds(490, 140, 250, 30);
        DeleteQuestion.setForeground(Color.BLACK);
        DeleteQuestion.setFont(new Font("Courier", Font.BOLD, 20));
        add(DeleteQuestion);

        QuestionId = new JLabel("Question ID:");
        QuestionId.setBounds(370, 180, 150, 30);
        QuestionId.setForeground(Color.BLACK);
        QuestionId.setFont(new Font("Courier", Font.BOLD, 20));
        add(QuestionId);

        ID = new JTextField(30);
        ID.setBounds(520, 180, 150, 30);
        ID.setFont(new Font("Courier", Font.BOLD, 20));
        add(ID);

        Search = new JButton("Search");
        Search.setBounds(690, 180, 120, 30);
        Search.setFont(new Font("Courier", Font.BOLD, 15));
        Search.setBackground(Color.BLACK);
        Search.setForeground(Color.GRAY);
        Search.addActionListener(this);
        add(Search);

        Question = new JLabel("Question:");
        Question.setBounds(340, 240, 100, 30);
        Question.setForeground(Color.BLACK);
        Question.setFont(new Font("Courier", Font.BOLD, 20));
        add(Question);

        que = new JTextArea();
        que.setBounds(440, 240, 400, 60);
        add(que);
        JScrollPane scroll = new JScrollPane(que, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setBounds(440, 240, 400, 60);
        add(scroll);

        Delete = new JButton("Delete");
        Delete.setBounds(320, 400, 170, 30);
        Delete.setFont(new Font("Courier", Font.BOLD, 15));
        Delete.setBackground(Color.BLACK);
        Delete.setForeground(Color.GRAY);
        Delete.addActionListener(this);
        add(Delete);

        ImageIcon Exit1 = new ImageIcon(ClassLoader.getSystemResource("Classroom/icons/cancel.png"));
        Image Exit2 = Exit1.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon Exit3 = new ImageIcon(Exit2);
        Exit = new JButton("", Exit3);
        Exit.setBounds(825, 5, 30, 30);
        Exit.setFont(new Font("Courier", Font.BOLD, 15));
        Exit.setBackground(Color.GRAY);
        Exit.addActionListener(this);
        add(Exit);

        Reset = new JButton("Reset");
        Reset.setBounds(500, 400, 170, 30);
        Reset.setFont(new Font("Courier", Font.BOLD, 15));
        Reset.setBackground(Color.BLACK);
        Reset.setForeground(Color.GRAY);
        Reset.addActionListener(this);
        add(Reset);

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
        if (e.getSource() == Back) {
            this.setVisible(false);
            new ExamMainFrame().setVisible(true);
        } else if (e.getSource() == Reset) {
            ID.setText("");
            que.setText("");
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
                ResultSet rs = connect.statement.executeQuery("select * from exam where QuestionId='" + Id + "'");
                if (rs.next()) {
                    if (e.getSource() == Search) {

                        {
                            que.setText(rs.getString("Question"));
                        }
                    } else if (e.getSource() == Delete) {

                        int confirm = JOptionPane.showConfirmDialog(this, "Do you sure you want to delete", "Confirm", JOptionPane.YES_NO_OPTION);
                        if (confirm == 0) {
                            connect.statement.executeUpdate("Delete from exam where QuestionId='" + Id + "'");
                            connect.statement.executeUpdate("Delete from examans where QuestionId='" + Id + "'");
                            JOptionPane.showMessageDialog(null, "Sucessfully deleted");

                            rs = connect.statement.executeQuery("Select count(QuestionId) from exam where QuestionId >'" + Id + "'");
                            rs.next();
                            int nextValue = rs.getInt(1);

                            int id = Integer.parseInt(Id);
                            int nextid;
                            while (nextValue != 0) {
                                nextid = id + 1;
                                connect.statement.executeUpdate("Update exam set QuestionId='" + id + "' where QuestionId='" + nextid + "'");
                                id = id + 1;
                                nextValue--;
                            }
                            this.setVisible(false);
                            new ExamMainFrame().setVisible(true);
                        }

                    }
                } else {
                    ID.setText("");
                    que.setText("");
                    JOptionPane.showMessageDialog(null, "Question Id does not exist");
                }

            } catch (Exception exception) {
                
            }
        }
    }
    public static void main(String []args)
    {
    	new ExamDeleteQuestion().setVisible(true);
    }

}
