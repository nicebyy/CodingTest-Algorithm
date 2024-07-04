
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static java.util.Arrays.stream;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int h,w;
    static char[][] map;
    static ArrayList<Point> meteors = new ArrayList<>();
    static Point[] bottomMeteors;
    public static void main(String[] args) throws IOException {


        int[] input = stream(br.readLine().split(" "))
                            .mapToInt(Integer::parseInt)
                            .toArray();
        h = input[0];
        w = input[1];
        map = new char[h][w];
        bottomMeteors = new Point[w];
        for(int i=0;i<h;i++){
            map[i] = br.readLine().toCharArray();

            for(int j=0;j<w;j++){
                if(map[i][j]=='X'){
                    map[i][j] = '.';
                    meteors.add(new Point(j,i));
                    bottomMeteors[j] = new Point(j,i);
                }
            }
        }

        drop(1);

        StringBuilder sb = new StringBuilder();

        for(int i=0;i<h;i++){
            for(int j=0;j<w;j++){
                sb.append(map[i][j]);
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    private static void drop(int distance) {

        for (Point meteor : bottomMeteors) {

            if(meteor==null || !isRange(meteor.x, meteor.y+distance)){
                continue;
            }

            if(map[meteor.y+distance][meteor.x]=='#'){
                print(distance-1);
                return;
            }
        }

        drop(distance+1);
    }

    private static boolean isRange(int x,int y){
        return x>=0 && y>=0 && x<w && y<h;
    }

    private static void print(int distance) {

        for (Point meteor : meteors) {
            map[meteor.y+distance][meteor.x] = 'X';
        }
    }

    static class Point{
        int x,y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
        public Point() {
            this.x = 0;
            this.y =0;
        }
    }
}

