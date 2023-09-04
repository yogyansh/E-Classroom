package Classroom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class ExamMainFrame extends JFrame implements ActionListener {

    JLabel background, background1, Title;
    JButton AddNewQue, AssignMarks, AllQue, DeleteQue, Result, Logout, Exit, Back;

    public ExamMainFrame() {
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1070, 550);
        setLocation(300, 150);
        setLayout(null);
        getContentPane().setBackground(Color.gray);
        setResizable(false);

        AddNewQue = new JButton("Add New Question");
        AddNewQue.setBounds(0, 35, 170, 30);
        AddNewQue.setFont(new Font("Courier", Font.BOLD, 15));
        AddNewQue.setBackground(Color.BLACK);
        AddNewQue.setForeground(Color.GRAY);
        AddNewQue.addActionListener(this);
        add(AddNewQue);

        Title = new JLabel("Exam Setup");
        Title.setFont(new Font("Courier", Font.BOLD, 18));
        Title.setBounds(420, 0, 200, 30);
        add(Title);

        AssignMarks = new JButton("Assign Marks");
        AssignMarks.setBounds(510, 35, 170, 30);
        AssignMarks.setFont(new Font("Courier", Font.BOLD, 15));
        AssignMarks.setBackground(Color.BLACK);
        AssignMarks.setForeground(Color.GRAY);
        AssignMarks.addActionListener(this);
        add(AssignMarks);

        AllQue = new JButton("All Question");
        AllQue.setBounds(340, 35, 170, 30);
        AllQue.setFont(new Font("Courier", Font.BOLD, 15));
        AllQue.setBackground(Color.BLACK);
        AllQue.setForeground(Color.GRAY);
        AllQue.addActionListener(this);
        add(AllQue);

        DeleteQue = new JButton("Delete Question");
        DeleteQue.setBounds(170, 35, 170, 30);
        DeleteQue.setFont(new Font("Courier", Font.BOLD, 15));
        DeleteQue.setBackground(Color.BLACK);
        DeleteQue.setForeground(Color.GRAY);
        DeleteQue.addActionListener(this);
        add(DeleteQue);

        Result = new JButton("Result");
        Result.setBounds(680, 35, 170, 30);
        Result.setFont(new Font("Courier", Font.BOLD, 15));
        Result.setBackground(Color.BLACK);
        Result.setForeground(Color.GRAY);
        Result.addActionListener(this);
        add(Result);

        Logout = new JButton("Logout");
        Logout.setBounds(850, 35, 170, 30);
        Logout.setFont(new Font("Courier", Font.BOLD, 15));
        Logout.setBackground(Color.BLACK);
        Logout.setForeground(Color.GRAY);
        Logout.addActionListener(this);
        add(Logout);

        ImageIcon Exit1 = new ImageIcon(ClassLoader.getSystemResource("Classroom/icons/cancel.png"));
        Image Exit2 = Exit1.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon Exit3 = new ImageIcon(Exit2);
        Exit = new JButton("", Exit3);
        Exit.setBounds(1020, 35, 30, 30);
        Exit.setBackground(Color.BLACK);
        Exit.setFont(new Font("Courier", Font.BOLD, 15));
        Exit.addActionListener(this);
        add(Exit);

        ImageIcon Back1 = new ImageIcon(ClassLoader.getSystemResource("Classroom/icons/back.png"));
        Image Back2 = Back1.getImage().getScaledInstance(25, 30, Image.SCALE_DEFAULT);
        ImageIcon Back3 = new ImageIcon(Back2);

        Back = new JButton("", Back3);
        Back.setBounds(5, 5, 23, 23);
        Back.setBackground(Color.GRAY);
        Back.setFont(new Font("Courier", Font.BOLD, 15));
        Back.addActionListener(this);
        add(Back);

        ImageIcon first = new ImageIcon(ClassLoader.getSystemResource("Classroom/icons/Teacher.png"));
        Image second = first.getImage().getScaledInstance(512, 512, Image.SCALE_DEFAULT);
        ImageIcon third = new ImageIcon(second);
        background = new JLabel(third);
        background.setBounds(90, 30, 512, 512);
        add(background);

        ImageIcon first1 = new ImageIcon(ClassLoader.getSystemResource("Classroom/icons/FTeacher.png"));
        Image second1 = first1.getImage().getScaledInstance(512, 512, Image.SCALE_DEFAULT);
        ImageIcon third1 = new ImageIcon(second1);
        background1 = new JLabel(third1);
        background1.setBounds(340, 30, 512, 512);
        add(background1);
    }

    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == Exit) {
                int op = JOptionPane.showConfirmDialog(this, "Do you want to Exit", "Confirm", JOptionPane.YES_NO_OPTION);
                if (op == 0) {
                    System.exit(0);
                }
            } else if (e.getSource() == Result) {
                this.setVisible(false);
                new ExamResult().setVisible(true);

            } else if (e.getSource() == Back) {
                this.setVisible(false);
                new TeacherOptionChoose().setVisible(true);
            } else if (e.getSource() == AddNewQue) {
                this.setVisible(false);
                new ExamNewQuestion().setVisible(true);

            } else if (e.getSource() == DeleteQue) {
                this.setVisible(false);
                new ExamDeleteQuestion().setVisible(true);
            } else if (e.getSource() == AllQue) {
                this.setVisible(false);
                new ExamAllQuestion().setVisible(true);
            } else if (e.getSource() == AssignMarks) {
                this.setVisible(false);
                new ExamMarksAssigning().setVisible(true);

            } else if (e.getSource() == Logout) {
                int op = JOptionPane.showConfirmDialog(this, "Do you want to Logout", "Confirm", JOptionPane.YES_NO_OPTION);
                if (op == 0) {
                    new OpeningPage().setVisible(true);
                    setVisible(false);
                }
            }
        } catch (Exception exception) {
            

        }
    }
    public static void main(String []args)
    {
    	new ExamMainFrame().setVisible(true);
    }
}
