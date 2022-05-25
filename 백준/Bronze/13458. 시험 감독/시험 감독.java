import java.io.*;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Arrays.stream;


public class Main {



    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] arr = stream(br.readLine().split(" "))
                            .mapToInt(Integer::parseInt).sorted()
                            .toArray();


        int[] input = stream(br.readLine().split(" "))
                            .mapToInt(Integer::parseInt)
                            .toArray();
        int b = input[0];
        int c = input[1];

        long answer = 0;
        for(int i=0;i<n;i++){

            arr[i] -= b;
            answer++;

            if(arr[i]<0)
                continue;

            answer+=arr[i]/c;

            if(arr[i]%c>0)
                answer++;
        }

        System.out.println(answer);
    }
}
