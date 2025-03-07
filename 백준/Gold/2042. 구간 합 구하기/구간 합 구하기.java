
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

import static java.util.Arrays.stream;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static long[] array;
    public static void main(String[] args) throws IOException {
        int[] input = stream(br.readLine().split(" "))
                            .mapToInt(Integer::parseInt)
                            .toArray();
        array = new long[input[0]];
        for(int i=0; i<array.length; i++){
            array[i] = Long.parseLong(br.readLine());
        }
        TreeNode root = buildTree(array, 0, array.length-1);

        for(int i=0; i<input[1] + input[2]; i++){
            long[] query = stream(br.readLine().split(" "))
                                .mapToLong(Long::parseLong)
                                .toArray();
            if(query[0] == 1){
                update(root, Math.toIntExact(query[1]-1), query[2]);
            }else{
                long sum = getSum(root, Math.toIntExact(query[1]-1), Math.toIntExact(query[2]-1));
                System.out.println(sum);
            }

//            System.out.println(root.sum);
        }
    }

    static void update(TreeNode node, int index, long value){
        if(node.start == node.end){
            node.sum = value;
        }else{
            int mid = (node.start + node.end)/2;

            if(mid >= index){
                update(node.left, index, value);
            }else{
                update(node.right, index, value);
            }
            node.sum = node.left.sum + node.right.sum;
        }
    }

    static long getSum(TreeNode node, int from, int to){

        if(node.start == from && node.end == to){
            return node.sum;
        }

        int mid = (node.start + node.end)/2;

        if(to <= mid) {
            return getSum(node.left, from, to);
        } else if(from > mid) {
            return getSum(node.right, from, to);
        } else {
            return getSum(node.left, from, mid) + getSum(node.right, mid+1, to);
        }
    }

    static TreeNode buildTree(long[] array, int start, int end){

        TreeNode node = new TreeNode(start, end, 0);

        if(start == end){
            node.sum = array[start];
        }else{

            int mid = (start + end)/2;
            node.left = buildTree(array, start, mid);
            node.right = buildTree(array, mid+1, end);

            node.sum += node.left!=null ? node.left.sum : 0;
            node.sum += node.right!= null ? node.right.sum : 0;
        }
        return node;
    }

    static class TreeNode{
        int start = 0;
        int end = 0;
        long sum = 0;
        TreeNode left;
        TreeNode right;

        public TreeNode(int start, int end, long sum) {
            this.start = start;
            this.end = end;
            this.sum = sum;
        }
    }
}

/**
 * 5 2 2
 * 1
 * 2
 * 3
 * 4
 * 5
 * 1 3 6
 * 2 2 5
 * 1 5 2
 * 2 3 5
 */