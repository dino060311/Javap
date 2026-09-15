package day0915;

import java.awt.*;
import javax.swing.*;

public class Gui extends JFrame {
	public Gui() {
		setTitle("300x3000 스윙프레임 만들기");
		Container contentPane = getContentPane();
		JButton button = new JButton("Click");
		contentPane.add(button);
		setSize(300, 300);
		setVisible(true);
	}

	public static void main(String[] args) {
		new Gui();
	}

}