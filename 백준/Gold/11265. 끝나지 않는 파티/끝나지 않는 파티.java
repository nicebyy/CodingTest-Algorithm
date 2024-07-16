
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import static java.util.Arrays.stream;

public class Main {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final String[] ans = {"Enjoy other party\n","Stay here\n"};

    public static void main(String[] args) throws IOException {

        int[] input = stream(br.readLine().split(" "))
                            .mapToInt(Integer::parseInt)
                            .toArray();
        int n = input[0];
        int m = input[1];

        int[][] map = new int[n+1][n+1];

        for(int i=1;i<=n;i++){
            map[i] = stream(("0 "+br.readLine()).split(" "))
                                .mapToInt(Integer::parseInt)
                                .toArray();
        }
        for(int k=1;k<=n;k++){
        for(int i=1;i<=n;i++){
            for(int j=1;j<=n;j++){


                    if(map[i][k] + map[k][j] < map[i][j]){
                        map[i][j] = map[i][k] + map[k][j];
                    }
                }
            }
        }

        StringBuilder ansBuilder = new StringBuilder();

        for(int i=0;i<m;i++){

            input = stream(br.readLine().split(" "))
                                .mapToInt(Integer::parseInt)
                                .toArray();

            if(map[input[0]][input[1]] <= input[2]){
                ansBuilder.append(ans[0]);
            }else{
                ansBuilder.append(ans[1]);
            }
        }

        System.out.println(ansBuilder.toString());
    }

    static void print(int[][] map){

        for(int i=1;i<map.length;i++){
            for(int j=1;j<map[0].length;j++){
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
    }
}
