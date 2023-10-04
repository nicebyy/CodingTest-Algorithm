import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[][] dp;
    static int[] arr;
    static int w, n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();

        n = input[0];
        w = input[1];
        dp = new int[n + 1][w + 1];
        arr = new int[n + 1];

        int curPos = 1;

        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 1; i <= n; i++) {

            //안 움직이는 경우 ~ 움직이는 경우
            for (int j = 0; j <= w; j++) {


                if (j == 0) {

                    if (arr[i] == 1) {
                        dp[i][j] = dp[i - 1][j] + 1;
                    } else {
                        dp[i][j] = dp[i - 1][j];
                    }
                    continue;
                }

                if (j % 2 == 0) {

                    if (arr[i] == 1) {
                        dp[i][j] = Math.max(dp[i - 1][j - 1], dp[i - 1][j] + 1);
                    } else {
                        dp[i][j] = Math.max(dp[i - 1][j - 1] + 1, dp[i - 1][j]);
                    }
                } else{
                    
                    if (arr[i] == 1) {
                        dp[i][j] = Math.max(dp[i - 1][j - 1] + 1, dp[i - 1][j]);
                    } else {
                        dp[i][j] = Math.max(dp[i - 1][j - 1], dp[i - 1][j] + 1);

                    }
                }


            }
        }
        int result = Arrays.stream(dp[n]).max().orElse(0);
        System.out.println(result);
    }
}
