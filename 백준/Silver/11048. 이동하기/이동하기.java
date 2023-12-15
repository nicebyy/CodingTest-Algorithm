
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

import static java.util.Arrays.stream;

public class Main {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int h,w;
    static int[][] map;
    static int[][] sum;
    public static void main(String[] args) throws IOException {
        int[] input = stream(br.readLine().split(" "))
                            .mapToInt(Integer::parseInt)
                            .toArray();
        h = input[0]; w = input[1];

        map = new int[h][w];
        sum = new int[h][w];

        for(int i=0; i<h;i++){
            map[i] = stream(br.readLine().split(" "))
                                .mapToInt(Integer::parseInt)
                                .toArray();
        }

        sum[0][0] = map[0][0];
        
        for(int i=0;i<h;i++){

            for(int j=0;j<w;j++){

                if(isRange(j,i+1)){
                    sum[i+1][j] = Math.max(sum[i+1][j],sum[i][j]+map[i+1][j]);
                }
                if(isRange(j+1,i)){
                    sum[i][j+1] = Math.max(sum[i][j+1],sum[i][j]+map[i][j+1]);
                }

                if(isRange(j+1,i+1)){
                    sum[i+1][j+1] = Math.max(sum[i+1][j+1],sum[i][j]+map[i+1][j+1]);
                }
            }
        }

        System.out.println(sum[h-1][w-1]);
    }

    static boolean isRange(int x,int y){
        return x>=0 && y>=0 && x<w && y<h;
    }
}


