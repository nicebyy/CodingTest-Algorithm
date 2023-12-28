
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.util.Arrays.stream;

public class Main {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int tc,n,seq;
    public static void main(String[] args) throws IOException {
        tc = Integer.parseInt(br.readLine());

        while (tc-->0){
            int[] input = stream(br.readLine().split(" "))
                                .mapToInt(Integer::parseInt)
                                .toArray();
            n = input[0];
            seq = input[1];

            int[] times = stream(("0 "+br.readLine()).split(" "))
                                .mapToInt(Integer::parseInt)
                                .toArray();

            Craft craft= new Craft(times);
            for(int i=0;i<seq;i++){
                input = stream(br.readLine().split(" "))
                                    .mapToInt(Integer::parseInt)
                                    .toArray();
                craft.graph.get(input[0]).add(input[1]);
                craft.degrees[input[1]]++;
            }
            craft.setGoal(Integer.parseInt(br.readLine()));

            System.out.println(craft.build());
        }
    }

    static class Craft{
        public int[] degrees;
        public int[] times;
        public int[] buildTime;
        public ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        public LinkedList<Integer> executableList = new LinkedList<>();
        public int goal = 0;

        public Craft(int[] times){
            this.times = times;
            this.buildTime = new int[times.length];
            this.degrees = new int[times.length];
            for(int i=0;i<times.length;i++){
                this.graph.add(new ArrayList<>());
            }
        }
        public void setGoal(int goal){
            this.goal = goal;
        }
        public int build(){

            for(int i=1;i<this.degrees.length;i++){
                if(this.degrees[i] == 0){
                    executableList.add(i);
                    buildTime[i] = times[i];
                }
            }

            while (!executableList.isEmpty()){

                Integer cur = executableList.poll();

                for (int next : graph.get(cur)) {

                    buildTime[next] = Math.max(buildTime[next],buildTime[cur]+times[next]);

                    if(--degrees[next] == 0){
                        executableList.add(next);
                    }
                }
            }
            return buildTime[this.goal];
        }
    }
}


