package Classroom;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class ExamLastFrame extends JFrame implements ActionListener {

    JLabel background, Submission, QuestionAttempt, TotalQuestion;
    JButton Exit, Back;
    String name, email;
    int rollno;

    public ExamLastFrame(int que, int attempt, String name, String email, int rollno) {
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1050, 600);
        setLocation(300, 100);
        setLayout(null);
        setResizable(false);

        this.name = name;
        this.email = email;
        this.rollno = rollno;

        ImageIcon first = new ImageIcon(ClassLoader.getSystemResource("Classroom/icons/questionMark.jpg"));
        Image second = first.getImage().getScaledInstance(1050, 600, Image.SCALE_DEFAULT);
        ImageIcon third = new ImageIcon(second);
        background = new JLabel(third);
        background.setBounds(0, 0, 1050, 600);
        add(background);

        Submission = new JLabel("Your exam has been submitted succesfully");
        Submission.setBounds(190, 160, 650, 30);
        Submission.setFont(new Font("Viner Hand ITC", Font.BOLD, 30));
        Submission.setForeground(Color.WHITE);
        background.add(Submission);

        QuestionAttempt = new JLabel("Question Attempted: " + attempt);
        QuestionAttempt.setBounds(200, 260, 350, 30);
        QuestionAttempt.setForeground(Color.WHITE);
        QuestionAttempt.setFont(new Font("Viner Hand ITC", Font.BOLD, 30));
        background.add(QuestionAttempt);

        TotalQuestion = new JLabel("Total Question: " + que);
        TotalQuestion.setBounds(200, 300, 350, 30);
        TotalQuestion.setForeground(Color.WHITE);
        TotalQuestion.setFont(new Font("Viner Hand ITC", Font.BOLD, 30));
        background.add(TotalQuestion);

        ImageIcon Exit1 = new ImageIcon(ClassLoader.getSystemResource("Classroom/icons/cancel.png"));
        Image Exit2 = Exit1.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon Exit3 = new ImageIcon(Exit2);
        Exit = new JButton("", Exit3);
        Exit.setBounds(1000, 5, 30, 30);
        Exit.setFont(new Font("Courier", Font.BOLD, 15));
        Exit.setBackground(Color.WHITE);
        Exit.addActionListener(this);
        background.add(Exit);

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
    	new ExamLastFrame(0,0,"","",0).setVisible(true);
    }
}
