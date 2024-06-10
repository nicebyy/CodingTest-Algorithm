
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

import static java.util.Arrays.stream;

public class Main {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n,m,tc;
    static int[][] map;
    static int[] rows,cols;
    public static void main(String[] args) throws IOException {

        tc = Integer.parseInt(br.readLine());

        while (tc-->0){

            int[] input = stream(br.readLine().split(" "))
                                .mapToInt(Integer::parseInt)
                                .toArray();

            n = input[0];
            m = input[1];

            map = new int[n][n];
            rows = new int[n];
            cols = new int[n];

            for(int i=0;i<n;i++){

                map[i] = stream(br.readLine().split(" "))
                                    .mapToInt(Integer::parseInt)
                                    .toArray();
                rows[i] = stream(map[i]).sum();
            }

            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    cols[i] += map[j][i];
                }
            }

            while (m-->0){

                int[] query = stream(br.readLine().split(" "))
                                    .mapToInt(Integer::parseInt)
                                    .toArray();

                int[] start = new int[]{query[0]-1,query[1]-1};
                int[] end = new int[]{query[2]-1,query[3]-1};
                int value = query[4];

                for(int i=start[0];i<=end[0];i++){
                    rows[i] += (end[1]-start[1]+1)*value;
                }

                for(int j=start[1];j<=end[1];j++){
                    cols[j] += (end[0]-start[0]+1)*value;
                }
            }

            for(int i=0;i<rows.length;i++){
                System.out.print(rows[i]+" ");
            }
            System.out.println();

            for(int i=0;i<cols.length;i++){
                System.out.print(cols[i]+" ");
            }
            System.out.println();
        }
    }
}
