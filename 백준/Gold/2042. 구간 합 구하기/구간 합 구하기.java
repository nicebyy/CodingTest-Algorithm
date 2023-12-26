
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.util.Arrays.stream;

public class Main {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int n,queryCount,changeCount;
    static long[] arr,tree;
    public static void main(String[] args) throws IOException {

        int[] input = stream(br.readLine().split(" "))
                            .mapToInt(Integer::parseInt)
                            .toArray();

        n = input[0];
        changeCount = input[1];
        queryCount = input[2];
        int h = (int) Math.ceil(Math.log(n)/Math.log(2));
        tree = new long[(int) Math.pow(2,h+1)];
        arr = new long[n+1];

        for(int i=1;i<=n;i++){
            arr[i] = Long.parseLong(br.readLine());
        }

        setTree(1,1,n);

        for(int q=0; q<queryCount+changeCount; q++){

            long[] query = stream(br.readLine().split(" "))
                                .mapToLong(Long::parseLong)
                                .toArray();
            if(query[0] == 1){

                int targetIndex = Math.toIntExact(query[1]);
                long updateValue = query[2];

                long diff = updateValue - arr[targetIndex];
                arr[targetIndex] = updateValue;
                updateTree(1,1,n,targetIndex,diff);
            }else{
                long sum = getTreeSum(1,1, n,query[1],query[2]);
                System.out.println(sum);
            }
        }
    }

    public static long getTreeSum(int node, int start, int end,long left,long right){

        if(left > end || right < start){
            return 0;
        }
        if(left <= start && right >= end){
            return tree[node];
        }
        return getTreeSum(node*2, start, (start+end)/2, left, right)+
                getTreeSum(node*2+1, (start+end)/2+1, end, left, right);
    }
    public static void updateTree(int node,int start,int end,int targetIndex, long diff) {

        if(targetIndex >= start && targetIndex <= end){
            tree[node] = tree[node] + diff;

            if(start != end){
                updateTree(node*2,start, (start+end)/2, targetIndex, diff);
                updateTree(node*2+1,(start+end)/2+1, end, targetIndex, diff);
            }
        }
    }

    public static long setTree(int node,int start,int end){

        if(start == end){
            tree[node]  = arr[start];
        }else{
            long leftSum = setTree(node * 2, start, (start + end) / 2);
            long rightSum = setTree(node * 2+1, (start+end)/2+1, end);
            tree[node] = leftSum + rightSum;
        }
        return tree[node];
    }
}


