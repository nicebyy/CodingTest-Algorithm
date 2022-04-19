import java.util.stream.*;

import static java.util.Arrays.*;
import static java.util.stream.Collectors.*;

import java.util.*;
import java.io.*;


public class Main {

    static int n, m,ans=0;
    static int[] parent;
    static ArrayList<ArrayList<Integer>> party = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
        n = input[0]; m = input[1];
        parent = new int[n + 1];
        for (int i = 1; i <= n; i++)
            parent[i] = i;

        input = stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();

        if(input[0]!=0){
            for(int i=1;i<=input[0];i++){
                union(0,input[i]);
            }
        }

//        System.out.println("Arrays.toString(parent) = " + Arrays.toString(parent));
        for(int i=0;i<m;i++){

            ArrayList<Integer> p = new ArrayList<>();
            input = stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();

            for(int j=1;j<=input[0];j++)
                p.add(input[j]);

            party.add(p);
        } // end input

        solve();
    }
    static void solve(){

        int ans=0;
        boolean condition = true;
        boolean[] visit = new boolean[m];

        while (condition){

            condition = false;

            for(int i=0;i<m;i++){
                if(visit[i]) continue;

                ArrayList<Integer> partyList = party.get(i);
                for (Integer member : partyList) {

                    if(findRoot(member)==findRoot(0)){

                        partyList.forEach(e->union(0,e));
                        condition = true;
                        visit[i]=true;

                        ans++;
                        break;
                    }
                }
            }
        }
        System.out.println(m-ans);
    }

    static void union(int x,int y){
        int xRoot = findRoot(x);
        int yRoot = findRoot(y);

        if(xRoot!=yRoot)
            parent[yRoot]=x;
    }

    static int findRoot(int x){

        if(x == parent[x])
            return x;
        parent[x] = findRoot(parent[x]);
        return parent[x];
    }
}

