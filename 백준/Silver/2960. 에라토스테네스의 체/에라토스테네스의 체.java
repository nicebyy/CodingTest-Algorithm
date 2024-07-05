
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.util.Arrays.stream;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n,k;
    public static void main(String[] args) throws IOException {
        int[] input = stream(br.readLine().split(" "))
                            .mapToInt(Integer::parseInt)
                            .toArray();
         n = input[0];
         k = input[1];
         boolean[] visit = new boolean[n+1];

         for(int i=2;i<=n;i++){

             for(int j=1;j*i<=n;j++){
                 if(!visit[j*i]){
                     visit[j*i] = true;
                     k--;

                     if(k==0){
                         System.out.println(j*i);
                         return;
                     }
                 }
             }
         }
    }
}

