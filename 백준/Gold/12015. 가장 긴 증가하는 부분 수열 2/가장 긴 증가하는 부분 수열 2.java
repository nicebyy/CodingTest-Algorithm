
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

import static java.util.Arrays.stream;

public class Main {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {

        int n = Integer.parseInt(br.readLine());
        int[] arr = stream(br.readLine().split(" "))
                            .mapToInt(Integer::parseInt)
                            .toArray();
        int[] lis = new int[n];
        lis[0] = arr[0];

        int index = 0;
        for(int i=1;i<n;i++){

            if(lis[index] < arr[i]){
                lis[++index] = arr[i];
            }else{
                int pos = binarySearch(lis, index, arr[i]);
                lis[pos] = arr[i];
            }
        }

        System.out.println(index+1);
    }
    private static int binarySearch(int[] lis, int index, int target) {

        int left = 0;
        int right = index+1;

        while (left < right){

            int mid = (left + right) / 2;
            
            if(lis[mid] == target){
                return mid;
            }else if(lis[mid] > target){
                right = mid;
            }else{
                left = mid+1;
            }
        }
        return left;
    }
}