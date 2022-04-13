import static java.util.Arrays.*;
import static java.util.stream.Collectors.*;

import java.util.*;
import java.io.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {


    /**
     * 다익스트라를 진행하는데 ,
     * 만약에 다음 노드로 가는 것들 중 제일 싼거를 찾을텐데
     *  1. 다음걸 0 으로
     *  2. 제일 싼 걸로
     *  두 가지 경우를 push
     *
     */
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

        while (minEstimate<=maxEstimate){
            int midEstimate = (minEstimate+maxEstimate)/2;

            if(isArrive(midEstimate)) {
                ans = midEstimate;
                maxEstimate = midEstimate - 1;
            }
            else
                minEstimate = midEstimate + 1;
        }
        System.out.println(ans);
    }
    static boolean isArrive(int estimate){

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

                int nextCount=cur.weight;

                if(estimate < next.weight)
                    nextCount++;

                if(dist[next.node] > nextCount){
                    dist[next.node]=nextCount;
                    pq.add(new Node(next.node,nextCount));
                }
            }
        }

        return dist[n]<=k;
    }
    static class Node {
        int node,weight;

        public Node(int node, int weight) {
            this.node = node;
            this.weight = weight;
        }
    }
}

