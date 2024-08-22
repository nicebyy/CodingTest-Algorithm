
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import static java.util.Arrays.stream;

public class Main {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int[][] arr;
    static int[] dir = {1,0,-1};
    static int n,m;

    public static void main(String[] args) throws IOException {

        int[] input = stream(br.readLine().split(" "))
                            .mapToInt(Integer::parseInt)
                            .toArray();
        n = input[0];
        m = input[1];
        arr = new int[n+1][250];

        for(int i=1;i<=n;i++){
            Arrays.fill(arr[i],Integer.MAX_VALUE);
        }

        for(int i=0;i<m;i++){
            int pos = Integer.parseInt(br.readLine());
            Arrays.fill(arr[pos],-1);
        }

        arr[1][0] = 0;
        jump(1,0,0);

        int minValue = stream(arr[n]).min().getAsInt();
        int result = minValue == Integer.MAX_VALUE ? -1 : minValue;
        System.out.println(result);
    }


    public static void jump(int cur,int width,int depth){

        for(int i=0;i<dir.length;i++){

            int nextWidth = width + dir[i];
            int next = nextWidth + cur;

            if(nextWidth>0 && next <= n && arr[next][nextWidth] != -1){

                if(arr[next][nextWidth] > depth+1){
                    arr[next][nextWidth] = depth+1;
                    jump(next,width+dir[i],depth+1);
                }
            }
        }
    }
}

