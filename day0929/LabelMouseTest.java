package day0929;

import java.awt.event.*;
import javax.swing.*;

public class LabelMouseTest extends JFrame {
    public LabelMouseTest() {
        setTitle("마우스 올리기/내리기 테스트");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 250);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        JLabel label = new JLabel("Love Java");

        label.addMouseListener(new MouseListener() {
            public void mouseEntered(MouseEvent e) {
                label.setText("사랑해 자바");
            }

            public void mouseExited(MouseEvent e) {
                label.setText("Love Java");
            }

            public void mousePressed(MouseEvent e) {
            }

            public void mouseReleased(MouseEvent e) {
            }

            public void mouseClicked(MouseEvent e) {
            }
        });

        panel.add(label);
        setContentPane(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        new LabelMouseTest();
    }
}