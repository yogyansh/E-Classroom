package Classroom;

import javax.swing.*;
import java.awt.*;
import java.awt.*;
import java.awt.event.*;

public class Exam_Terms extends JFrame implements ActionListener {

    JLabel background, Title, Terms1, Terms2, Terms3, Terms11, Terms21, Terms22, Terms31;
    JButton Enter, Exit, Back;
    String Nam, mail;
    int Id;

    public Exam_Terms(String Nam, String mail, int Id) {
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1260, 626);
        setLocation(200, 150);
        setLayout(null);
        getContentPane().setBackground(Color.BLACK);
        setResizable(false);

        this.Nam = Nam;
        this.mail = mail;
        this.Id = Id;

        ImageIcon first = new ImageIcon(ClassLoader.getSystemResource("Classroom/icons/light.png"));
        Image second = first.getImage().getScaledInstance(512, 512, Image.SCALE_DEFAULT);
        ImageIcon third = new ImageIcon(second);
        background = new JLabel(third);
        background.setBounds(90, 0, 512, 512);
        add(background);

        Title = new JLabel("Terms and Conditions");
        Title.setForeground(Color.WHITE);
        Title.setFont(new Font("Viner Hand ITC", Font.CENTER_BASELINE, 30));
        Title.setBounds(700, 120, 400, 30);
        add(Title);

        Terms1 = new JLabel("1.Exam have a time limit so ensure that exam is completed within");
        Terms1.setForeground(Color.WHITE);
        Terms1.setFont(new Font("Courier", Font.CENTER_BASELINE, 18));
        Terms1.setBounds(640, 180, 600, 30);
        add(Terms1);

        Terms11 = new JLabel("  time limit");
        Terms11.setForeground(Color.WHITE);
        Terms11.setFont(new Font("Courier", Font.CENTER_BASELINE, 18));
        Terms11.setBounds(640, 210, 600, 30);
        add(Terms11);

        Terms2 = new JLabel("2.Countdown timer is shown in the exam  navigation block ,so");
        Terms2.setForeground(Color.WHITE);
        Terms2.setFont(new Font("Courier", Font.CENTER_BASELINE, 18));
        Terms2.setBounds(640, 260, 630, 30);
        add(Terms2);

        Terms21 = new JLabel("  before the timer run out, you have to press next for submission");
        Terms21.setForeground(Color.WHITE);
        Terms21.setFont(new Font("Courier", Font.CENTER_BASELINE, 18));
        Terms21.setBounds(640, 290, 630, 30);
        add(Terms21);

        Terms22 = new JLabel(" of Answers you have been filled so far");
        Terms22.setForeground(Color.WHITE);
        Terms22.setFont(new Font("Courier", Font.CENTER_BASELINE, 18));
        Terms22.setBounds(640, 320, 630, 30);
        add(Terms22);

        Terms3 = new JLabel("3.Once you entered into the exam there is no going back out of ");
        Terms3.setForeground(Color.WHITE);
        Terms3.setFont(new Font("Courier", Font.CENTER_BASELINE, 18));
        Terms3.setBounds(640, 370, 630, 30);
        add(Terms3);

        Terms31 = new JLabel(" it without submitted all questions");
        Terms31.setForeground(Color.WHITE);
        Terms31.setFont(new Font("Courier", Font.CENTER_BASELINE, 18));
        Terms31.setBounds(640, 400, 630, 30);
        add(Terms31);

        Enter = new JButton("Start");
        Enter.setBackground(Color.WHITE);
        Enter.setFont(new Font("Viner Hand ITC", Font.BOLD, 20));
        Enter.setBounds(725, 450, 110, 30);
        add(Enter);
        Enter.addActionListener(this);

        Exit = new JButton("Exit");
        Exit.setBackground(Color.WHITE);
        Exit.setFont(new Font("Viner Hand ITC", Font.BOLD, 20));
        Exit.setBounds(865, 450, 110, 30);
        add(Exit);
        Exit.addActionListener(this);

        ImageIcon Back1 = new ImageIcon(ClassLoader.getSystemResource("Classroom/icons/back.png"));
        Image Back2 = Back1.getImage().getScaledInstance(25, 30, Image.SCALE_DEFAULT);
        ImageIcon Back3 = new ImageIcon(Back2);
        Back = new JButton("", Back3);
        Back.setBounds(5, 5, 23, 23);
        Back.setFont(new Font("Courier", Font.BOLD, 15));
        Back.addActionListener(this);
        add(Back);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Enter) {
            setVisible(false);
            new Exam(Nam, mail, Id).setVisible(true);

        } else if (e.getSource() == Exit) {
            System.exit(0);
        } else if (e.getSource() == Back) {
            this.setVisible(false);
            new StudentOptionChoose(Nam, mail, Id).setVisible(true);
        }
    }
    public static void main(String []args)
    {
    	new Exam_Terms("","",0).setVisible(true);
    }
}
