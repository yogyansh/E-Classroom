package Classroom;

import javax.swing.*;
import java.awt.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class QuizEditQuestion extends JFrame implements ActionListener {

    JLabel background, Question, Option1, Option2, Option3, Option4, Answer, QuestionId, EditQuestions;
    JTextField que, opt1, opt2, opt3, opt4, ans, ID;
    JButton Save, Reset, Exit, Search, Back;

    public QuizEditQuestion() {
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

        EditQuestions = new JLabel("Edit Question");
        EditQuestions.setBounds(350, 30, 250, 30);
        EditQuestions.setForeground(Color.WHITE);
        EditQuestions.setFont(new Font("Courier", Font.BOLD, 20));
        background.add(EditQuestions);

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

        Save = new JButton("Save");
        Save.setBounds(250, 450, 170, 30);
        Save.setFont(new Font("Courier", Font.BOLD, 15));
        Save.setBackground(Color.WHITE);
        Save.setForeground(Color.BLACK);
        Save.addActionListener(this);
        background.add(Save);

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

        ImageIcon Back1 = new ImageIcon(ClassLoader.getSystemResource("Classroom/icons/back.png"));
        Image Back2 = Back1.getImage().getScaledInstance(25, 30, Image.SCALE_DEFAULT);
        ImageIcon Back3 = new ImageIcon(Back2);

        Back = new JButton("", Back3);
        Back.setBounds(5, 5, 23, 23);
        Back.setFont(new Font("Courier", Font.BOLD, 15));
        Back.addActionListener(this);
        add(Back);

        Reset = new JButton("Reset");
        Reset.setBounds(430, 450, 170, 30);
        Reset.setFont(new Font("Courier", Font.BOLD, 15));
        Reset.setBackground(Color.WHITE);
        Reset.setForeground(Color.BLACK);

        Reset.addActionListener(this);
        background.add(Reset);

    }

    public void empty() {
        ID.setText("");
        que.setText("");
        opt1.setText("");
        opt2.setText("");
        opt3.setText("");
        opt4.setText("");
        ans.setText("");
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == Reset) {
            empty();
        } else if (e.getSource() == Exit) {
            System.exit(0);
        } else if (e.getSource() == Back) {
            this.setVisible(false);
            new QuizMainFrame().setVisible(true);
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
                    } else if (e.getSource() == Save) {

                        String Que = que.getText();
                        String Opt1 = opt1.getText();
                        String Opt2 = opt2.getText();
                        String Opt3 = opt3.getText();
                        String Opt4 = opt4.getText();
                        String Ans = ans.getText();
                        if (Que.equals("") || Opt1.equals("") || Opt2.equals("") || Opt3.equals("") || Opt4.equals("") || Ans.equals("")) {
                            JOptionPane.showMessageDialog(null, "All fields must be filled");
                            return;
                        }
                        if (Opt1.equalsIgnoreCase(Ans) || Opt2.equalsIgnoreCase(Ans) || Opt3.equalsIgnoreCase(Ans) || Opt4.equalsIgnoreCase(Ans)) {
                            int confirm = JOptionPane.showConfirmDialog(this, "Do you sure you want to Save Changes", "Confirm", JOptionPane.YES_NO_OPTION);
                            if (confirm == 0) {

                                connect.statement.executeUpdate("Update question set Question='" + Que + "',Option1='" + Opt1 + "',Option2='" + Opt2 + "',Option3='" + Opt3 + "',Option4='" + Opt4 + "',Answer='" + Ans + "' where QuestionId='" + Id + "'");
                                empty();
                                JOptionPane.showMessageDialog(null, "Sucessfully Saved Changes");
                                this.setVisible(false);
                                new QuizMainFrame().setVisible(true);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Answer must be matched with any of option");
                        }

                    }
                } else {
                    empty();
                    JOptionPane.showMessageDialog(null, "Question Id does not exist");
                }

            } catch (Exception exception) {
                
            }
        }
    }
    public static void main(String []args)
    {
    	new QuizEditQuestion().setVisible(true);
    }
}
