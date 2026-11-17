package day1103;

import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.Scanner;

public class ShoppingCost {
    public static void main(String[] args) {
        HashMap<String, Integer> prices = new HashMap<>();
        prices.put("고추장", 3000);
        prices.put("만두", 500);
        prices.put("새우깡", 1500);
        prices.put("콜라", 600);
        prices.put("참치캔", 2000);
        prices.put("치약", 1000);
        prices.put("연어", 2500);
        prices.put("삼겹살", 2500);
        String[] items = { "고추장", "만두", "새우깡", "콜라", "참치캔", "치약", "연어", "삼겹살" };
        System.out.println("쇼핑 비용을 계산해드립니다. 구입 가능 물건과 가격은 다음과 같습니다.");
        for (String item : items) {
            System.out.print("[" + item + "," + prices.get(item) + "] ");
        }
        System.out.println();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("물건과 개수를 입력하세요>>");
            String input = scanner.nextLine().trim();
            if (input.equals("그만")) {
                break;
            }
            StringTokenizer st = new StringTokenizer(input);
            if (st.countTokens() == 0 || st.countTokens() % 2 != 0) {
                System.out.println("입력에 문제가 있습니다!");
                continue;
            }
            int total = 0;
            boolean error = false;
            while (st.hasMoreTokens()) {
                String item = st.nextToken();
                String countText = st.nextToken();
                if (!prices.containsKey(item)) {
                    System.out.println(item + "은 없는 상품입니다!");
                    error = true;
                    break;
                }
                try {
                    int count = Integer.parseInt(countText);
                    total += prices.get(item) * count;
                } catch (NumberFormatException e) {
                    System.out.println("입력에 문제가 있습니다!");
                    error = true;
                    break;
                }
            }
            if (!error) {
                System.out.println("전체 비용은 " + total + "원입니다.");
            }
        }
        scanner.close();
    }
}