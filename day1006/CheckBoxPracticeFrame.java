package day1006;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CheckBoxPracticeFrame extends JFrame {
    private JButton btn = new JButton("test button");

    public CheckBoxPracticeFrame() {
        super("CheckBox Practice Frame");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container c = getContentPane();
        c.setLayout(new FlowLayout());

        // 체크박스 컴포넌트 2개 생성
        JCheckBox a = new JCheckBox("버튼 비활성화");
        JCheckBox b = new JCheckBox("버튼 감추기");

        // 체크박스 컴포넌트들과 버튼을 컨텐트팬에 부착
        c.add(a);
        c.add(b);
        c.add(btn);

        // '버튼 비활성화' 체크 여부에 따라 버튼 활성화/비활성화
        a.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                btn.setEnabled(e.getStateChange() == ItemEvent.DESELECTED);
            }
        });

        // '버튼 감추기' 체크 여부에 따라 버튼 표시/숨김
        b.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                btn.setVisible(e.getStateChange() == ItemEvent.DESELECTED);
            }
        });

        setSize(250, 130);
        setVisible(true);
    }

    public static void main(String[] args) {
        new CheckBoxPracticeFrame();
    }
}