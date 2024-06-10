import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static java.util.Arrays.stream;

public class Main {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n,m,max;
    static int[] arr;
    public static void main(String[] args) throws IOException {

        int[] input = stream(br.readLine().split(" "))
                            .mapToInt(Integer::parseInt)
                            .toArray();
        n = input[0];
        m = input[1];
        max = 0;
        arr = stream(br.readLine().split(" "))
                            .mapToInt(Integer::parseInt)
                            .toArray();

        dfs(0,0,new ArrayList<Integer>());

        System.out.println(max);
    }

    public static void dfs(int index,int sum,ArrayList<Integer> list){

        if(list.size() == 3){
            max = Math.max(sum,max);
            return;
        }

        for(int i=index;i<arr.length;i++){

            int cur = arr[i];

            if(list.size() < 3 && sum+cur <= m){
                list.add(cur);
                dfs(i+1,sum+cur,list);
                list.remove((Integer)cur);
            }
        }
    }
}