
import java.io.*;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

import static java.util.Arrays.stream;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int[] input = stream(br.readLine().split(" "))
                            .mapToInt(Integer::parseInt)
                            .toArray();
        Town town = new Town(input[0],input[1]);

        for(int i=0;i<town.getTotalPath();i++){
            input = stream(br.readLine().split(" "))
                                .mapToInt(Integer::parseInt)
                                .toArray();
            town.addPath(input);
        }
        int ans = town.spanningTree();
        System.out.println(ans-town.getMaxPathWeight());
    }

    public static class Town{
        private int houseCount;
        private int totalPath;
        private int maxPathWeight = 0;
        private int totalWeight=0;
        private int[] parents;
        private ArrayList<ArrayList<int[]>> map = new ArrayList<>();
        private PriorityQueue<int[]> pathQueue = new PriorityQueue<>((o1, o2) -> o1[2]-o2[2]);

        public Town(int n,int m){
            this.houseCount = n;
            this.totalPath = m;
            this.parents = new int[houseCount+1];

            for(int i=0;i<=n;i++){
                map.add(new ArrayList<>());
                parents[i] = i;
            }
        }

        public void addPath(int[] path){
            this.pathQueue.add(path);
        }

        public int getTotalPath(){
            return this.totalPath;
        }
        
        public int getMaxPathWeight(){
            return this.maxPathWeight;
        }

        public int spanningTree(){

            while (!pathQueue.isEmpty()){

                int[] curPath = pathQueue.poll();
                int from = curPath[0];
                int to = curPath[1];
                int weight = curPath[2];

                int fromRoot = find(from);
                int toRoot = find(to);

                if(fromRoot == toRoot){
                    continue;
                }

                union(fromRoot,toRoot);
                this.maxPathWeight = Math.max(maxPathWeight,weight);
                this.totalWeight += weight;
            }

            return this.totalWeight;
        }

        public void union(int fromRoot,int toRoot){
            parents[fromRoot] = toRoot;
        }
        public int find(int e){

            if(e == this.parents[e]){
                return e;
            }
            return this.parents[e] = find(parents[e]);
        }
    }
}
