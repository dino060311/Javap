package day0915;

import java.awt.*;
import javax.swing.*;

public class Calculator extends JFrame {

    public Calculator() {
        setTitle("계산기");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setBackground(Color.LIGHT_GRAY);
        topPanel.setLayout(new BorderLayout(10, 0));
        topPanel.setBorder(BorderFactory.createEmptyBorder(8, 40, 8, 25));
        topPanel.add(new JLabel("수식"), BorderLayout.WEST);
        topPanel.add(new JTextField(), BorderLayout.CENTER);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(5, 4, 5, 5));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        centerPanel.add(new JButton("C"));
        centerPanel.add(new JButton("UN"));
        centerPanel.add(new JButton("BK"));
        centerPanel.add(new JButton("/"));

        centerPanel.add(new JButton("7"));
        centerPanel.add(new JButton("8"));
        centerPanel.add(new JButton("9"));
        centerPanel.add(new JButton("x"));

        centerPanel.add(new JButton("4"));
        centerPanel.add(new JButton("5"));
        centerPanel.add(new JButton("6"));
        centerPanel.add(new JButton("-"));

        centerPanel.add(new JButton("1"));
        centerPanel.add(new JButton("2"));
        centerPanel.add(new JButton("3"));
        centerPanel.add(new JButton("+"));

        centerPanel.add(new JButton("0"));
        centerPanel.add(new JButton("."));

        JButton equalButton = new JButton("=");
        equalButton.setBackground(new Color(120, 200, 200));
        centerPanel.add(equalButton);

        centerPanel.add(new JButton("%"));

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.YELLOW);
        bottomPanel.setLayout(new BorderLayout(10, 0));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 25));
        bottomPanel.add(new JLabel("계산 결과"), BorderLayout.WEST);
        bottomPanel.add(new JTextField(), BorderLayout.CENTER);

        contentPane.add(topPanel, BorderLayout.NORTH);
        contentPane.add(centerPanel, BorderLayout.CENTER);
        contentPane.add(bottomPanel, BorderLayout.SOUTH);

        setSize(430, 480);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Calculator();
    }
}