
import java.util.LinkedList;
import java.util.PriorityQueue;

class Solution {

    int[][] map;
    int[] dx = {0,-1,1,0}, dy= {1,0,0,-1};
    static int k,r,c,m,n,x,y;
    PriorityQueue<String> queue = new PriorityQueue<>(1);

    String[] direction = {"d","l","r","u"};
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        String answer = "impossible";

        this.k = k;
        this.r= r;
        this.c= c;
        this.m = m;
        this.n = n;

        if(getDiff(y,x) > k || getDiff(y,x)%2 != k%2)
            return answer;

        dfs(new Node(y,x,0,new StringBuilder()));

        if(!queue.isEmpty())
            answer = queue.poll();
        return answer;
    }

    public int getDiff(int x,int y){
        return Math.abs(x-c) + Math.abs(y-r);
    }

    private void dfs(Node curNode) {

        if(!queue.isEmpty() || getDiff(curNode.x,curNode.y) + curNode.depth > k)
            return;

        if(curNode.depth == k && curNode.x == c && curNode.y == r){
            queue.add(curNode.sb.toString());
            return;
        }

        for(int i=0;i<direction.length;i++){

            int nextX = curNode.x+dx[i];
            int nextY = curNode.y+dy[i];

            if (isRange(nextX, nextY) && curNode.depth + 1 <= k) {

                StringBuilder sb = curNode.sb;
                sb.append(direction[i]);
                dfs(new Node(nextX, nextY, curNode.depth + 1, sb));
                sb.delete(sb.length() - 1, sb.length());
            }
        }
    }

    static class Node{

        int x;
        int y;
        int depth;
        StringBuilder sb;

        public Node(int x, int y, int depth, StringBuilder sb) {
            this.x = x;
            this.y = y;
            this.depth = depth;
            this.sb = sb;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "x=" + x +
                    ", y=" + y +
                    ", depth=" + depth +
                    ", sb=" + sb +
                    '}';
        }
    }
    public static boolean isRange(int x,int y){
        return x>=1 && y>=1 && x<=m && y<=n;
    }
}