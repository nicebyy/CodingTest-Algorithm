import static java.util.Arrays.*;
import static java.util.stream.Collectors.*;

import java.util.*;
import java.io.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    
    static int n,e,k;
    static int minEstimate=0,maxEstimate;
    static ArrayList<ArrayList<Node>> graph = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         int[] input = stream(br.readLine().split(" "))
                  .mapToInt(Integer::parseInt).toArray();
         n = input[0]; e = input[1]; k = input[2];

         for(int i=0;i<=n;i++)
             graph.add(new ArrayList<>());
         for(int i=0;i<e;i++){
             input = stream(br.readLine().split(" "))
                     .mapToInt(Integer::parseInt).toArray();
             graph.get(input[0]).add(new Node(input[1],input[2]));
             graph.get(input[1]).add(new Node(input[0],input[2]));
             maxEstimate = Math.max(maxEstimate,input[2]);
         }//end input

        int ans=-1; 

        while (minEstimate<=maxEstimate){ //이분 탐색
            int midEstimate = (minEstimate+maxEstimate)/2;

            if(isArrive(midEstimate)) { //도달 가능하면 ans 갱신
                ans = midEstimate; 
                maxEstimate = midEstimate - 1; // max 추정치 범위를 좁힘
            }
            else
                minEstimate = midEstimate + 1; // min 추정치 범위를 좁힘
        }
        System.out.println(ans); // 도달 불가능이면 -1 
    }
    static boolean isArrive(int estimate){ // 추정치로 탐색 가능한지 탐색

        int[] dist = new int[n+1];
        Arrays.fill(dist,Integer.MAX_VALUE);
        dist[1]=0;

        PriorityQueue<Node> pq = new PriorityQueue<Node>((o1, o2) -> o1.weight-o2.weight);
        pq.add(new Node(1,0));

        while (!pq.isEmpty()){
            Node cur = pq.poll();

            if(dist[cur.node] < cur.weight)
                continue;

            for (Node next : graph.get(cur.node)) {

                int nextCount=cur.weight; // 공짜회선 개수

                if(estimate < next.weight) // 더 비싼 회선가격이 나오면
                    nextCount++; // 공짜 회선으로 전환

                if(dist[next.node] > nextCount){ 
                    dist[next.node]=nextCount;
                    pq.add(new Node(next.node,nextCount));
                }
            }
        }

        return dist[n]<=k; // 공짜회선 k개 보다 적은 개수이면 탐색 가능
    }
    static class Node {
        int node,weight;

        public Node(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
    }
}

