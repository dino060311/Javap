package day1006;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CardSwitchGame extends JFrame {
    public CardSwitchGame() {
        setTitle("카드 스위치 게임");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container c = getContentPane();
        MyPanel panel = new MyPanel();
        c.add(panel, BorderLayout.CENTER);

        setSize(300, 200);
        setVisible(true);
    }

    public static void main(String[] args) {
        new CardSwitchGame();
    }
}

class MyPanel extends JPanel {
    private CardLabel[] cardArray = new CardLabel[12];
    private CardLabel firstCard = null;
    private CardLabel[] prev = new CardLabel[2];

    public MyPanel() {
        setLayout(new GridLayout(3, 4, 10, 10));

        MouseClick mc = new MouseClick();

        for (int i = 0; i < cardArray.length; i++) {
            cardArray[i] = new CardLabel(i + 1);
            this.add(cardArray[i]);
            cardArray[i].addMouseListener(mc);
        }
    }

    public class MouseClick extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            if (firstCard == null) {
                if (prev[0] != null) {
                    prev[0].deSelect();
                }

                if (prev[1] != null) {
                    prev[1].deSelect();
                }

                firstCard = (CardLabel) e.getSource();
                firstCard.select();

                prev[0] = firstCard;
            } else {
                CardLabel secondCard = (CardLabel) e.getSource();

                if (firstCard == secondCard) {
                    firstCard.deSelect();
                    firstCard = null;
                    return;
                }

                secondCard.select();
                prev[1] = secondCard;

                int tmp = firstCard.getNumber();
                firstCard.setNumber(secondCard.getNumber());
                secondCard.setNumber(tmp);

                firstCard = null;
            }
        }
    }
}

class CardLabel extends JLabel {
    private int number;

    private static final Color NORMAL_COLOR = Color.YELLOW;
    private static final Color SELECTED_COLOR = Color.MAGENTA;

    public CardLabel(int number) {
        super(Integer.toString(number));

        this.number = number;

        setFont(new Font("Gothic", Font.PLAIN, 20));
        setHorizontalAlignment(JLabel.CENTER);
        setOpaque(true);
        setBackground(NORMAL_COLOR);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int n) {
        number = n;
        setText(Integer.toString(number));
    }

    public void select() {
        setBackground(SELECTED_COLOR);
    }

    public void deSelect() {
        setBackground(NORMAL_COLOR);
    }
}
