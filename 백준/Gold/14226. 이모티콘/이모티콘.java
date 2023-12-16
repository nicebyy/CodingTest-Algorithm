
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

import static java.util.Arrays.stream;

public class Main {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int n;
    static int[][] dp;
    static int minTime = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {

        n = Integer.parseInt(br.readLine());

        dp = new int[n*10][n*10];
        for(int i=1;i<dp.length;i++)
            Arrays.fill(dp[i],Integer.MAX_VALUE);

        dp[1][0] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[2] - o2[2]);
        pq.add(new int[]{1,0,0});
        while (!pq.isEmpty()){ // dp[print][clip]

            int[] cur = pq.poll();
            int printedEmoticon = cur[0];
            int clipboardEmoticon = cur[1];
            int time = cur[2];
//
//            System.out.print("printedEmoticon = " + printedEmoticon);
//            System.out.println("     time = " + time);
            if(printedEmoticon == n){
                minTime = Math.min(minTime,time);
                break;
            }

            if(dp[printedEmoticon][printedEmoticon] > time+1 ){
                pq.add(new int[]{printedEmoticon,printedEmoticon,time+1});
                dp[printedEmoticon][printedEmoticon] = time+1;
            }

            if(isRange(printedEmoticon + clipboardEmoticon) && dp[printedEmoticon + clipboardEmoticon][clipboardEmoticon] > time+1){
                pq.add(new int[]{printedEmoticon + clipboardEmoticon, clipboardEmoticon, time + 1});
                dp[printedEmoticon + clipboardEmoticon][clipboardEmoticon] = time+1;
            }

            if (isRange(printedEmoticon - 1) && dp[printedEmoticon-1][clipboardEmoticon] > time+1) {
                pq.add(new int[]{printedEmoticon - 1, clipboardEmoticon, time + 1});
                dp[printedEmoticon-1][clipboardEmoticon] = time+1;
            }
        }

        System.out.println(minTime);
    }

    static boolean isRange(int value){
        return value >0 && value < dp[0].length;
    }
}


