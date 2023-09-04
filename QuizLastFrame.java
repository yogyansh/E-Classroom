package Classroom;

import javax.swing.*;
import java.awt.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class QuizLastFrame extends JFrame implements ActionListener {

    JLabel background, Title, MarksObtained, Marks, QuestionAttempt;
    JButton Exit, Back;
    String email, name;
    int rollno;

    public QuizLastFrame(String Value, int Result, String name, String email, int rollno) {
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(875, 535);
        setLocation(300, 100);
        setLayout(null);
        setResizable(false);

        this.name = name;
        this.email = email;
        this.rollno = rollno;

        ImageIcon first = new ImageIcon(ClassLoader.getSystemResource("Classroom/icons/questionMark.jpg"));
        Image second = first.getImage().getScaledInstance(875, 500, Image.SCALE_DEFAULT);
        ImageIcon third = new ImageIcon(second);
        background = new JLabel(third);
        background.setBounds(0, 0, 875, 500);
        add(background);

        Title = new JLabel("Congratulations");
        Title.setBounds(340, 15, 250, 35);
        Title.setForeground(Color.WHITE);
        Title.setFont(new Font("Viner Hand ITC", Font.BOLD, 30));
        background.add(Title);

        MarksObtained = new JLabel("Marks Obtained: " + Result);
        MarksObtained.setBounds(200, 250, 350, 30);
        MarksObtained.setForeground(Color.WHITE);
        MarksObtained.setFont(new Font("Viner Hand ITC", Font.BOLD, 30));
        background.add(MarksObtained);

        QuestionAttempt = new JLabel("Question Attempted: " + Value);
        QuestionAttempt.setBounds(200, 200, 350, 30);
        QuestionAttempt.setForeground(Color.WHITE);
        QuestionAttempt.setFont(new Font("Viner Hand ITC", Font.BOLD, 30));
        background.add(QuestionAttempt);

        Marks = new JLabel("");
        Marks.setBounds(450, 270, 300, 30);
        Marks.setForeground(Color.WHITE);
        Marks.setFont(new Font("Viner Hand ITC", Font.BOLD, 30));
        background.add(Marks);

        ImageIcon Exit1 = new ImageIcon(ClassLoader.getSystemResource("Classroom/icons/cancel.png"));
        Image Exit2 = Exit1.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon Exit3 = new ImageIcon(Exit2);
        Exit = new JButton("", Exit3);
        Exit.setBounds(820, 5, 30, 30);
        Exit.setFont(new Font("Courier", Font.BOLD, 15));
        Exit.setBackground(Color.BLACK);
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
    	new QuizLastFrame("",0,"","",0).setVisible(true);
    }

}
