package Classroom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.regex.Pattern;

public class ExamMarksAssigning extends JFrame implements ActionListener, KeyListener {

    JLabel background, AssigningMarks, StudentId, stu, Marks, QuestionId, que, mlabel;
    JButton Search, Enter, Exit, Back;
    JTextField StuId, number, QueId;
    int flag = 0;

    public ExamMarksAssigning() {
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

        AssigningMarks = new JLabel("Assigning Marks");
        AssigningMarks.setBounds(490, 40, 250, 30);
        AssigningMarks.setForeground(Color.BLACK);
        AssigningMarks.setFont(new Font("Courier", Font.BOLD, 20));
        add(AssigningMarks);

        StudentId = new JLabel("Student ID:");
        StudentId.setBounds(370, 90, 150, 30);
        StudentId.setForeground(Color.BLACK);
        StudentId.setFont(new Font("Courier", Font.BOLD, 20));
        add(StudentId);

        QuestionId = new JLabel("Question ID:");
        QuestionId.setBounds(370, 130, 150, 30);
        QuestionId.setForeground(Color.BLACK);
        QuestionId.setFont(new Font("Courier", Font.BOLD, 20));
        add(QuestionId);

        QueId = new JTextField();
        QueId.setBounds(500, 130, 170, 30);
        add(QueId);

        StuId = new JTextField();
        StuId.setBounds(500, 90, 170, 30);
        add(StuId);

        number = new JTextField();
        number.setBounds(350, 390, 170, 30);
        number.addKeyListener(this);
        add(number);

        Marks = new JLabel("Marks:");
        Marks.setBounds(270, 390, 80, 30);
        Marks.setForeground(Color.BLACK);
        Marks.setFont(new Font("Courier", Font.BOLD, 20));
        add(Marks);

        que = new JLabel("Answer");
        que.setBounds(340, 150, 500, 150);
        add(que);

        Enter = new JButton("Enter");
        Enter.setFont(new Font("Courier", Font.BOLD, 15));
        Enter.setBounds(350, 430, 170, 30);
        Enter.setBackground(Color.BLACK);
        Enter.setForeground(Color.GRAY);
        Enter.addActionListener(this);
        add(Enter);

        Search = new JButton("Search");
        Search.setBounds(690, 90, 150, 30);
        Search.setFont(new Font("Courier", Font.BOLD, 15));
        Search.setBackground(Color.BLACK);
        Search.setForeground(Color.GRAY);
        Search.addActionListener(this);
        add(Search);

        mlabel = new JLabel("");
        mlabel.setBounds(530, 390, 170, 30);
        mlabel.setForeground(Color.RED);
        add(mlabel);

        ImageIcon Exit1 = new ImageIcon(ClassLoader.getSystemResource("Classroom/icons/cancel.png"));
        Image Exit2 = Exit1.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon Exit3 = new ImageIcon(Exit2);
        Exit = new JButton("", Exit3);
        Exit.setBounds(825, 5, 30, 30);
        Exit.setFont(new Font("Courier", Font.BOLD, 15));
        Exit.setBackground(Color.GRAY);
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

    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
        try {
            if (!(Pattern.compile("^[0-9]{1,5}$")).matcher(number.getText()).matches()) {
                mlabel.setText("*Only Digits");
                flag = 1;
            } else {
                mlabel.setText("");
                flag = 0;
            }
        } catch (Exception exception) {
            
        }

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Back) {
            this.setVisible(false);
            new ExamMainFrame().setVisible(true);
        } else if (e.getSource() == Exit) {
            System.exit(0);
        } else {

            try {
                ConnectionDB connect = new ConnectionDB();
                String Stu = StuId.getText();
                String Que = QueId.getText();
                if (Stu.equals("")) {
                    JOptionPane.showMessageDialog(null, "Enter Student Id");
                    return;
                }
                if (Que.equals("")) {
                    JOptionPane.showMessageDialog(null, "Enter Question Id");
                    return;
                }
                ResultSet rs = connect.statement.executeQuery("select * from examAns where StudentId='" + Stu + "' and QuestionId= '" + Que + "'");
                if (rs.next()) {
                    if (e.getSource() == Search) {

                        que.setText("Ans" + rs.getInt("QuestionId") + ":" + rs.getString("Answer"));
                    } else if (e.getSource() == Enter) {
                        String num = number.getText();

                        if (num.equals("")) {
                            JOptionPane.showMessageDialog(null, "Enter Marks");
                            return;
                        }
                        if (flag == 1) {
                            return;
                        }
                        connect.statement.executeUpdate("Update ExamAns Set Marks='" + num + "' where StudentId='" + Stu + "' and QuestionId='" + Que + "'");
                        JOptionPane.showMessageDialog(null, "Sucessfully Saved");
                        StuId.setText("");
                        QueId.setText("");
                        que.setText("");
                        number.setText("");

                    }
                } else {
                    StuId.setText("");
                    QueId.setText("");
                    que.setText("");
                    JOptionPane.showMessageDialog(null, "Student ID or Question ID does not exist");
                }

            } catch (Exception exception) {
                

            }
        }

    }
    public static void main(String []args)
    {
    	new ExamMarksAssigning().setVisible(true);
    }
}
