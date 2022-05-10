import java.util.*;
import java.io.*;
import java.util.stream.*;

import static java.util.Arrays.*;
import static java.util.stream.Collectors.*;
import static java.util.stream.IntStream.*;

public class Main {

    static int[] dx = {0,0,1,-1},dy={1,-1,0,0};
    static int[][] map;
    static boolean[][] visit;
    static int n,r,l;
    static LinkedList<int[]> target,q;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 초기설정
        int[] input = stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
        n = input[0]; l = input[1]; r = input[2];
        map = new int[n][n];
        for(int i=0;i<n;i++) {
            map[i] = stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();
        }

        int day=0;
        while (true){
            visit = new boolean[n][n];
            boolean condition = false;
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++)
                    if(!visit[i][j] && bfs(j,i)) // 아직 방문하지 않은 연합 탐색
                        condition=true;
            }

            if(!condition) break; // 연합 대상이 없는 경우
            else day++;
        }


        System.out.println(day);
    }

    static boolean bfs(int x,int y){

        target = new LinkedList<>();
        target.add(new int[]{x,y});
        int sum=map[y][x];

        q = new LinkedList<>();
        visit[y][x]=true;
        q.add(new int[]{x,y});

        while (!q.isEmpty()){

            int[] cur = q.poll();

            for(int i=0;i<dx.length;i++){
                int nextX = cur[0]+dx[i];
                int nextY = cur[1]+dy[i];
                if(!isRange(nextX,nextY)) continue;

                int diff = Math.abs(map[nextY][nextX] - map[cur[1]][cur[0]]);

                if(diff>=l&&diff<=r&& !visit[nextY][nextX]){
                    // bfs 탐색큐에 다음 좌표 삽입
                    visit[nextY][nextX]=true;
                    q.add(new int[]{nextX,nextY});
                    // 연합 대상국가 target 큐에 삽입
                    target.add(new int[]{nextX,nextY});
                    sum+=map[nextY][nextX];
                }
            }
        }

        // 연합
        if(target.size()>1){
            int avg = sum/target.size();

            while (!target.isEmpty()){
                int[] poll = target.poll();
                map[poll[1]][poll[0]] = avg;
            }
            return true;
        }
        // 연합 조건을 만족하지 않을 시
        return false;
    }

    static boolean isRange(int x,int y){
        return x>=0&&y>=0&&x<n&&y<n;
    }

}
