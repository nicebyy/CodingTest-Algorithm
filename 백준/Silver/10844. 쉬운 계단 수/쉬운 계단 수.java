import java.io.*;
import java.util.*;
import static java.util.Arrays.stream;

public class Main {

    static int limit = 1000000000;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long[][] dp = new long[n+1][10];

        Arrays.fill(dp[1],1);
        dp[1][0]=0;

        for(int i=2; i<=n; i++){
            for(int j=0; j<10; j++){

                long next = switch (j){ // 숫자 + j
                    case 0->dp[i-1][1]; // 0 일경우 다음은 1만 와야됨
                    case 9->dp[i-1][8]; // 9 일경우 다음은 8만 와야됨
                    default -> dp[i-1][j-1]+dp[i-1][j+1] % limit;
                };
                dp[i][j] = next;
            }
        }
        long result = stream(dp[n])
                .reduce((sum, e) -> (sum + e) % limit)
                .getAsLong();
        System.out.println(result);

    }
}

