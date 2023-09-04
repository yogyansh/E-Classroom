package Classroom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.util.regex.Pattern;

public class AssignmentCreate extends JFrame implements ActionListener, KeyListener {

    JLabel background, CreateAssignment, AssignmentId, Topic, Marks, mlabel, Subject;
    JTextArea Assignment;
    JTextField marks, SubjectName;
    JButton Save, Reset, Exit, Back;
    int flag = 0, count;

    public AssignmentCreate() {
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

        CreateAssignment = new JLabel("Create Assignment");
        CreateAssignment.setBounds(490, 70, 250, 30);
        CreateAssignment.setForeground(Color.BLACK);
        CreateAssignment.setFont(new Font("Courier", Font.BOLD, 20));
        add(CreateAssignment);

        AssignmentId = new JLabel("");
        AssignmentId.setBounds(370, 110, 250, 30);
        AssignmentId.setForeground(Color.BLACK);
        AssignmentId.setFont(new Font("Courier", Font.BOLD, 20));
        add(AssignmentId);
        try {
            ConnectionDB connect = new ConnectionDB();
            ResultSet rs = connect.statement.executeQuery("Select count(AssignmentId) from Assignment");
            rs.next();
            count = rs.getInt(1);
            count = count + 1;
            AssignmentId.setText("Assignment ID: " + count);
        } catch (Exception exception) {
            
        }

        Topic = new JLabel("Topic:");
        Topic.setBounds(320, 220, 100, 30);
        Topic.setForeground(Color.BLACK);
        Topic.setFont(new Font("Courier", Font.BOLD, 20));
        add(Topic);

        Subject = new JLabel("Subject:");
        Subject.setBounds(320, 180, 90, 30);
        Subject.setForeground(Color.BLACK);
        Subject.setFont(new Font("Courier", Font.BOLD, 20));
        add(Subject);

        Assignment = new JTextArea();
        Assignment.setBounds(430, 240, 420, 60);
        JScrollPane scroll = new JScrollPane(Assignment, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setBounds(430, 240, 420, 60);
        add(scroll);

        mlabel = new JLabel("");
        mlabel.setBounds(820, 180, 100, 30);
        mlabel.setForeground(Color.RED);
        add(mlabel);

        Marks = new JLabel("Marks:");
        Marks.setBounds(590, 180, 90, 30);
        Marks.setForeground(Color.BLACK);
        Marks.setFont(new Font("Courier", Font.BOLD, 20));
        add(Marks);

        marks = new JTextField();
        marks.setBounds(670, 180, 150, 30);
        marks.addKeyListener(this);
        add(marks);

        SubjectName = new JTextField();
        SubjectName.setBounds(430, 180, 150, 30);
        SubjectName.addKeyListener(this);
        add(SubjectName);

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
            new AssignmentMainFrame().setVisible(true);
        } else if (e.getSource() == Reset) {
            marks.setText("");
            Assignment.setText("");
            SubjectName.setText("");
        } else if (e.getSource() == Exit) {
            System.exit(0);
        } else if (e.getSource() == Save) {
            String assignment = Assignment.getText();
            String mark = marks.getText();
            String subj = SubjectName.getText();

            if (assignment.equals("") || mark.equals("") || subj.equals("")) {
                JOptionPane.showMessageDialog(null, "All fields must be filled");
                return;
            }
            if (flag == 1) {
                return;
            }
            try {
                ConnectionDB connect = new ConnectionDB();
                connect.statement.executeUpdate("Insert into Assignment values('" + count + "','" + subj + "','" + assignment + "','" + mark + "')");
                JOptionPane.showMessageDialog(null, "Saved Sucessfully");
                this.setVisible(false);
                new AssignmentMainFrame().setVisible(true);

            } catch (Exception exception) {
                
            }
        }
    }
    public static void main(String []args)
    {
    	new AssignmentCreate().setVisible(true);
    }
}
