package Classroom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.regex.Pattern;

public class ExamNewQuestion extends JFrame implements ActionListener, KeyListener {

    JLabel background, AddNewQuestion, QuestionId, Question, Marks, mlabel;
    JTextArea que;
    JTextField marks;
    JButton Save, Reset, Exit, Back;
    int flag = 0, count;

    public ExamNewQuestion() {
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

        AddNewQuestion = new JLabel("ADD NEW QUESTION");
        AddNewQuestion.setBounds(490, 140, 250, 30);
        AddNewQuestion.setForeground(Color.BLACK);
        AddNewQuestion.setFont(new Font("Courier", Font.BOLD, 20));
        add(AddNewQuestion);

        QuestionId = new JLabel("");
        QuestionId.setBounds(370, 180, 150, 30);
        QuestionId.setForeground(Color.BLACK);
        QuestionId.setFont(new Font("Courier", Font.BOLD, 20));
        add(QuestionId);
        try {
            ConnectionDB connect = new ConnectionDB();
            ResultSet rs = connect.statement.executeQuery("Select count(QuestionId) from Exam");
            rs.next();
            count = rs.getInt(1);
            count = count + 1;
            QuestionId.setText("Question ID: " + count);
        } catch (Exception exception) {
            
        }

        Question = new JLabel("Question:");
        Question.setBounds(320, 220, 100, 30);
        Question.setForeground(Color.BLACK);
        Question.setFont(new Font("Courier", Font.BOLD, 20));
        add(Question);

        que = new JTextArea();
        que.setBounds(430, 220, 420, 60);
        JScrollPane scroll = new JScrollPane(que, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setBounds(430, 220, 420, 60);
        add(scroll);

        mlabel = new JLabel("");
        mlabel.setBounds(540, 300, 100, 30);
        mlabel.setForeground(Color.RED);
        add(mlabel);

        Marks = new JLabel("Marks:");
        Marks.setBounds(320, 300, 100, 30);
        Marks.setForeground(Color.BLACK);
        Marks.setFont(new Font("Courier", Font.BOLD, 20));
        add(Marks);

        marks = new JTextField();
        marks.setBounds(430, 300, 100, 30);
        marks.addKeyListener(this);
        add(marks);

        Save = new JButton("Save");
        Save.setBounds(320, 450, 170, 30);
        Save.setFont(new Font("Courier", Font.BOLD, 15));
        Save.setBackground(Color.BLACK);
        Save.setForeground(Color.GRAY);
        Save.addActionListener(this);
        add(Save);

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
        Reset.setBounds(500, 450, 170, 30);
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

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
        try {
            if (!(Pattern.compile("^[0-9]{1,5}$")).matcher(marks.getText()).matches()) {
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
        } else if (e.getSource() == Reset) {
            marks.setText("");
            que.setText("");
        } else if (e.getSource() == Exit) {
            System.exit(0);
        } else if (e.getSource() == Save) {
            String Que = que.getText();
            String mark = marks.getText();

            if (Que.equals("") || mark.equals("")) {
                JOptionPane.showMessageDialog(null, "All fields must be filled");
                return;
            }
            if (flag == 1) {
                return;
            }
            try {
                ConnectionDB connect = new ConnectionDB();
                connect.statement.executeUpdate("Insert into Exam values('" + count + "','" + Que + "','" + mark + "')");
                JOptionPane.showMessageDialog(null, "Saved Sucessfully");
                this.setVisible(false);
                new ExamMainFrame().setVisible(true);

            } catch (Exception exception) {
                
            }
        }
    }
    public static void main(String []args)
    {
    	new ExamNewQuestion().setVisible(true);
    }

}
