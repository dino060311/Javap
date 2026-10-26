package day1013;

import java.awt.*;
import java.math.*;
import java.util.regex.*;
import javax.swing.*;
import javax.swing.undo.*;

public class Calculator extends JFrame {

    private JTextField expressionField = new JTextField();
    private JTextField resultField = new JTextField();

    // UN: 입력 취소
    private UndoManager undoManager = new UndoManager();

    public Calculator() {
        setTitle("계산기");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        // 상단: 수식 입력
        JPanel topPanel = new JPanel();
        topPanel.setBackground(Color.LIGHT_GRAY);
        topPanel.setLayout(new BorderLayout(10, 0));
        topPanel.setBorder(
                BorderFactory.createEmptyBorder(8, 40, 8, 25)
        );

        topPanel.add(new JLabel("수식"), BorderLayout.WEST);
        topPanel.add(expressionField, BorderLayout.CENTER);

        expressionField.getDocument()
                .addUndoableEditListener(undoManager);

        // Enter 키로도 계산
        expressionField.addActionListener(e -> calculate());

        // 가운데: 버튼
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(5, 4, 5, 5));
        centerPanel.setBorder(
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        );

        String[] buttonNames = {
                "C", "UN", "BK", "/",
                "7", "8", "9", "x",
                "4", "5", "6", "-",
                "1", "2", "3", "+",
                "0", ".", "=", "%"
        };

        for (String name : buttonNames) {
            JButton button = new JButton(name);

            if (name.equals("=")) {
                button.setBackground(new Color(120, 200, 200));
            }

            button.addActionListener(e -> buttonClicked(name));
            centerPanel.add(button);
        }

        // 하단: 계산 결과
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.YELLOW);
        bottomPanel.setLayout(new BorderLayout(10, 0));
        bottomPanel.setBorder(
                BorderFactory.createEmptyBorder(8, 10, 8, 25)
        );

        resultField.setEditable(false);
        resultField.setBackground(Color.WHITE);

        bottomPanel.add(new JLabel("계산 결과"), BorderLayout.WEST);
        bottomPanel.add(resultField, BorderLayout.CENTER);

        contentPane.add(topPanel, BorderLayout.NORTH);
        contentPane.add(centerPanel, BorderLayout.CENTER);
        contentPane.add(bottomPanel, BorderLayout.SOUTH);

        setSize(430, 480);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // 버튼을 눌렀을 때 실행
    private void buttonClicked(String name) {
        switch (name) {
            case "C":
                expressionField.setText("");
                resultField.setText("");
                undoManager.discardAllEdits();
                break;

            case "UN":
                if (undoManager.canUndo()) {
                    undoManager.undo();
                }
                break;

            case "BK":
                deleteCharacter();
                break;

            case "=":
                calculate();
                break;

            default:
                // 커서 위치에 숫자 또는 연산자 입력
                expressionField.replaceSelection(name);
                break;
        }

        expressionField.requestFocusInWindow();
    }

    // 선택한 부분 또는 커서 앞의 한 글자 삭제
    private void deleteCharacter() {
        int start = expressionField.getSelectionStart();
        int end = expressionField.getSelectionEnd();

        if (start != end) {
            expressionField.replaceSelection("");
        } else if (start > 0) {
            expressionField.select(start - 1, start);
            expressionField.replaceSelection("");
        }
    }

    // 수식 계산
    private void calculate() {
        String expression = expressionField.getText()
                .replaceAll("\\s+", "");

        if (expression.isEmpty()) {
            resultField.setText("수식을 입력하세요.");
            return;
        }

        // 정수, 소수, 음수
        String number = "[+-]?(?:\\d+(?:\\.\\d*)?|\\.\\d+)";

        // 숫자 + 연산자 + 숫자 형식 검사
        Pattern pattern = Pattern.compile(
                "^(" + number + ")([+\\-x*/%])(" + number + ")$"
        );

        Matcher matcher = pattern.matcher(expression);

        if (!matcher.matches()) {
            int operatorCount = 0;

            for (int i = 0; i < expression.length(); i++) {
                char ch = expression.charAt(i);

                if ("+-x*/%".indexOf(ch) != -1) {
                    operatorCount++;
                }
            }

            if (operatorCount > 1) {
                resultField.setText("한 개의 연산만 할 수 있음");
            } else {
                resultField.setText("올바른 수식을 입력하세요.");
            }

            return;
        }

        try {
            BigDecimal num1 = new BigDecimal(matcher.group(1));
            String operator = matcher.group(2);
            BigDecimal num2 = new BigDecimal(matcher.group(3));

            if ((operator.equals("/") || operator.equals("%"))
                    && num2.compareTo(BigDecimal.ZERO) == 0) {
                resultField.setText("0으로 나눌 수 없음");
                return;
            }

            BigDecimal result;

            switch (operator) {
                case "+":
                    result = num1.add(num2);
                    break;

                case "-":
                    result = num1.subtract(num2);
                    break;

                case "x":
                case "*":
                    result = num1.multiply(num2);
                    break;

                case "/":
                    result = num1.divide(
                            num2, MathContext.DECIMAL64
                    );
                    break;

                case "%":
                    result = num1.remainder(num2);
                    break;

                default:
                    resultField.setText("잘못된 연산자입니다.");
                    return;
            }

            resultField.setText(
                    result.stripTrailingZeros().toPlainString()
            );

        } catch (NumberFormatException | ArithmeticException e) {
            resultField.setText("계산할 수 없는 수식입니다.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Calculator());
    }
}