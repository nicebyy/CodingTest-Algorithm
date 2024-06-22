
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.stream;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {

        int n = Integer.parseInt(br.readLine());
        int[] arr = stream(("0 "+br.readLine()).split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int[] dp = new int[n+1];
        arr[0] = arr[1];
        for(int i=1;i<=n;i++){

            if(arr[i] < arr[i-1]){
                dp[i-1]++;
            }
            dp[i] = dp[i-1];
        }
        int queryCount = Integer.parseInt(br.readLine());
        StringBuilder result = new StringBuilder();

        for(int i=0;i<queryCount;i++){

            int[] query = stream(br.readLine().split(" "))
                    .mapToInt(e -> Integer.parseInt(e)-1)
                    .toArray();

            result.append(dp[query[1]] - dp[query[0]]+"\n");
        }
        System.out.println(result);
    }
}

