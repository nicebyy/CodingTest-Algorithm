
import java.util.*;
import java.util.Collections.*;
import java.util.stream.Collectors;

class Solution {

    int[][] dp;
    public int solution(int[][] triangle) {
        int answer = 0;
        dp = new int[triangle.length][triangle.length];
        dp[0][0] = triangle[0][0];

        for(int i=1;i<triangle.length;i++){

            for(int j=0;j<=i;j++){

                if(j==0){
                    dp[i][j] = triangle[i][j]+dp[i-1][j];
                }else if(j==i){
                    dp[i][j] = triangle[i][j]+dp[i-1][j-1];
                }else{
                    dp[i][j] += Math.max(dp[i-1][j-1],dp[i-1][j])+triangle[i][j];
                }
            }
        }
        return Arrays.stream(dp[triangle.length-1]).max().getAsInt();
    }
}