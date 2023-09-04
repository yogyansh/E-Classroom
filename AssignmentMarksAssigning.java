package Classroom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.regex.Pattern;

public class AssignmentMarksAssigning extends JFrame implements ActionListener, KeyListener {

    JLabel background, AssigningMarks, StudentId, stu, Marks, AssignmentId, que, mlabel;
    JButton Search, Enter, Exit, Back;
    JTextField StuId, number, QueId;
    int flag = 0;

    public AssignmentMarksAssigning() {
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1070, 550);
        setLocation(300, 150);
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
        AssigningMarks.setBounds(410, 30, 250, 30);
        AssigningMarks.setForeground(Color.BLACK);
        AssigningMarks.setFont(new Font("Courier", Font.BOLD, 20));
        add(AssigningMarks);

        StudentId = new JLabel("Student ID:");
        StudentId.setBounds(380, 90, 120, 30);
        StudentId.setForeground(Color.BLACK);
        StudentId.setFont(new Font("Courier", Font.BOLD, 20));
        add(StudentId);

        AssignmentId = new JLabel("Assignment ID:");
        AssignmentId.setBounds(380, 130, 150, 30);
        AssignmentId.setForeground(Color.BLACK);
        AssignmentId.setFont(new Font("Courier", Font.BOLD, 20));
        add(AssignmentId);

        QueId = new JTextField();
        QueId.setBounds(530, 130, 170, 30);
        add(QueId);

        StuId = new JTextField();
        StuId.setBounds(530, 90, 170, 30);
        add(StuId);

        number = new JTextField();
        number.setBounds(420, 390, 170, 30);
        number.addKeyListener(this);
        add(number);

        Marks = new JLabel("Marks:");
        Marks.setBounds(340, 390, 80, 30);
        Marks.setForeground(Color.BLACK);
        Marks.setFont(new Font("Courier", Font.BOLD, 20));
        add(Marks);

        mlabel = new JLabel("");
        mlabel.setBounds(590, 390, 100, 30);
        mlabel.setForeground(Color.RED);
        add(mlabel);

        que = new JLabel("Answer");
        que.setBounds(490, 150, 300, 150);
        add(que);

        Enter = new JButton("Enter");
        Enter.setFont(new Font("Courier", Font.BOLD, 15));
        Enter.setBounds(420, 430, 170, 30);
        Enter.setBackground(Color.BLACK);
        Enter.setForeground(Color.GRAY);
        Enter.addActionListener(this);
        add(Enter);

        Search = new JButton("Search");
        Search.setBounds(750, 90, 150, 30);
        Search.setFont(new Font("Courier", Font.BOLD, 15));
        Search.setBackground(Color.BLACK);
        Search.setForeground(Color.GRAY);
        Search.addActionListener(this);
        add(Search);

        ImageIcon Exit1 = new ImageIcon(ClassLoader.getSystemResource("Classroom/icons/cancel.png"));
        Image Exit2 = Exit1.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon Exit3 = new ImageIcon(Exit2);
        Exit = new JButton("", Exit3);
        Exit.setBounds(1020, 5, 30, 30);
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
            new AssignmentMainFrame().setVisible(true);
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
                    JOptionPane.showMessageDialog(null, "Enter Assignment Id");
                    return;
                }
                ResultSet rs = connect.statement.executeQuery("select * from AssignmentAns where StudentId='" + Stu + "' and AssignmentId= '" + Que + "'");
                if (rs.next()) {
                    if (e.getSource() == Search) {

                        que.setText("Ans" + rs.getInt("AssignmentId") + ":" + rs.getString("Answer"));
                    } else if (e.getSource() == Enter) {
                        String num = number.getText();

                        if (num.equals("")) {
                            JOptionPane.showMessageDialog(null, "Enter Marks");
                            return;
                        }

                        connect.statement.executeUpdate("Update AssignmentAns Set Marks='" + num + "' where StudentId='" + Stu + "' and AssignmentId='" + Que + "'");
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
                    JOptionPane.showMessageDialog(null, "Student ID or Assignment ID does not exist");
                }

            } catch (Exception exception) {
                

            }
        }

    }
    public static void main(String []args)
    {
    	new AssignmentMarksAssigning().setVisible(true);
    }
}
