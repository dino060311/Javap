package day1006;

import javax.swing.*;
import java.awt.*;

public class LabelEx extends JFrame {
	public LabelEx() {
		setTitle("레이블 예제");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout());

		// 문자열 레이블 생성
		JLabel textLabel = new JLabel("사랑합니다.");

		// 이미지 레이블 생성
		ImageIcon beauty = new ImageIcon("day1006/image/beauty.jpg");
		JLabel imageLabel = new JLabel(beauty); // 이미지 레이블 생성

		// 문자열과 이미지를 모두 가진 레이블 생성
		ImageIcon normlIcon = new ImageIcon("day1006/image/normalIcon.gif");
		JLabel label = new JLabel("보고싶으면 전화하세요", normlIcon, SwingConstants.CENTER);

		// 컨텐트팬에 3개의 레이블 부착
		c.add(textLabel);
		c.add(imageLabel);
		c.add(label);

		setSize(400, 600);
		setVisible(true);
	}

	public static void main(String[] args) {
		new LabelEx();
	}
}