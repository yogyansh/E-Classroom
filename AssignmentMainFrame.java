package Classroom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AssignmentMainFrame extends JFrame implements ActionListener {

    JButton Create, Delete, Logout, Back, Exit, Result, AllAssignment, AssignMarks;
    JLabel Title, background, background1;

    public AssignmentMainFrame() {
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1070, 550);
        setLocation(300, 150);
        setLayout(null);
        getContentPane().setBackground(Color.gray);
        setResizable(false);

        Create = new JButton("New Assignment");
        Create.setBounds(0, 35, 170, 30);
        Create.setFont(new Font("Courier", Font.BOLD, 15));
        Create.setBackground(Color.BLACK);
        Create.setForeground(Color.GRAY);
        Create.addActionListener(this);
        add(Create);

        Title = new JLabel("Assignment Setup");
        Title.setFont(new Font("Courier", Font.BOLD, 18));
        Title.setBounds(420, 0, 200, 30);
        add(Title);

        Delete = new JButton("Delete");
        Delete.setBounds(170, 35, 170, 30);
        Delete.setFont(new Font("Courier", Font.BOLD, 15));
        Delete.setBackground(Color.BLACK);
        Delete.setForeground(Color.GRAY);
        Delete.addActionListener(this);
        add(Delete);

        AllAssignment = new JButton("All Assignment");
        AllAssignment.setBounds(340, 35, 170, 30);
        AllAssignment.setFont(new Font("Courier", Font.BOLD, 15));
        AllAssignment.setBackground(Color.BLACK);
        AllAssignment.setForeground(Color.GRAY);
        AllAssignment.addActionListener(this);
        add(AllAssignment);

        AssignMarks = new JButton("Assign Marks");
        AssignMarks.setBounds(510, 35, 170, 30);
        AssignMarks.setFont(new Font("Courier", Font.BOLD, 15));
        AssignMarks.setBackground(Color.BLACK);
        AssignMarks.setForeground(Color.GRAY);
        AssignMarks.addActionListener(this);
        add(AssignMarks);

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
                new AssignmentResult().setVisible(true);

            } else if (e.getSource() == Back) {
                this.setVisible(false);
                new TeacherOptionChoose().setVisible(true);
            } else if (e.getSource() == Create) {
                this.setVisible(false);
                new AssignmentCreate().setVisible(true);

            } else if (e.getSource() == Delete) {
                this.setVisible(false);
                new AssignmentDelete().setVisible(true);
            } else if (e.getSource() == AllAssignment) {
                this.setVisible(false);
                new AllAssignment().setVisible(true);
            } else if (e.getSource() == AssignMarks) {
                this.setVisible(false);
                new AssignmentMarksAssigning().setVisible(true);

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
}
