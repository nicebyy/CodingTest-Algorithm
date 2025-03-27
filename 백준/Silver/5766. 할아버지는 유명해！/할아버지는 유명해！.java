
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.util.Arrays.compare;
import static java.util.Arrays.stream;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder answers = new StringBuilder();

        while (true) {
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            if (n == 0 && m == 0) {
                System.out.println(answers);
                break;
            }
            scanner.nextLine(); // 개행 문자 처리

            Map<Integer, Integer> memberMap = new HashMap<>();

            for (int i = 0; i < n; i++) {
                String[] input = scanner.nextLine().split(" ");
                for (String numStr : input) {
                    int num = Integer.parseInt(numStr);
                    memberMap.put(num, memberMap.getOrDefault(num, 0) + 1);
                }
            }

            TreeMap<Integer, List<Integer>> rankMap = new TreeMap<>(Comparator.reverseOrder());

            for (Map.Entry<Integer, Integer> entry : memberMap.entrySet()) {
                int number = entry.getKey();
                int score = entry.getValue();

                rankMap.putIfAbsent(score, new ArrayList<>());
                rankMap.get(score).add(number);
            }

            int rank = 0;
            for (Map.Entry<Integer, List<Integer>> entry : rankMap.entrySet()) {
                rank++;
                if (rank == 2) {
                    List<Integer> list = entry.getValue();
                    Collections.sort(list);
                    for (int num : list) {
                        answers.append(num).append(" ");
                    }
                    answers.append("\n");
                    break;
                }
            }
        }
        scanner.close();
    }
}
