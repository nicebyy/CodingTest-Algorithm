import java.util.*;
import java.io.*;

public class Main{

    static int N,K;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]); K = Integer.parseInt(input[1]);
        int[][] dp = new int[N+1][K+1];
        int[][] arr = new int[N+1][2];
        for(int i=1;i<=N;i++) {
             arr[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();
        }
        for(int i=1;i<=N;i++){
            for(int j=1;j<=K;j++){

                if(arr[i][0] > j) {
                    dp[i][j] = dp[i - 1][j];
                }
                else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - arr[i][0]] + arr[i][1]);
                }

            }
        }

        System.out.println(dp[N][K]);

    }
}
