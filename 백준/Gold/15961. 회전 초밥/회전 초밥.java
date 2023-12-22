
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.util.Arrays.stream;

public class Main {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int n,range,k,c;
    static int[] check,arr;
    public static void main(String[] args) throws IOException {

        int[] input = stream(br.readLine().split(" "))
                            .mapToInt(Integer::parseInt)
                            .toArray();
        n = input[0];
        range = input[1];
        k = input[2];
        c = input[3];

        int startIndex = 0;
        int endIndex = 0;
        int currentCount = 0;
        int max = 0;
        arr = new int[n+1];
        check = new int[range+1];


        for(int i=0;i<n+range;i++){

            endIndex = i%n;
            startIndex = startIndex%n;

            if(i<n) arr[endIndex] = Integer.parseInt(br.readLine());

            if(check[arr[endIndex]] == 0){
                currentCount ++ ;
            }
            check[arr[endIndex]]++;

            if(Math.abs(endIndex-startIndex)+1 <= k){

            }else{
                check[arr[startIndex]]--;
                if(check[arr[startIndex]] == 0){
                    currentCount --;
                }
                startIndex++;
            }

            if(check[c] == 0){
                max = Math.max(max,currentCount+1);
            }else{
                max = Math.max(max,currentCount);
            }
//            System.out.println(Arrays.stream(check).filter(e->e>0).count()+"..."+Arrays.toString(check));
//            System.out.println(max);
        }

        System.out.println(max);
    }
}


