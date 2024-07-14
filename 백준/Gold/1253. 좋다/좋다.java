
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.util.Arrays.copyOf;
import static java.util.Arrays.stream;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int n;
    static int[] arr;

    public static void main(String[] args) throws IOException {

        n = Integer.parseInt(br.readLine());

        arr = stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        Arrays.sort(arr);
        int ans = 0;
        for (int i = 0; i < n; i++) {

            int start = 0;
            int end = n - 1;

            while (start < end) {

                int sum = arr[start] + arr[end];

                if (sum == arr[i]) {
                    if(start != i && end != i){
                        ans++;
                        break;   
                    }else if(start == i){
                        start++;
                    }else{
                        end--;
                    }
                    
                } else if (sum < arr[i]) {
                    start++;
                } else {
                    end--;
                }
            }
        }

        System.out.println(ans);
    }
}
