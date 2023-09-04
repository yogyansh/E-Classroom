package Classroom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class QuizResult extends JFrame implements ActionListener {

    JLabel Title, Filter;
    JTable table;
    JButton Exit, Search, SearchById, Back;
    int i;
    JScrollPane panel;
    String ColName[] = {"ID", "Name", "Email", "Contact", "Marks"};
    String data[][];

    public QuizResult() {
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1050, 700);
        setLocation(300, 100);
        setLayout(null);
        getContentPane().setBackground(Color.GRAY);
        setResizable(false);

        SearchById = new JButton("Filter By ID");
        SearchById.setBounds(355, 60, 180, 30);
        SearchById.setFont(new Font("Courier", Font.BOLD, 15));
        SearchById.setBackground(Color.BLACK);
        SearchById.addActionListener(this);
        SearchById.setForeground(Color.GRAY);
        add(SearchById);

        Search = new JButton("Filter by Marks");
        Search.setBounds(540, 60, 180, 30);
        Search.setFont(new Font("Courier", Font.BOLD, 15));
        Search.setBackground(Color.BLACK);
        Search.setForeground(Color.GRAY);
        Search.addActionListener(this);
        add(Search);

        Title = new JLabel("Result");
        Title.setBounds(470, 20, 350, 30);
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

        ImageIcon Back1 = new ImageIcon(ClassLoader.getSystemResource("Classroom/icons/back.png"));
        Image Back2 = Back1.getImage().getScaledInstance(25, 30, Image.SCALE_DEFAULT);
        ImageIcon Back3 = new ImageIcon(Back2);

        Back = new JButton("", Back3);
        Back.setBounds(5, 5, 23, 23);
        Back.setFont(new Font("Courier", Font.BOLD, 15));
        Back.setBackground(Color.GRAY);
        Back.addActionListener(this);
        add(Back);

        OrginalData();

    }

    public void Table(String Data[][]) {
        table = new JTable(Data, ColName);
        table.setFont(new Font("Courier", Font.PLAIN, 15));
        panel = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panel.setBounds(0, 110, 1020, 500);
        panel.setBackground(Color.GRAY);
        panel.setForeground(Color.WHITE);
        add(panel);
    }

    public void OrginalData() {
        try {
            ConnectionDB connect = new ConnectionDB();
            ResultSet rs = connect.statement.executeQuery("Select count(*) As rowcount from studentlogin");
            rs.next();
            int count = rs.getInt(1);

            data = new String[count][5];

            rs = connect.statement.executeQuery("Select * from studentlogin");

            i = 0;
            while (rs.next()) {
                data[i][0] = rs.getString("Id");
                data[i][1] = rs.getString("Name");
                data[i][2] = rs.getString("Email");
                data[i][3] = rs.getString("Contact");
                data[i][4] = rs.getString("Result");

                i++;
            }
            Table(data);

        } catch (Exception exception) {
            
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Exit) {
            System.exit(0);
        } else if (e.getSource() == Back) {
            this.setVisible(false);
            new QuizMainFrame().setVisible(true);
        } else if (e.getSource() == Search) {
            try {
                ConnectionDB connect = new ConnectionDB();
                ResultSet rs = connect.statement.executeQuery("Select * from studentlogin order by Result desc");
                i = 0;
                while (rs.next()) {
                    data[i][0] = rs.getString("Id");
                    data[i][1] = rs.getString("Name");
                    data[i][2] = rs.getString("Email");
                    data[i][3] = rs.getString("Contact");
                    data[i][4] = rs.getString("Result");

                    i++;
                }
                Table(data);

            } catch (Exception exception) {
                
            }
        } else if (e.getSource() == SearchById) {
            OrginalData();
        }
    }
    public static void main(String []args)
    {
    	new QuizResult().setVisible(true);
    }
}
