package Classroom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.util.regex.Pattern;

public class AssignmentDelete extends JFrame implements ActionListener {

    JLabel background, DeleteAssignment, AssignmentId, ALabel;
    JTextArea Assignment;
    JTextField ID;
    JButton Delete, Reset, Exit, Back, Search;
    int count;

    public AssignmentDelete() {
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1070, 550);
        setLocation(300, 150);
        setLayout(null);
        getContentPane().setBackground(Color.gray);
        setResizable(false);

        ImageIcon first = new ImageIcon(ClassLoader.getSystemResource("Classroom/icons/exam.png"));
        Image second = first.getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT);
        ImageIcon third = new ImageIcon(second);
        background = new JLabel(third);
        background.setBounds(0, 80, 300, 300);
        add(background);

        DeleteAssignment = new JLabel("Delete Assignment");
        DeleteAssignment.setBounds(490, 40, 250, 30);
        DeleteAssignment.setForeground(Color.BLACK);
        DeleteAssignment.setFont(new Font("Courier", Font.BOLD, 20));
        add(DeleteAssignment);

        AssignmentId = new JLabel("Assignment ID:");
        AssignmentId.setBounds(370, 110, 250, 30);
        AssignmentId.setForeground(Color.BLACK);
        AssignmentId.setFont(new Font("Courier", Font.BOLD, 20));
        add(AssignmentId);

        ALabel = new JLabel("Assignment Topic:");
        ALabel.setBounds(430, 210, 250, 30);
        ALabel.setForeground(Color.BLACK);
        ALabel.setFont(new Font("Courier", Font.BOLD, 20));
        add(ALabel);

        Search = new JButton("Search");
        Search.setBounds(710, 110, 120, 30);
        Search.setFont(new Font("Courier", Font.BOLD, 15));
        Search.setBackground(Color.BLACK);
        Search.setForeground(Color.GRAY);
        Search.addActionListener(this);
        add(Search);

        ID = new JTextField();
        ID.setBounds(520, 110, 170, 30);
        add(ID);

        Assignment = new JTextArea();
        Assignment.setBounds(320, 240, 420, 60);
        JScrollPane scroll = new JScrollPane(Assignment, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setBounds(320, 240, 420, 60);
        add(scroll);

        Delete = new JButton("Delete");
        Delete.setBounds(320, 450, 170, 30);
        Delete.setFont(new Font("Courier", Font.BOLD, 15));
        Delete.setBackground(Color.BLACK);
        Delete.setForeground(Color.GRAY);
        Delete.addActionListener(this);
        add(Delete);

        ImageIcon Exit1 = new ImageIcon(ClassLoader.getSystemResource("Classroom/icons/cancel.png"));
        Image Exit2 = Exit1.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon Exit3 = new ImageIcon(Exit2);
        Exit = new JButton("", Exit3);
        Exit.setBounds(1020, 5, 30, 30);
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

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Back) {
            this.setVisible(false);
            new AssignmentMainFrame().setVisible(true);
        } else if (e.getSource() == Reset) {
            Assignment.setText("");
            ID.setText("");
        } else if (e.getSource() == Exit) {
            System.exit(0);
        } else {
            try {
                ConnectionDB connect = new ConnectionDB();
                String Id = ID.getText();
                if (Id.equals("")) {
                    JOptionPane.showMessageDialog(null, "Enter Question Id");
                    return;
                }
                ResultSet rs = connect.statement.executeQuery("select * from Assignment where AssignmentId='" + Id + "'");
                if (rs.next()) {
                    if (e.getSource() == Search) {

                        {
                            Assignment.setText(rs.getString("Assignment"));
                        }
                    } else if (e.getSource() == Delete) {

                        int confirm = JOptionPane.showConfirmDialog(this, "Do you sure you want to delete", "Confirm", JOptionPane.YES_NO_OPTION);
                        if (confirm == 0) {
                            connect.statement.executeUpdate("Delete from Assignment where AssignmentId='" + Id + "'");
                            connect.statement.executeUpdate("Delete from Assignmentans where AssignmentId='" + Id + "'");
                            JOptionPane.showMessageDialog(null, "Sucessfully deleted");

                            rs = connect.statement.executeQuery("Select count(AssignmentId) from Assignment where AssignmentId >'" + Id + "'");
                            rs.next();
                            int nextValue = rs.getInt(1);

                            int id = Integer.parseInt(Id);
                            int nextid;
                            while (nextValue != 0) {
                                nextid = id + 1;
                                connect.statement.executeUpdate("Update Assignment set AssignmentId='" + id + "' where AssignmentId='" + nextid + "'");
                                id = id + 1;
                                nextValue--;
                            }
                            this.setVisible(false);
                            new AssignmentMainFrame().setVisible(true);
                        }

                    }
                } else {
                    ID.setText("");
                    Assignment.setText("");
                    JOptionPane.showMessageDialog(null, "Question Id does not exist");
                }

            } catch (Exception exception) {
                
            }
        }
    }
    public static void main(String []args)
    {
    	new AssignmentDelete().setVisible(true);
    }

}
