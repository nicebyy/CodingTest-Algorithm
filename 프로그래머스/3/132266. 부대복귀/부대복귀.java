
import java.util.*;
import java.util.List;

class Solution {
    public static int[] costs;
    public static List<ArrayList<Integer>> map = new ArrayList<>();
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {

        costs = new int[n+1];
        Arrays.fill(costs,Integer.MAX_VALUE);
        for(int i=0;i<=n;i++){
            map.add(new ArrayList<>());
        }
        for (int[] road : roads) {
            map.get(road[0]).add(road[1]);
            map.get(road[1]).add(road[0]);
        }

        costs[destination] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1]-o2[1]);
        pq.add(new int[]{destination,0});

        while (!pq.isEmpty()){

            int[] cur = pq.poll();

            for (Integer next : map.get(cur[0])) {
                if(costs[next] > cur[1]+1){
                    costs[next] = cur[1]+1;
                    pq.add(new int[]{next,costs[next]});
                }
            }
        }
        return Arrays.stream(sources)
                .map(e-> costs[e] == Integer.MAX_VALUE ? -1 : costs[e])
                .toArray();
    }
}