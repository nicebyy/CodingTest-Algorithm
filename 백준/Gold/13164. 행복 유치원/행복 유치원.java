
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.util.Arrays.stream;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n;
    static int k;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        int[] input = stream(br.readLine().split(" "))
                            .mapToInt(Integer::parseInt)
                            .toArray();
        n = input[0];
        k = input[1];
        arr = stream(br.readLine().split(" "))
                            .mapToInt(Integer::parseInt)
                            .toArray();

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o2[1]==o1[1] ? o1[0]-o2[0] : o1[1]-o2[1]); // {index, value}

        for(int i=0; i<arr.length-1; i++){
            int value = Math.abs(arr[i] - arr[i + 1]);
            pq.add(new int[]{i,value});
        }

        int count = n-k;
        int cost = 0;
        while (count -- > 0 && !pq.isEmpty()){
            int[] poll = pq.poll();
            cost += poll[1];
        }
        System.out.println(cost);
    }
}

