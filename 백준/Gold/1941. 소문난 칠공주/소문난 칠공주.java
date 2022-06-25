import java.lang.reflect.Array;
import java.text.Format;
import java.util.*;
import java.io.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.*;

public class Main {


    static ArrayList<Point> list = new ArrayList<>();
    static int[] dx={0,0,1,-1},dy={1,-1,0,0},dz={1,-1};
    static char[][] map = new char[5][5];
    static boolean[][] visit = new boolean[5][5];
    static boolean[] check = new boolean[25];
    static int ans=0;

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {

        for(int i=0;i<5;i++){
            map[i] = br.readLine().toCharArray();
            for(int j=0;j<5;j++)
                list.add(new Point(j,i));
        }

        comb(0,0,0);
        System.out.println(ans);
    }
    static void comb(int cur,int depth,int sCnt) {

        if (depth == 7) {

            if(sCnt<4)
                return;

            if (isClosable())
                ans++;
            return;
        }

        for(int next=cur;next<25;next++){
            Point np = list.get(next);

            if(!check[next]){
                check[next]=true;
                comb(next,depth+1,map[np.y][np.x]=='S' ? sCnt+1:sCnt);
                check[next]=false;
            }
        }
    }

    private static boolean isClosable() {

        char[][] clone = new char[5][5];
        for(int i=0;i<5;i++)
            clone[i] = map[i].clone();
        int index = 0;
        for(int i=0;i<check.length;i++){
            if(check[i]){
                Point point = list.get(i);
                clone[point.y][point.x]='1';
                index = i;
            }
        }

        LinkedList<Point> q = new LinkedList<>();
        Point cur = list.get(index);
        q.add(cur);
        visit = new boolean[5][5];
        visit[cur.y][cur.x]=true;
        int visitCnt = 1;

        while (!q.isEmpty()){
            Point poll = q.poll();

            for(int i=0;i<dx.length;i++){

                int nextX = poll.x + dx[i];
                int nextY = poll.y + dy[i];

                if(isRange(nextX,nextY) && !visit[nextY][nextX] && clone[nextY][nextX]=='1'){
                    visit[nextY][nextX]=true;
                    q.add(new Point(nextX,nextY));
                    visitCnt++;
                }
            }
        }

        return visitCnt==7;
    }

    static boolean isRange(int x,int y){

        return x>=0&&y>=0&&x<5&&y<5;
    }


    static class Point{

        int x,y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
}
