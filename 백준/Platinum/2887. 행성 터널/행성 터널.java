
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

public class Main {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n;
    public static void main(String[] args) throws IOException {

        int n = Integer.parseInt(br.readLine());


        int[][] planets = new int[n][4];
        ArrayList<ArrayList<Edge>> edges = new ArrayList<>();

        for(int i=0;i<n;i++){
            int[] input = stream((br.readLine()+" "+i).split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            planets[i] = input;
            edges.add(new ArrayList<>());
        }


        for(int dimension=0;dimension<planets[0].length;dimension++){

            Arrays.sort(planets,comparatorByD(dimension));

            for(int i=0;i<planets.length-1;i++){

                Edge[] edge = Edge.getEdge(planets[i], planets[i + 1]);

                edges.get(edge[0].node).add(edge[1]);
                edges.get(edge[1].node).add(edge[0]);
            }
        }

        boolean[] visit = new boolean[n];

        long totalCost = 0;

        PriorityQueue<Edge> pq = new PriorityQueue<>((e1,e2)->e1.weight - e2.weight);

        pq.add(new Edge(0,0));

        while (!pq.isEmpty()){

            Edge cur = pq.poll();

            if(visit[cur.node]){
                continue;
            }

            totalCost += cur.weight;
            visit[cur.node] = true;

            for (Edge next : edges.get(cur.node)) {

                if(!visit[next.node]){
                    pq.add(next);
                }
            }
        }

        System.out.println(totalCost);
    }

    static Comparator<int[]> comparatorByD(int dimension){
        return Comparator.comparingInt(o -> o[dimension]);
    }
    static class Edge {

        int node;
        int weight;

        public Edge(int node , int weight) {
            this.node = node;
            this.weight = weight;
        }

        public static Edge[] getEdge(int[] o1,int[] o2){
            int xDiff = Math.abs(o1[0] - o2[0]);
            int yDiff = Math.abs(o1[1] - o2[1]);
            int zDiff = Math.abs(o1[2] - o2[2]);

            int weight = Math.min(Math.min(xDiff, yDiff), zDiff);

            return new Edge[]{new Edge(o1[3],weight),new Edge(o2[3],weight)};
        }
    }
}

