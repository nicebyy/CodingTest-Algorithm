
import java.io.*;
import java.util.*;

import static java.util.Arrays.stream;

public class Main {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static HashSet<Long> duplicateCheckSet = new HashSet<Long>();
    static long MAX_RANGE = (long)Math.pow(10,9);

    public static void main(String[] args) throws IOException {

        long[] input = stream(br.readLine().split(" "))
                            .mapToLong(Long::parseLong)
                            .toArray();
        long start = input[0];
        long target = input[1];

        if(start == target){
            System.out.println(0);
            return;
        }

        LinkedList<Node> queue = new LinkedList<>();

        queue.add(new Node(start));

        while (!queue.isEmpty()){

            Node cur = queue.poll();
            duplicateCheckSet.add(cur.num);

            if(cur.num == target){ //ernd
                System.out.println(cur.builder.toString());
                return;
            }

            if(!duplicateCheckSet.contains(cur.num*cur.num) && MAX_RANGE >= cur.num*cur.num){
                queue.add(new Node(cur.num*cur.num,new StringBuilder(cur.builder),0));
            }
            if(!duplicateCheckSet.contains(cur.num+cur.num) && MAX_RANGE >= cur.num+cur.num){
                queue.add(new Node(cur.num+cur.num,new StringBuilder(cur.builder),1));
            }
            if(!duplicateCheckSet.contains(cur.num-cur.num)){
                queue.add(new Node(cur.num-cur.num,new StringBuilder(cur.builder),2));
            }
            if(cur.num!=0 && !duplicateCheckSet.contains(cur.num/cur.num)){
                queue.add(new Node(cur.num/cur.num,new StringBuilder(cur.builder),3));
            }
        }

        System.out.println(-1);
    }
    static class Node{
        long num;
        StringBuilder builder;

        public Node(long num) {
            this.num = num;
            this.builder = new StringBuilder();
        }
        public Node(long num,StringBuilder sb,int op) {
            this.num = num;

            if(op == 0){
                this.builder = sb.append("*");
            }else if(op == 1){
                this.builder = sb.append("+");
            }else if(op == 2){
                this.builder = sb.append("=");
            }else if(op == 3){
                this.builder = sb.append("/");
            }
        }
    }
}
