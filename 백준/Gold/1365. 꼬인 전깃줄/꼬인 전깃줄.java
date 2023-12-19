
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
        lis = new int[arr.length];
        lis[0] = arr[0];
        int index = 0;
        for(int i=1;i<lis.length;i++){

            if(lis[index] < arr[i]){
                lis[++index] = arr[i];
            }else if(lis[index] > arr[i]){

                int start = 0;
                int end = index;

                while (start < end){

                    int mid = (start + end)/2;

                    if(lis[mid] < arr[i]){
                        start = mid+1;
                    }else{
                        end = mid;
                    }
                }

                lis[end] = arr[i];
            }
        }

        System.out.println(n-(index+1));

    }
}


