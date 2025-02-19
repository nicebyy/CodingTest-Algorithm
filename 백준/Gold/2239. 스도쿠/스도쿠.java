

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.util.Arrays.stream;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[][] map;
    static int count = 0;
    static LinkedList<int[]> blanks = new LinkedList<>();
    public static void main(String[] args) throws IOException {

        map = new int[9][9];

        for(int i=0;i<9;i++){
            map[i] = stream(br.readLine().split(""))
                                .mapToInt(Integer::parseInt)
                                .toArray();
            for(int j=0;j<9;j++){
                if(map[i][j] == 0){
                    blanks.add(new int[]{j,i});
                }
            }
        }

        solve(0);
    }

    public static void solve(int index){

        if(index == blanks.size()){
            for(int i=0;i<9;i++){
                for(int j=0;j<9;j++){
                    System.out.print(map[i][j]);
                }
                System.out.println();
            }
            System.exit(0);
        }

        int[] point = blanks.get(index);
        int x = point[0];
        int y = point[1];

        boolean[] check = new boolean[10];

        for(int i=0; i<9;i++){
            check[map[y][i]] = true; // 가로 체크
            check[map[i][x]] = true; // 세로 체크
        }

        int[] firstPoint = getFirstPoint(x, y);
        int firstPointX = firstPoint[0];
        int firstPointY = firstPoint[1];

        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                check[map[firstPointY+i][firstPointX+j]] = true;
            }
        }

        for(int i=1; i<=9; i++){
            if(!check[i]){
                map[y][x] = i;
                solve(index+1);
                map[y][x] = 0;
            }
        }
    }

    public static int[] getFirstPoint(int x, int y){
        return new int[]{x-(x%3), y-(y%3)};
    }
}
