package day1124;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.io.*;

public class LoginProgram extends JFrame {

    // 회원 정보 저장
    static HashMap<String, String> users = new HashMap<>();

    JTextField idField;
    JPasswordField pwField;

    // 관리자 아이디와 비밀번호
    private final String ADMIN_ID = "admin";
    private final String ADMIN_PW = "1234";

    public LoginProgram() {

        setTitle("로그인 프로그램");
        setSize(350, 240);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 기본 테스트 계정
        users.put("admin", "1234");

        // 파일에서 회원 정보 불러오기
        loadUsers();

        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(
                BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(new JLabel("아이디"));
        idField = new JTextField();
        panel.add(idField);

        panel.add(new JLabel("비밀번호"));
        pwField = new JPasswordField();
        panel.add(pwField);

        JButton loginBtn = new JButton("로그인");
        JButton joinBtn = new JButton("회원가입");
        JButton adminBtn = new JButton("관리자");

        panel.add(loginBtn);
        panel.add(joinBtn);

        panel.add(adminBtn);

        add(panel);

        // 로그인 버튼
        loginBtn.addActionListener(e -> login());

        // 회원가입 버튼
        joinBtn.addActionListener(e -> {
            new JoinDialog(this);
        });

        // 관리자 버튼
        adminBtn.addActionListener(e -> adminLogin());

        setVisible(true);
    }

    // login.txt 파일에서 회원 정보 불러오기
    private void loadUsers() {

        FileReader fr = null;
        users.clear();

        try {
            fr = new FileReader(new File("C:/Users/ella0/Desktop/login.txt"));
            BufferedReader br = new BufferedReader(fr);

            String line;

            while ((line = br.readLine()) != null) {

                String[] data = line.split(":", 2);

                if (data.length == 2) {
                    users.put(data[0].trim(), data[1].trim());
                }
            }

            br.close();

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "file load error");
        }
    }

    // 회원 정보 파일에 저장
    void saveUsers(String id, String pw) {

        FileWriter fr = null;

        try {
            fr = new FileWriter(
                    "C:/Users/ella0/Desktop/login.txt", true);

            BufferedWriter bw = new BufferedWriter(fr);

            bw.write(id + " : " + pw);
            bw.newLine();

            bw.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(
                    this, "파일 저장 오류: " + e.getMessage());
        }
    }

    // 로그인 처리
    void login() {

        String id = idField.getText();
        String pw = new String(pwField.getPassword());

        if (users.containsKey(id) &&
                users.get(id).equals(pw)) {

            JOptionPane.showMessageDialog(
                    this, "로그인 성공! " + id + "님 환영합니다.");

        } else {
            JOptionPane.showMessageDialog(
                    this, "아이디 또는 비밀번호가 틀렸습니다.");
        }
    }

    // 관리자 로그인
    void adminLogin() {

        JTextField adminIdField = new JTextField();
        JPasswordField adminPwField = new JPasswordField();

        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10));

        panel.add(new JLabel("관리자 아이디"));
        panel.add(adminIdField);

        panel.add(new JLabel("관리자 비밀번호"));
        panel.add(adminPwField);

        int result = JOptionPane.showConfirmDialog(
                this,
                panel,
                "관리자 로그인",
                JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {

            String id = adminIdField.getText();
            String pw = new String(adminPwField.getPassword());

            if (ADMIN_ID.equals(id) && ADMIN_PW.equals(pw)) {

                new AdminDialog(this);

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "관리자 ID 또는 비밀번호가 일치하지 않습니다.",
                        "로그인 오류",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        new LoginProgram();
    }
}

// 회원가입 팝업 클래스
class JoinDialog extends JDialog {

    JTextField idField;
    JPasswordField pwField;
    JPasswordField pwCheckField;

    public JoinDialog(JFrame parent) {

        super(parent, "회원가입", true);

        setSize(350, 220);
        setLocationRelativeTo(parent);

        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));

        panel.setBorder(
                BorderFactory.createEmptyBorder(20, 20, 20, 20));

        panel.add(new JLabel("아이디"));
        idField = new JTextField();
        panel.add(idField);

        panel.add(new JLabel("비밀번호"));
        pwField = new JPasswordField();
        panel.add(pwField);

        panel.add(new JLabel("비밀번호 확인"));
        pwCheckField = new JPasswordField();
        panel.add(pwCheckField);

        JButton joinBtn = new JButton("가입하기");
        JButton cancelBtn = new JButton("취소");

        panel.add(joinBtn);
        panel.add(cancelBtn);

        add(panel);

        // 가입하기 버튼
        joinBtn.addActionListener(e -> join());

        // 취소 버튼
        cancelBtn.addActionListener(e -> dispose());

        setVisible(true);
    }

    // 회원가입 처리
    void join() {

        String id = idField.getText().trim();
        String pw = new String(pwField.getPassword());
        String pwCheck = new String(pwCheckField.getPassword());

        if (id.isEmpty() || pw.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this, "아이디와 비밀번호를 입력하세요.");
            return;
        }

        if (LoginProgram.users.containsKey(id)) {
            JOptionPane.showMessageDialog(
                    this, "이미 존재하는 아이디입니다.");
            return;
        }

        if (!pw.equals(pwCheck)) {
            JOptionPane.showMessageDialog(
                    this, "비밀번호가 일치하지 않습니다.");
            return;
        }

        // HashMap에 회원 정보 저장
        LoginProgram.users.put(id, pw);

        // 회원가입 후 파일에 저장
        ((LoginProgram) getOwner()).saveUsers(id, pw);

        JOptionPane.showMessageDialog(
                this, "회원가입이 완료되었습니다!");

        dispose();
    }
}

// 관리자 회원 정보 관리 창
class AdminDialog extends JDialog {

    public AdminDialog(JFrame parent) {

        super(parent, "회원 정보 관리", true);

        setSize(400, 300);
        setLocationRelativeTo(parent);

        JPanel panel = new JPanel(new GridLayout(
                LoginProgram.users.size(), 2, 10, 10));

        panel.setBorder(
                BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // HashMap users에서 회원 정보 가져오기
        for (String id : LoginProgram.users.keySet()) {

            String pw = LoginProgram.users.get(id);

            JTextField idText = new JTextField(id);
            JTextField pwText = new JTextField(pw);

            idText.setEditable(false);
            pwText.setEditable(false);

            panel.add(idText);
            panel.add(pwText);
        }

        JButton closeBtn = new JButton("닫기");

        closeBtn.addActionListener(e -> dispose());

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(closeBtn);

        add(panel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }
}
