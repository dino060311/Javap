package day0929;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ClickCount extends JFrame {
    public ClickCount() {
        super("클릭 횟수 카운트");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 100);
        setContentPane(new MyPanel());
        setVisible(true);
    }

    class MyPanel extends JPanel {
        private JButton[] btns = {
                new JButton("0"),
                new JButton("0"),
                new JButton("0"),
                new JButton("0"),
                new JButton("0")
        };

        public MyPanel() {
            for (JButton b : btns) {
                add(b);
                b.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JButton button = (JButton) e.getSource();
                        int count = Integer.parseInt(button.getText());
                        button.setText(Integer.toString(count + 1));
                    }
                });
            }
        }
    }

    public static void main(String[] args) {
        new ClickCount();
    }
}