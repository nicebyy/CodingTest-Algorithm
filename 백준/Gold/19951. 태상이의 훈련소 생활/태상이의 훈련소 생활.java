
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import static java.util.Arrays.stream;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static long minValue = Long.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        int[] input = stream(br.readLine().split(" "))
                            .mapToInt(Integer::parseInt)
                            .toArray();
         int n = input[0];
         int m = input[1];
         int[] arr = stream(("0 "+br.readLine()).split(" "))
                            .mapToInt(Integer::parseInt)
                            .toArray();
        int[] orderSum = new int[arr.length+1];

        for(int i=0; i<m; i++){
            int[] order = stream(br.readLine().split(" "))
                                .mapToInt(Integer::parseInt)
                                .toArray();

            int from = order[0];
            int to = order[1];
            int value = order[2];

            orderSum[from] += value;
            orderSum[to+1] -= value;
        }

        for(int i=1;i<=n;i++){
            orderSum[i] += orderSum[i-1];
        }

        for(int i=1;i<=n;i++){
            System.out.print((arr[i]+orderSum[i])+" ");
        }
    }
}
