
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLOutput;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class Main {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int tc = 0;
    static int n = 0;
    static String[] students;
    static boolean[] visit;
    static int min;
    static HashMap<String, Integer> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        String[] mbtiList = {"ISTJ", "ISFJ", "INFJ", "INTJ", "ISTP", "ISFP", "INFP", "INTP", "ESTP", "ESFP", "ENFP", "ENTP", "ESTJ", "ESFJ", "ENFJ", "ENTJ"};

        for (int i = 0; i < mbtiList.length; i++) {
            for (int j = 0; j < mbtiList.length; j++) {
                int value = 0;
                for (int k = 0; k < 4; k++) {
                    if (mbtiList[i].charAt(k) != mbtiList[j].charAt(k)) {
                        value++;
                    }
                }
                map.put(mbtiList[i] + mbtiList[j], value);
            }
        }

        tc = Integer.parseInt(br.readLine());
        while (tc-- > 0) {
            n = Integer.parseInt(br.readLine());
            students = br.readLine().split(" ");
            Map<String, Long> collect = Arrays.stream(students).collect(groupingBy(i -> i, counting()));
            Optional<Long> over3 = collect.values().stream().filter(i -> i >= 3).findAny();
            if (over3.isPresent()) {
                System.out.println(0);
                continue;
            }
            visit = new boolean[students.length];
            min = Integer.MAX_VALUE;
            dfs(0, 0, new ArrayList<>());
            System.out.println(min);
        }
    }

    static void dfs(int cur, int depth, ArrayList<String> list) {

        if (depth == 3) {

            String a = list.get(0);
            String b = list.get(1);
            String c = list.get(2);

            int sum = map.get(a + b) + map.get(b + c) + map.get(c + a);
            min = Math.min(min, sum);
            return;
        }

        for (int i = cur; i < students.length; i++) {

            if (!visit[i]) {
                visit[i] = true;
                list.add(students[i]);
                dfs(i, depth + 1, list);
                visit[i] = false;
                list.remove(students[i]);
            }
        }
    }
}

/**
 * INFP INFP ESTP ESTJ ISTJ
 */