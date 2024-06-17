
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

import static java.util.Arrays.stream;

public class Main {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n;
    static int[] score;
    static int[][] dp;
    static ArrayList<Integer>[] list;

    public static void main(String[] args) throws IOException {

        n = Integer.parseInt(br.readLine());
        list = new ArrayList[n + 1];
        score = new int[n + 1];
        dp = new int[n + 1][2];

        for (int i = 1; i <= n; i++) {
            list[i] = new ArrayList<>();
        }

        int[] link = stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        for (int i = 0, j = 2; i < link.length; i++, j++) {
            list[link[i]].add(j);
        }

        score = stream(("0 " + br.readLine()).split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        for (int i = 1; i <= n; i++) {
            Arrays.fill(dp[i], -1);
        }

        System.out.println(dfs(1,0));
    }

    public static int dfs(int cur, int isBind) {

        
        if (dp[cur][isBind] != -1) {
            return dp[cur][isBind];
        }

        dp[cur][isBind] = 0;


        for (Integer next : list[cur]) {
            dp[cur][isBind] += dfs(next, 0);
        }
        
        if (isBind == 0) {
            int sum = dp[cur][isBind];
            for (Integer next : list[cur]) {
                dp[cur][isBind] = Math.max(dp[cur][isBind],
                        sum-dfs(next,0)+dfs(next,1)+score[cur]*score[next]
                );
            }
        }

        return dp[cur][isBind];
    }
}
