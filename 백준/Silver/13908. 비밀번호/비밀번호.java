import static java.util.Arrays.*;
import static java.util.stream.Collectors.*;

import java.text.Format;
import java.util.*;
import java.io.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    static int[] visit;
    static ArrayList<Integer> knowList;
    static int n,know,count;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
        n = input[0]; know = input[1];

        visit = new int[10];
        if(know>0){
            knowList = stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).boxed()
                    .collect(toCollection(ArrayList::new));
            dfs(0);
        }else 
            count=(int)Math.pow(10,n);
        System.out.println(count);
    }
    static void dfs(int depth){

        if(depth==n){

            for(int i=0;i<10;i++){
                if(visit[i]==0){
                    if(knowList.contains(i))
                        return;
                }
            }
            count++;
            return;
        }

        for(int i=0;i<10;i++){
            visit[i]++;
            dfs(depth+1);
            visit[i]--;
        }
    }
}

