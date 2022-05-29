import java.util.*;
import java.io.*;
import java.util.stream.*;

import static java.util.Arrays.stream;

public class Main {

    static int n;
    static int[] dx={-2,-2,0,0,2,2},dy={-1,1,-2,2,-1,1};
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        int[][] map = new int[n][n];
        boolean[][] visit = new boolean[n][n];
        int[] input = stream(br.readLine().split(" "))
                            .mapToInt(Integer::parseInt)
                            .toArray();

        int[] s = new int[]{input[0],input[1],0};
        int[] e = new int[]{input[2],input[3]};

        LinkedList<int[]> q = new LinkedList<>();
        q.add(s);

        int ans=-1;
        while (!q.isEmpty()){

            int[] cur = q.poll();

            if(cur[1]==e[1] && cur[0]==e[0]){
                ans = cur[2];
                break;
            }

            for(int i=0;i<dx.length;i++){
                int nextX = cur[0]+dx[i];
                int nextY = cur[1]+dy[i];

                if(isRange(nextX,nextY) && !visit[nextY][nextX]) {
                    visit[nextY][nextX]=true;
                    q.add(new int[]{nextX, nextY, cur[2] + 1});
                }
            }
        }

        System.out.println(ans);
    }

    static boolean isRange(int x,int y){

        return x>=0&&y>=0&&x<n&&y<n;
    }


}
