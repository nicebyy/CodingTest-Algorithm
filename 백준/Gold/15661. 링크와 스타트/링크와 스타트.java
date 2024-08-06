
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;

import static java.util.Arrays.fill;
import static java.util.Arrays.stream;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int n;
    static int minDiff = Integer.MAX_VALUE;
    static int[][] map;
    static boolean[] visit;

    public static void main(String[] args) throws IOException {

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            map[i] = stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }
        visit = new boolean[n];

        find(0);
        System.out.println(minDiff);
    }

    public static void find(int depth) {

        if (depth == n) {

            int start = 0;
            int link = 0;

            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    
                    if (visit[i] != visit[j]) {
                        continue;
                    }

                    int synergy = map[i][j] + map[j][i];
                    if (visit[i]) {
                        start+= synergy;
                    }else{
                        link+= synergy;
                    }
                }
            }

            int diff = Math.abs(start - link);
            minDiff = Math.min(diff,minDiff);
            return;
        }

        visit[depth] = true;
        find(depth+1);
        visit[depth] = false;
        find(depth+1);
    }
}

