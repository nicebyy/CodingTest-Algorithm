import static java.util.Arrays.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;
import java.util.stream.Collectors;

public class Main {

    static int n;
    static boolean[] isPrime;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        dfs("",0);

    }

    static void dfs(String cur,int depth){

        if(depth==n){
            //todo
            System.out.println(cur);
            return;
        }

        for(int i=1;i<=9;i++){
            if(isPrime(Integer.parseInt(cur+i))){
                dfs(cur+i,depth+1);
            }
        }

    }
    private static boolean isPrime(int num) {

        if(num==1)
            return false;
        int sqrt=(int)Math.sqrt(num);
        for(int i=2; i<=sqrt; i++) {
            if(num%i==0)
                return false;
        }
        return true;
    }
}