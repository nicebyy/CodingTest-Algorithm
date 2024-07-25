
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

public class Main {

    static char[] arr1, arr2;
    static Integer[][] dp;
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {

        arr1 = br.readLine().toCharArray();
        arr2 = br.readLine().toCharArray();

        dp = new Integer[arr1.length][arr2.length];

        int result = go(arr1.length - 1, arr2.length - 1);
        System.out.println(result);
    }

    static int go(int i, int j) {

        if (i < 0 || j < 0) {
            return 0;
        }

        if(dp[i][j] == null){
            dp[i][j] = 0;
            if (arr1[i] == arr2[j]) {
                dp[i][j] = go(i - 1, j - 1) + 1;
            } else {
                dp[i][j] = Math.max(go(i - 1, j), go(i, j - 1));
            }
        }
        return dp[i][j];
    }
}


