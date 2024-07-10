
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import static java.util.Arrays.stream;

public class Main {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int n;
    static int[] arr;
    static int[] lis;

    public static void main(String[] args) throws IOException {

        n = Integer.parseInt(br.readLine());
        arr = stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        lis = new int[n];

        lis[0] = arr[0];
        int lisIndex = 1;

        for(int i=1;i<n;i++){

            if(arr[i] > lis[lisIndex-1]){
                lis[lisIndex++] = arr[i];
            }else{

                int pos = Arrays.binarySearch(lis, 0,lisIndex,arr[i]);
                
                if(pos >= 0){
                    lis[pos] = arr[i];
                }else{
                    lis[(-1*pos)-1] = arr[i];
                }
            }
        }

        System.out.println(n-lisIndex);
    }
}