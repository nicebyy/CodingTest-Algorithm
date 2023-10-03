import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {


    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[0]-o2[0]);

        for(int i=0;i<n;i++){ // { 0 : day , 1 : pay }
            pq.add(Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray());
        }

        PriorityQueue<Integer> rewards = new PriorityQueue<>();
        
        while (!pq.isEmpty()){

            int[] cur = pq.poll();
        
            rewards.add(cur[1]);
            
            if(rewards.size() > cur[0]){
                rewards.poll();
            }
        }
        
        rewards.stream()
                .reduce(Integer::sum)
                .ifPresentOrElse(
                result-> System.out.println(result),
                ()-> System.out.println(0)
        );
    }
}
