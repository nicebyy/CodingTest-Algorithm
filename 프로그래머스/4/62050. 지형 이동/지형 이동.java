import java.util.*;
class Solution {

    int[] dx = {1,-1,0,0} , dy = {0,0,1,-1};
    int h,w,height;
    boolean[][] visit;
    int[][] label;
    int[] parent;

    PriorityQueue<Edge> pq = new PriorityQueue<Edge>((o1, o2) -> o1.cost - o2.cost);
    public int solution(int[][] land, int height) {
        int answer = 0;
        h = land.length;;
        w = land[0].length;
        this.height = height;
        label = new int[h][w];
        visit = new boolean[h][w];

        int landCount = 0;
        for(int i=0;i<h;i++){
            for(int j=0;j<w;j++){

                if(!visit[i][j]){
                    visit[i][j] = true;
                    ++landCount;
                    dfs(j,i,landCount,land);
                }
            }
        }
        visit = new boolean[h][w];
        for(int i=0;i<h;i++){
            for(int j=0;j<w;j++){

                if(!visit[i][j]){
                    visit[i][j] = true;

                    setEdge(j,i,land);
                }
            }
        }

        parent = new int[landCount+1];
        for(int i=1;i<parent.length;i++){
            parent[i] = i;
        }

        int costSum = 0;
        int edgeCount = 0;
        while (!pq.isEmpty()){

            if(edgeCount == landCount){
                break;
            }

            Edge poll = pq.poll();

            int xRoot = find(poll.start);
            int yRoot = find(poll.end);

            if(xRoot != yRoot){
                union(poll.start,poll.end);
                costSum += poll.cost;
                edgeCount++;
            }
        }
        System.out.println(costSum);
        return costSum;
    }

    public int find(int x){

        if(parent[x] == x){
            return x;
        }
        parent[x] = find(parent[x]);
        return parent[x];
    }

    public void union(int x,int y){
        int xRoot = find(x);
        parent[xRoot] = y;
    }

    private void setEdge(int x, int y,int[][] land) {

        visit[y][x] = true;

        for(int i=0;i<dx.length;i++){
            int nextX = dx[i] + x;
            int nextY = dy[i] + y;

            if(!isRange(nextX,nextY))
                continue;

            if(label[nextY][nextX] != label[y][x] && !visit[nextY][nextX]){

                visit[nextY][nextX] = true;

                int cost = Math.abs(land[y][x] - land[nextY][nextX]) > height ?
                        Math.abs(land[y][x] - land[nextY][nextX]) : 0;
                pq.add( new Edge(label[y][x],label[nextY][nextX],cost));
                setEdge(nextX,nextY,land);
            }
        }
    }

    private void dfs(int x, int y, int landCount,int[][] land) {

        visit[y][x] = true;
        label[y][x] = landCount;

        for(int i=0;i<dx.length;i++){

            int nextX = dx[i] + x;
            int nextY = dy[i] + y;

            if(!isRange(nextX,nextY))
                continue;
            int diff = Math.abs(land[nextY][nextX] - land[y][x]);

            if(diff <= height && !visit[nextY][nextX]){
                dfs(nextX,nextY,landCount,land);
            }
        }
    }

    boolean isRange(int x,int y){
        return x>=0 && x<w && y>=0 && y<h;
    }

    static class Edge{

        int start,end;
        int cost;

        public Edge(int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }
    }
}