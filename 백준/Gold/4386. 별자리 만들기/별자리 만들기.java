import java.io.*;
import java.util.*;

import static java.util.Arrays.stream;

public class Main {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n;
    static int[] parent;
    static Map<Integer,Node> map = new HashMap<Integer,Node>();
    static PriorityQueue<Edge> priorityQueue = new PriorityQueue<Edge>();

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        parent = new int[n];
        for(int i=0;i<n;i++){
            String[] input = br.readLine().split(" ");
            double x = Double.parseDouble(input[0]);
            double y = Double.parseDouble(input[1]);
            map.put(i,new Node(x,y,i));
        }

        for (Integer key1 : map.keySet()) {
            for (Integer key2 : map.keySet()) {

                if(key1 == key2){
                    continue;
                }

                Node start = map.get(key1);
                Node end = map.get(key2);

                priorityQueue.add(new Edge(start,end));
            }
        }

        for(int i=0;i<n;i++){
            parent[i] = i;
        }

        double totalCost = 0.00;


        while (!priorityQueue.isEmpty()){

            Edge cur = priorityQueue.poll();

            int startRoot = find(cur.start.index);
            int endRoot = find(cur.end.index);

            if(startRoot == endRoot){
                continue;
            }

            union(startRoot,endRoot);
            totalCost += cur.cost;
        }

        System.out.printf("%.2f",totalCost);

    }
    static void union(int start,int end){
        int startRoot = find(start);
        int endRoot = find(end);

        if(startRoot != endRoot){
            parent[startRoot] =end;
        }
    }
    static int find(int x){
        if(x == parent[x]){
            return x;
        }
        return parent[x]=find(parent[x]);
    }

    static class Edge implements Comparable<Edge>{

        Node start;
        Node end;
        double cost;

        public Edge(Node start, Node end) {
            this.start = start;
            this.end = end;
            this.cost = Math.sqrt( Math.pow(start.x-end.x,2)+Math.pow(start.y-end.y,2));
        }

        @Override
        public int compareTo(Edge o) {
            return Double.compare(this.cost,o.cost);
        }
    }

    static class Node{

        int index;
        double x;
        double y;

        public Node(double x, double y,int index) {
            this.x = x;
            this.y = y;
            this.index = index;
        }
    }
}
