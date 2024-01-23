
import java.io.*;
import java.util.*;

import static java.util.Arrays.stream;

public class Main {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n;
    static ArrayDeque<int[]> stack = new ArrayDeque<int[]>();
    static int totalScore = 0;
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());

        for(int i=1;i<=n;i++){
            int[] input = stream(br.readLine().split(" "))
                                .mapToInt(Integer::parseInt)
                                .toArray();
            int score;
            int duration;
            if(input[0] == 0){
                if(stack.isEmpty()){
                    continue;
                }
                int[] poll = stack.pollFirst();
                score = poll[0];
                duration = poll[1];
            }else{
                score = input[1];
                duration = input[2];
            }

            if(--duration == 0){
                totalScore+= score;
            }else{
                stack.addFirst(new int[]{score,duration});
            }
        }
        System.out.println(totalScore);
    }
}
