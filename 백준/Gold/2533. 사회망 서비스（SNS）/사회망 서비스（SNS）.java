
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;

import static java.lang.System.*;
import static java.util.Arrays.stream;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(in));
    static int n;
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        IntStream.range(0, n+1).forEach(i -> graph.add(new ArrayList<>()));

        for(int i=0; i<n-1;i++){
            int[] input = stream(br.readLine().split(" "))
                                .mapToInt(Integer::parseInt)
                                .toArray();
            graph.get(input[0]).add(input[1]);
            graph.get(input[1]).add(input[0]);
        }
        dp = new int[n+1][2];
        for(int i=1;i<=n;i++){
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }

        dfs(1,0);
        out.println(Math.min(dp[1][0],dp[1][1]));
    }

    /**
     * dfs : leaf ~ root 순으로 올라옴 -> 최종 root
     */
    public static void dfs(int cur, int prev){

        dp[cur][0] = 0; // cur 이 얼리 어답터 아님
        dp[cur][1] = 1; // cur 이 얼리 어답터 시작.

        for (Integer next : graph.get(cur)) {

            if(next == prev){ // 위로 다시 올라오는거 방지
                continue;
            }

            dfs(next, cur);
            dp[cur][0] += dp[next][1]; // 지금 노드가 얼리 어답터가 아님 => next는 무조건 얼리어답터인.
            dp[cur][1] += Math.min( // 지금 노드가 얼리 어답터임 => next 는 얼리어답터 일 수도있고 아닐수도 있는.
                    dp[next][0],
                    dp[next][1]
            );
        }
    }
}

/**
 * 8
 * 1 2
 * 1 3
 * 1 4
 * 2 5
 * 2 6
 * 4 7
 * 4 8
 */