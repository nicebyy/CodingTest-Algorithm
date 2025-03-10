
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
        StringBuilder answers = new StringBuilder();
        for(int i=0; i<input[1] + input[2]; i++){
            String[] queries = br.readLine().split(" ");
            int queryType = Integer.parseInt(queries[0]);

            if(queryType == 1){
                int index = Integer.parseInt(queries[1])-1;
                long value = Long.parseLong(queries[2]);
                update(root, index, value);
            }else{
                int from = Integer.parseInt(queries[1])-1;
                int to = Integer.parseInt(queries[2])-1;
                long sum = getSum(root, from, to);
                answers.append(sum).append("\n");
            }
        }
        System.out.println(answers);
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

        if(mid >= to) {
            return getSum(node.left, from, to);
        } else if(mid < from) {
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