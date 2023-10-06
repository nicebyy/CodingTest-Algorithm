import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        Stack<Integer>[] stacks = new Stack[n];
        for(int i=0;i<n;i++){
            stacks[i] = new Stack<>();
        }

        int maxStackIndex = 0;
        int maxStackSize = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j]) {

                    if(dp[i] < dp[j]+1){
                        stacks[i].clear();
                        dp[i] = dp[j]+1;

                        for (Integer e : stacks[j]) {
                            stacks[i].add(e);
                        }
                    }
                }
            }
            stacks[i].add(arr[i]);
            if(maxStackSize < stacks[i].size()){
                maxStackSize = stacks[i].size();
                maxStackIndex = i;
            }
        }
//        System.out.println(stacks[maxStackIndex].toString());
        StringBuilder sb = new StringBuilder();
        sb.append(stacks[maxStackIndex].size()).append("\n");
        for (Integer e : stacks[maxStackIndex]) {
            sb.append(e).append(" ");
        }
        System.out.println(sb.toString());

    }
}
