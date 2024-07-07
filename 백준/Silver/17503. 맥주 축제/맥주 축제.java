
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n, m, k;

    public static void main(String[] args) throws IOException {
        int[] input = stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        n = input[0];
        m = input[1];
        k = input[2];

        PriorityQueue<Bear> bears = new PriorityQueue<>((o1, o2) -> o1.level == o2.level ? o2.score - o1.score : o1.level - o2.level);

        for (int i = 0; i < k; i++) {
            input = stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            bears.add(new Bear(input[0], input[1]));
        }

        PriorityQueue<Bear> pq = new PriorityQueue<>((o1, o2) -> o1.score - o2.score);
        
        int scoreSum = 0;
        while (!bears.isEmpty()) {

            Bear curBear = bears.poll();
            scoreSum += curBear.score;
            pq.add(curBear);

            if (pq.size() > n) {
                scoreSum -= pq.poll().score;
            }

            if (scoreSum >= m && pq.size() == n) {
                System.out.println(curBear.level);
                return;
            }
        }
        System.out.println(-1);
    }

    static class Bear {
        int score;
        int level;

        public Bear(int score, int level) {
            this.score = score;
            this.level = level;
        }

        @Override
        public String toString() {
            return "Bear{" +
                    "score=" + score +
                    ", level=" + level +
                    '}';
        }
    }
}




