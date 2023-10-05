import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] input = Arrays.stream(br.readLine().split(" "))
                 .mapToInt(Integer::parseInt).toArray();

        int[][] dp = new int[input[0]+1][input[0]+1];

        for(int i=1;i<=input[0];i++){
            dp[i][i] = 1;
            dp[i][0] = 1;
            dp[i][1] = i;
        }

        for(int i=2;i<=input[0];i++){
            for(int j=2;j<=i;j++){
                dp[i][j] = dp[i-1][j-1] + dp[i-1][j];
            }
        }

        System.out.println(dp[input[0]][input[1]]);
    }
}

/**
 *
 * 5C3 => 5C2 에서 하나를 더 뽑은 경우
 * +
 * 4C2 에서 하나를 더 뽑은 경우
 *
 */

