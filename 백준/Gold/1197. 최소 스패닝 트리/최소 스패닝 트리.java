import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {


    static int n;
    static int e;
    static int[] parents;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] in = br.readLine().split(" ");
        n = Integer.parseInt(in[0]);
        e = Integer.parseInt(in[1]);
        parents = new int[n+1];
        for(int i=1;i<=n;i++)
            parents[i] = i;

        PriorityQueue<Edge> queue = new PriorityQueue<>((o1, o2) -> o1.weight - o2.weight);

        for(int i=0;i<e;i++){
            queue.add(new Edge(br.readLine()));
        }

        int sum = 0;
        while (!queue.isEmpty()){

            Edge cur = queue.poll();

            if(find(cur.start) == find(cur.end)){
                continue;
            }

            union(cur.start,cur.end);
            sum += cur.weight;
        }

        System.out.println(sum);
    }
    static int find(int x){
        if(x == parents[x])
            return x;
        parents[x] = find(parents[x]);
        return parents[x];
    }

    static void union(int x,int y){

        int xRoot = find(x);
        int yRoot = find(y);

        if(xRoot != yRoot)
            parents[yRoot] = xRoot;
    }
    static class Edge{

        int start;
        int end;
        int weight;

        public Edge(String in) {
            String[] split = in.split(" ");
            this.start = Integer.parseInt(split[0]);
            this.end = Integer.parseInt(split[1]);
            this.weight = Integer.parseInt(split[2]);
        }
    }
}
