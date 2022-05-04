import java.io.*;
import java.util.*;

public class Main {
    static int W;
    static int[][] map;

    static int[] dr = { -1, 1, 0, 0 };
    static int[] dc = { 0, 0, -1, 1};
    static boolean[] house;
    static int listIndex;
    static ArrayList<Integer> list;
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


            W = Integer.parseInt(br.readLine());
            map = new int[W][W];
            list = new ArrayList<Integer>();
            listIndex = 0;
            for (int i = 0; i < W; i++) {
               String input[] = br.readLine().split("");
               for(int j=0;j<W;j++) {
            	   map[i][j] = Integer.parseInt(input[j]);
               }
            }
            

            house = new boolean[W*W/2+1];
            
            for (int i = 0; i < W; i++) {
                for (int j = 0; j < W; j++) {
                    if (map[i][j] == 1) {
//                        house[listIndex] = true;
                        list.add(1);
                        DFS(i, j,listIndex);                     
                        listIndex++;                     

                    }
                }
            }
            Collections.sort(list);
            System.out.println(list.size());
            for(int i : list) {
            	if(i>1)
            		System.out.println(i-1);
            	else
            		System.out.println(i);
            }
//            Arrays.sort(house);


        


    }

    private static void DFS(int i, int j,int index) {


        for (int dir = 0; dir < dc.length; dir++) {
            int nr = i + dr[dir];
            int nc = j + dc[dir];
            if (isRange(nr, nc) && map[nr][nc] == 1) {

                list.set(index,list.get(index)+1);
                map[nr][nc] = 0;
                DFS(nr, nc,index);
            }
        }
    }

    private static boolean isRange(int nr, int nc) {
        if (0 <= nr && nr < W && 0 <= nc && nc < W)
            return true;
        return false;
    }
}