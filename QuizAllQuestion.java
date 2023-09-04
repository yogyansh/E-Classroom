package Classroom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class QuizAllQuestion extends JFrame implements ActionListener {

    JLabel Title;
    JTable table;
    JScrollPane panel;
    JButton Exit, Back;
    int i;

    public QuizAllQuestion() {
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1050, 535);
        setLocation(300, 100);
        setLayout(null);
        getContentPane().setBackground(Color.GRAY);
        setResizable(false);

        Title = new JLabel("ALL QUESTIONS");
        Title.setBounds(340, 20, 350, 30);
        Title.setForeground(Color.BLACK);
        Title.setFont(new Font("Courier", Font.BOLD, 30));
        add(Title);

        ImageIcon Exit1 = new ImageIcon(ClassLoader.getSystemResource("Classroom/icons/cancel.png"));
        Image Exit2 = Exit1.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon Exit3 = new ImageIcon(Exit2);
        Exit = new JButton("", Exit3);
        Exit.setBounds(1000, 5, 30, 30);
        Exit.setFont(new Font("Courier", Font.BOLD, 15));
        Exit.setBackground(Color.GRAY);
        Exit.addActionListener(this);
        add(Exit);

        try {
            ConnectionDB connect = new ConnectionDB();
            ResultSet rs = connect.statement.executeQuery("Select count(*) As rowcount from question");
            rs.next();
            int count = rs.getInt(1);

            String ColName[] = {"ID", "Question", "Option 1", "Option 2", "Option 3", "Option 4", "Answer"};
            String data[][] = new String[count][7];

            rs = connect.statement.executeQuery("Select * from question");

            i = 0;
            while (rs.next()) {
                data[i][0] = rs.getString("QuestionId");
                data[i][1] = rs.getString("Question");
                data[i][2] = rs.getString("Option1");
                data[i][3] = rs.getString("Option2");
                data[i][4] = rs.getString("Option3");
                data[i][5] = rs.getString("Option4");
                data[i][6] = rs.getString("Answer");
                i++;
            }
            table = new JTable(data, ColName);
            table.setFont(new Font("Courier", Font.PLAIN, 15));
            table.getColumnModel().getColumn(0).setPreferredWidth(10);
            table.getColumnModel().getColumn(1).setPreferredWidth(320);
            panel = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            panel.setBounds(0, 70, 1020, 400);
            panel.setBackground(Color.GRAY);
            panel.setForeground(Color.WHITE);
            add(panel);

        } catch (Exception exception) {
            
        }
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
            new QuizMainFrame().setVisible(true);
        } else if (e.getSource() == Exit) {
            System.exit(0);
        }
    }
    public static void main(String []args)
    {
    	new QuizAllQuestion().setVisible(true);
    }
}
