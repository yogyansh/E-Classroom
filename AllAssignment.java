package Classroom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class AllAssignment extends JFrame implements ActionListener {

    JLabel Title;
    JTable table;
    JButton Back, Exit;

    public AllAssignment() {
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1050, 650);
        setLocation(300, 100);
        setLayout(null);
        setResizable(false);
        getContentPane().setBackground(Color.WHITE);

        Title = new JLabel("ALL ASSIGNMENT");
        Title.setBounds(320, 20, 350, 30);
        Title.setForeground(Color.BLACK);
        Title.setFont(new Font("Courier", Font.BOLD, 30));
        add(Title);

        try {
            ConnectionDB connect = new ConnectionDB();
            ResultSet rs = connect.statement.executeQuery("Select count(AssignmentId)from Assignment");
            rs.next();
            int count = rs.getInt(1);
            String data[][] = new String[count][4];
            String ColName[] = {"Assignment ID", "Assignment", "Subject", "Marks"};
            rs = connect.statement.executeQuery("Select * from Assignment");
            int i = 0;
            while (rs.next()) {
                data[i][0] = rs.getString("AssignmentId");
                data[i][1] = rs.getString("Assignment");
                data[i][2] = rs.getString("Subject");
                data[i][3] = rs.getString("Marks");
                i++;
            }
            table = new JTable(data, ColName);
            table.getColumnModel().getColumn(1).setPreferredWidth(500);
            JScrollPane scroll = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            scroll.setBounds(0, 60, 1020, 450);
            add(scroll);
        } catch (Exception exception) {
            
        }
        ImageIcon Exit1 = new ImageIcon(ClassLoader.getSystemResource("Classroom/icons/cancel.png"));
        Image Exit2 = Exit1.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon Exit3 = new ImageIcon(Exit2);
        Exit = new JButton("", Exit3);
        Exit.setBounds(1000, 5, 30, 30);
        Exit.setFont(new Font("Courier", Font.BOLD, 15));
        Exit.setBackground(Color.WHITE);
        Exit.addActionListener(this);
        add(Exit);

        ImageIcon Back1 = new ImageIcon(ClassLoader.getSystemResource("Classroom/icons/back.png"));
        Image Back2 = Back1.getImage().getScaledInstance(25, 30, Image.SCALE_DEFAULT);
        ImageIcon Back3 = new ImageIcon(Back2);
        Back = new JButton("", Back3);
        Back.setBounds(5, 5, 23, 23);
        Back.setBackground(Color.WHITE);
        Back.setFont(new Font("Courier", Font.BOLD, 15));
        Back.addActionListener(this);
        add(Back);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Back) {
            this.setVisible(false);
            new AssignmentMainFrame().setVisible(true);
        } else if (e.getSource() == Exit) {
            System.exit(0);
        }
    }
    public static void main(String []args)
    {
    	new AllAssignment().setVisible(true);
    }
}
