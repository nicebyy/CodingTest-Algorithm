
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.util.Arrays.stream;

public class Main {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int n,e,k,s;
    static List<ArrayList<Integer>> graph = new ArrayList<>();


    public static void main(String[] args) throws IOException {

        int[] input = stream(br.readLine().split(" "))
                            .mapToInt(Integer::parseInt)
                            .toArray();
        n = input[0];
        e = input[1];
        k = input[2];
        s = input[3];

        for(int i = 0; i <= n; i++) {
            graph.add(new ArrayList<Integer>());
        }

        for(int i = 0; i < e; i++) {
            input = stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            graph.get(input[0]).add(input[1]);
        }

        int[] distance = new int[n+1];
        Arrays.fill(distance,Integer.MAX_VALUE);

        distance[s] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1]-o2[1]);
        pq.add(new int[]{s,0});

        while (!pq.isEmpty()){

            int[] cur = pq.poll();

            for (int next : graph.get(cur[0])) {

                if(distance[next] > distance[cur[0]]+1){
                    distance[next] = distance[cur[0]]+1;
                    pq.add(new int[]{next,distance[next]});
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i=1;i<=n;i++){
            if(distance[i] == k){
                sb.append(i).append("\n");
            }
        }

        String result = sb.isEmpty() ? "-1" : sb.toString();
        System.out.println(result);
    }
}


