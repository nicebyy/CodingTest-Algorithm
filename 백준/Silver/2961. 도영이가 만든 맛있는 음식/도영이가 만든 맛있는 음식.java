import static java.util.Arrays.*;
import static java.util.stream.Collectors.*;

import java.text.Format;
import java.util.*;
import java.io.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    static boolean[] visit;
    static int n;
    static long min=Long.MAX_VALUE;
    static long[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new long[n][2];
        visit = new boolean[n];
        for(int i=0;i<n;i++){
            arr[i] = stream(br.readLine().split(" "))
                    .mapToLong(Long::parseLong).toArray();
        }

        dfs(0,0,1,0);
        System.out.println(min);
    }
    static void dfs(int cur,int depth,long sour,long bitter){

        if(depth>0)
            min = Math.min(min,Math.abs(sour - bitter));
//        System.out.print("sour =>"+sour);
//        System.out.println("     bitter => "+bitter);
        if(depth==n)
            return;

        for(int next=cur;next<n;next++){

            if(!visit[next]){
                visit[next]=true;
                dfs(next,depth+1,sour*arr[next][0],bitter+arr[next][1]);
                visit[next]=false;
            }
        }
    }
}

