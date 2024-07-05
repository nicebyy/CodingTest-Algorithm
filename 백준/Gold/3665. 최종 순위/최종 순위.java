
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.util.Arrays.stream;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int tc = 0;
    public static void main(String[] args) throws IOException {

        tc = Integer.parseInt(br.readLine());
        while (tc --> 0){

            int n = 0;
            ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
            n = Integer.parseInt(br.readLine());
            for(int i=0;i<=n;i++) {
                graph.add(new ArrayList<>());
            }
            int[] rank = stream(br.readLine().split(" "))
                                .mapToInt(Integer::parseInt)
                                .toArray();
            for(int i=0;i<rank.length;i++){
                for(int j=rank.length-1;j>i;j--){
                    graph.get(rank[i]).add(rank[j]);
                }
            }

            int mutation = Integer.parseInt(br.readLine());
            for(int i=0;i<mutation;i++){
                int[] input = stream(br.readLine().split(" "))
                        .mapToInt(Integer::parseInt)
                        .toArray();
                int from = input[0];
                int to = input[1];

                if(graph.get(to).contains(from)){
                    graph.get(to).remove((Integer)from);
                    graph.get(from).add(to);
                }else if(graph.get(from).contains(to)){
                    graph.get(from).remove((Integer)to);
                    graph.get(to).add(from);
                }
            }

            int[] inDegree = new int[n+1];

            for (ArrayList<Integer> list : graph) {
                for (Integer e : list) {
                    inDegree[e]++;
                }
            }

            int curRank = 0;
            int[] rankSet = new int[inDegree.length];


            for(int i=1;i<inDegree.length;i++){
                rankSet[inDegree[i]] = i;
            }

            StringBuilder ans = new StringBuilder();
            if(stream(inDegree).sum() != (n-1)*n/2 || stream(rankSet).sum() != n*(n+1)/2){
                ans.append("IMPOSSIBLE");
            }else{
                for(int i=0;i<rankSet.length-1;i++){
                    ans.append(rankSet[i]+" ");
                }
            }
            System.out.println(ans.toString());
        }
    }
}

