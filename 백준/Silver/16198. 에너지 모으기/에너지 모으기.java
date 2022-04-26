import static java.util.Arrays.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;
import java.util.stream.Collectors;

public class Main {

    static int maxEnergy = Integer.MIN_VALUE;
    static int n;
    static int[] energy;
    static boolean[] visit;
    static int[] next;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        energy = stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
        visit = new boolean[n];
        next = new int[n];
        for(int i=0;i<n;i++)
            next[i]=i;

        getEnergy(0,0);
        System.out.println(maxEnergy);
    }

    public static void getEnergy(int depth,int totalEnergy){

//        System.out.print("curEnergy = "+totalEnergy);
//        System.out.println("     depth = "+depth);

        if(depth==n-2){
            maxEnergy = Math.max(totalEnergy,maxEnergy);
        }

        for(int i=1;i<n-1;i++){

            if(!visit[i]){
                visit[i]=true;
                getEnergy(depth+1,totalEnergy+(energy[findRight(i)]*energy[findLeft(i)]));

                visit[i]=false;
                next[i]=i;
            }
        }
    }

    public static int findRight(int x){

        if(!visit[x]){
            return x;
        }
        return next[x] = findRight(x+1);
    }

    public static int findLeft(int x){

        if(!visit[x]){
            return x;
        }
        return next[x] = findLeft(x-1);
    }
}