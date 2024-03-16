
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;

class Solution {

    ArrayList<ArrayList<Point>> course = new ArrayList<>();
    boolean[] isGate,isSummit,visit;
    int minIntensity = Integer.MAX_VALUE;
    int minSummit = -1;
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        int[] answer = {};
        isGate = new boolean[n+1];
        isSummit = new boolean[n+1];

        for(int i=0;i<=n;i++){
            course.add(new ArrayList<>());
        }

        for (int[] path : paths) {

            int start = path[0];
            int end = path[1];
            int weight = path[2];

            course.get(start).add(new Point(-1,end,weight));
            course.get(end).add(new Point(-1,start,weight));
        }

        for (int gate : gates) {
            isGate[gate] = true;
        }
        for (int summit : summits) {
            isSummit[summit] = true;
        }
//////////////////////////////////////////////////////////////////

        Arrays.sort(summits);
        for (int summit : summits) {
            bfs(summit);
        }

        answer = new int[]{minSummit,minIntensity};
        return answer;
    }

    private void bfs(int start) {

        PriorityQueue<Point> queue = new PriorityQueue<>();
        queue.add(new Point(start,start,0));

        visit = new boolean[course.size()];

        while (!queue.isEmpty()){

            Point cur = queue.poll();
            visit[cur.node] = true;

//            if(isGate[cur.node]){
//
//                if(minIntensity > cur.weight){
//                    minIntensity = cur.weight;
//                    minSummit = cur.startSummit;
//                    break;
//                }
//            }


            for (Point next : course.get(cur.node)) {

                if(isSummit[next.node] || visit[next.node] ||  Math.max(next.weight,cur.weight) > minIntensity){
                    continue;
                }

                if(isGate[next.node] && minIntensity > Math.max(next.weight,cur.weight)){
                    minIntensity =  Math.max(next.weight,cur.weight);
                    minSummit = cur.startSummit;
                }else{
                    queue.add(new Point(cur.startSummit,next.node,Math.max(next.weight,cur.weight)));
                }
            }
        }
    }

    static class Point implements Comparable<Point>{

        int startSummit;
        int node;
        int weight;

        public Point(int startSummit,int node, int weight) {
            this.node = node;
            this.weight = weight;
            this.startSummit = startSummit;
        }

        @Override
        public int compareTo(Point o) {
            return this.weight - o.weight;
        }
    }
}