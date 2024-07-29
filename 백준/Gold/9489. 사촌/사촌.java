
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static java.util.Arrays.stream;

public class Main {


    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n,k;
    static int[] parent;

    public static void main(String[] args) throws IOException {

        String input="";
        while (!(input = br.readLine()).equals("0 0")){
            int[] init = stream(input.split(" "))
                                .mapToInt(Integer::parseInt)
                                .toArray();
            n = init[0]; k = init[1];
            parent = new int[n+1];
            int[] arr = stream(("-1 "+br.readLine()).split(" "))
                                .mapToInt(Integer::parseInt)
                                .toArray();

            int index = -1;
            int targetIndex = 0;
            parent[0] = -1;
            for(int i=1; i<=n; i++){

                if(arr[i] == k){
                    targetIndex = i;
                }
                if(arr[i] != arr[i-1]+1){
                    index++;
                }
                parent[i] = index;
            }

            int ans = 0;

            for(int i=1; i<=n; i++){

                if(parent[i] != parent[targetIndex] && parent[parent[i]] == parent[parent[targetIndex]]){
                    ans++;
                }
            }
            System.out.println(ans);
        }
    }
}


