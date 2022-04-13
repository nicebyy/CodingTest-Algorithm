import static java.util.Arrays.*;
import static java.util.stream.Collectors.*;

import java.util.*;
import java.io.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = stream(br.readLine().split(" "))
                  .mapToInt(Integer::parseInt).toArray();
        long[] sum = new long[n];

        for(int i=1;i<n;i++){
            sum[i] += sum[i-1]+arr[i];
        }

        long ans=0;
        for(int i=0;i<n-1;i++){
            ans+= (sum[n-1]-sum[i])*arr[i];
        }
        System.out.println(ans);
    }

}

