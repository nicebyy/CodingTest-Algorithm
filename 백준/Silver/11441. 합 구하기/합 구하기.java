
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.util.Arrays.stream;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {

        int n = Integer.parseInt(br.readLine());
        int[] arr = stream(br.readLine().split(" "))
                            .mapToInt(Integer::parseInt)
                            .toArray();
        int[] sum = new int[n];
        int queryCount = Integer.parseInt(br.readLine());

        sum[0] = arr[0];
        for(int i=1;i<n;i++){
            sum[i] += sum[i-1]+arr[i];
        }
        
        StringBuilder ans = new StringBuilder();
        for(int q=0;q<queryCount;q++){
            int[] query = stream(br.readLine().split(" "))
                                .mapToInt(e->Integer.parseInt(e)-1)
                                .toArray();
            ans.append(sum[query[1]]-sum[query[0]]+arr[query[0]]).append("\n");
        }

        System.out.println(ans.toString());
    }
}

