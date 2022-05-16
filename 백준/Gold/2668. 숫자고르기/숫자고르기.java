import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;


public class Main {

    static int max=0;
    static int n;
    static boolean[] visit;
    static int[] number;
    static TreeSet<Integer> answer = new TreeSet<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        number = new int[n+1];
        visit = new boolean[n+1];

        for (int i = 1; i <= n; i++) {
            number[i] = Integer.parseInt(br.readLine());
        }

        for(int i=1;i<=n;i++){

            visit[i]=true;
            dfs(i,i);
            visit[i]=false;
        }

        System.out.println(answer.size());
        answer.forEach(System.out::println);
    }

    static void dfs(int cur,int start){

        if(!visit[number[cur]]){
            visit[number[cur]]=true;
            dfs(number[cur],start);
            visit[number[cur]]=false;
        }

        if(start == number[cur]){
            answer.add(number[cur]);
        }
    }
}
