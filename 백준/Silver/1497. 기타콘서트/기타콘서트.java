
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.util.Arrays.stream;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n,k;
    static boolean[] visit;
    static long[] arr;
    static long COMPLETE = 0L;
    static long minValue = Long.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        int[] input = stream(br.readLine().split(" "))
                            .mapToInt(Integer::parseInt)
                            .toArray();
         n = input[0];
         k = input[1];
         visit = new boolean[n];
         arr = new long[n];

         for(int i=0;i<n;i++){

             String[] in = br.readLine().split(" ");
             String binaryString = in[1].replaceAll("Y", "1").replaceAll("N", "0");
             long value = Long.parseLong(binaryString,2);
             arr[i] = value;
             COMPLETE = COMPLETE | arr[i];
         }

         dfs(0,0,0);
        System.out.println(minValue == 0L ? -1L : minValue);
    }

    public static void dfs(int curIndex,int depth,long curValue){

        if(curValue == COMPLETE){
            minValue = Math.min(minValue,depth);
            return;
        }

        for(int i=curIndex;i<n;i++){

            if(!visit[i]){

                visit[i]=true;
                long nextValue = arr[i] | curValue;
                dfs(i,depth+1,nextValue);
                visit[i] = false;
            }
        }
    }
}

/**
 * 4 5
 * GIBSON YYYNN
 * FENDER YYNNY
 * EPIPHONE NNNYY
 * ESP YNNNN
 */