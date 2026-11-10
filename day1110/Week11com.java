
package day1110;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Week11com extends JFrame {

    HashMap<String, Integer> item = new HashMap<String, Integer>();

    String[] menu = { "고추장", "만두", "새우깡", "콜라", "참치캔", "치약", "연어", "삼겹살" };
    Integer[] price = { 3000, 500, 1500, 600, 2000, 1000, 2500, 2500 };
    Integer[] cnt = { 1, 2, 3, 4, 5, 6, 7 };

    JLabel lbprice = new JLabel();

    // JComboBox 제네릭 타입 지정
    JComboBox<String> comMenu = new JComboBox<String>();
    JComboBox<Integer> comcnt = new JComboBox<Integer>(cnt);

    JTextArea resultArea = new JTextArea(5, 30);

    JButton btnadd = new JButton("추가");
    JButton btndel = new JButton("삭제");

    JLabel lbtotal = new JLabel("총합 : 0 원");

    Integer total = 0;
    String resultTxt = "";
    Integer dan;
    String cbItem;

    public Week11com() {
        super("week11 combination");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // HashMap에 품목과 가격 저장
        for (int i = 0; i < menu.length; i++) {
            item.put(menu[i], price[i]);
        }

        // HashMap의 Key 값을 JComboBox에 설정
        for (String key : item.keySet()) {
            comMenu.addItem(key);
        }

        // 처음 선택된 품목의 단가 설정
        cbItem = (String) comMenu.getSelectedItem();

        if (cbItem != null) {
            dan = item.get(cbItem);
            lbprice.setText("단가 :" + dan.toString());
        }

        Container c = getContentPane();
        c.setLayout(new BorderLayout());

        c.add(new North(), BorderLayout.NORTH);
        c.add(new Center(), BorderLayout.CENTER);
        c.add(new South(), BorderLayout.SOUTH);

        // 품목 선택 이벤트
        comMenu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cbItem = (String) comMenu.getSelectedItem();

                if (cbItem != null) {
                    dan = item.get(cbItem);
                    lbprice.setText("단가 :" + dan.toString());
                }
            }
        });

        // 추가 버튼 이벤트
        btnadd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (cbItem == null || dan == null) {
                    return;
                }

                Integer ncnt = (Integer) comcnt.getSelectedItem();
                Integer sub = dan * ncnt;

                resultTxt += cbItem + ": 단가 " + dan.toString()
                        + "," + ncnt.toString() + "개\t합 : " + sub.toString();

                resultTxt += "\n";

                resultArea.setText(resultTxt);

                total += sub;

                // 총합 및 할인 표시
                updateTotal();
            }
        });

        // 삭제 버튼 이벤트
        btndel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String selectedItem = (String) comMenu.getSelectedItem();

                if (selectedItem != null) {

                    // HashMap에서 해당 Key 삭제
                    item.remove(selectedItem);

                    // HashMap의 Key 값으로 JComboBox 다시 설정
                    String[] keys = item.keySet().toArray(new String[0]);

                    comMenu.setModel(new DefaultComboBoxModel<String>(keys));

                    // 삭제 후 단가 갱신
                    cbItem = (String) comMenu.getSelectedItem();

                    if (cbItem != null) {
                        dan = item.get(cbItem);
                        lbprice.setText("단가 :" + dan.toString());
                    } else {
                        dan = null;
                        lbprice.setText("단가 :");
                    }
                }
            }
        });

        setSize(400, 300);
        setVisible(true);
    }

    // 총합 및 할인 금액 계산
    public void updateTotal() {

        if (total >= 10000) {

            Integer discount = total * 5 / 100;
            Integer finalTotal = total - discount;

            lbtotal.setText("총합 : " + total + "원 / 5% 할인 : "
                    + discount + "원 / 결제금액 : " + finalTotal + "원");

        } else {

            lbtotal.setText("총합 : " + total
                    + "원 (10000원 이상 구매 시 5% 할인)");
        }
    }

    class North extends JPanel {
        public North() {
            setLayout(new FlowLayout());

            lbprice.setPreferredSize(new Dimension(70, 50));
            lbprice.setText("단가 : ");

            add(comMenu);
            add(lbprice);
            add(comcnt);
            add(btnadd);
            add(btndel);
        }
    }

    class Center extends JPanel {
        public Center() {
            resultArea.setEditable(false);
            resultArea.setFont(new Font("맑은 고딕", Font.PLAIN, 14));

            JScrollPane scrollPane = new JScrollPane(resultArea);
            add(scrollPane);
        }
    }

    class South extends JPanel {
        public South() {
            lbtotal.setPreferredSize(new Dimension(350, 50));
            add(lbtotal);
        }
    }

    public static void main(String[] args) {
        new Week11com();
    }
}
