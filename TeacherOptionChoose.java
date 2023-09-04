package Classroom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TeacherOptionChoose extends JFrame implements ActionListener {

    JLabel background, background1;
    JButton Exit, Exam, Quiz, Assignment;

    public TeacherOptionChoose() {
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1070, 550);
        setLocation(300, 150);
        setLayout(null);
        getContentPane().setBackground(Color.gray);
        setResizable(false);

        ImageIcon first = new ImageIcon(ClassLoader.getSystemResource("Classroom/icons/Teacher.png"));
        Image second = first.getImage().getScaledInstance(512, 512, Image.SCALE_DEFAULT);
        ImageIcon third = new ImageIcon(second);
        background = new JLabel(third);
        background.setBounds(0, 30, 512, 512);
        add(background);

        ImageIcon first1 = new ImageIcon(ClassLoader.getSystemResource("Classroom/icons/FTeacher.png"));
        Image second1 = first1.getImage().getScaledInstance(512, 512, Image.SCALE_DEFAULT);
        ImageIcon third1 = new ImageIcon(second1);
        background1 = new JLabel(third1);
        background1.setBounds(280, 30, 512, 512);
        add(background1);

        Exam = new JButton("EXAM SETUP");
        Exam.setBounds(720, 200, 220, 50);
        Exam.setFont(new Font("Courier", Font.BOLD, 15));
        Exam.setBackground(Color.BLACK);
        Exam.setForeground(Color.GRAY);
        Exam.addActionListener(this);
        add(Exam);

        Quiz = new JButton("QUIZ SETUP");
        Quiz.setBounds(720, 270, 220, 50);
        Quiz.setFont(new Font("Courier", Font.BOLD, 15));
        Quiz.setBackground(Color.BLACK);
        Quiz.setForeground(Color.GRAY);
        Quiz.addActionListener(this);
        add(Quiz);

        Assignment = new JButton("ASSIGNMENT SETUP");
        Assignment.setBounds(720, 330, 220, 50);
        Assignment.setFont(new Font("Courier", Font.BOLD, 15));
        Assignment.setBackground(Color.BLACK);
        Assignment.setForeground(Color.GRAY);
        Assignment.addActionListener(this);
        add(Assignment);

        ImageIcon Exit1 = new ImageIcon(ClassLoader.getSystemResource("Classroom/icons/cancel.png"));
        Image Exit2 = Exit1.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon Exit3 = new ImageIcon(Exit2);
        Exit = new JButton("", Exit3);
        Exit.setBounds(1020, 0, 30, 30);
        Exit.setBackground(Color.GRAY);
        Exit.setFont(new Font("Courier", Font.BOLD, 15));
        Exit.addActionListener(this);
        add(Exit);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Quiz) {
            this.setVisible(false);
            new QuizMainFrame().setVisible(true);
        } else if (e.getSource() == Assignment) {
            this.setVisible(false);
            new AssignmentMainFrame().setVisible(true);
        } else if (e.getSource() == Exam) {
            this.setVisible(false);
            new ExamMainFrame().setVisible(true);
        } else if (e.getSource() == Exit) {
            System.exit(0);
        }
    }
    public static void main(String []args)
    {
    	new TeacherOptionChoose().setVisible(true);
    }
}
