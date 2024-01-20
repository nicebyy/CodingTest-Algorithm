
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

            if(cur.num == target){ //end
                System.out.println(cur.builder.toString());
                return;
            }

            if(checkValid(cur.num*cur.num)){
                queue.add(new Node(cur.num*cur.num,cur.builder,0));
            }
            if(checkValid(cur.num+cur.num)){
                queue.add(new Node(cur.num+cur.num,cur.builder,1));
            }
            if(checkValid(0)){
                queue.add(new Node(0,cur.builder,2));
            }
            if(checkValid(1)){
                queue.add(new Node(1,cur.builder,3));
            }
        }
        System.out.println(-1);
    }
    static boolean checkValid(long nextNum){
        return !duplicateCheckSet.contains(nextNum) && nextNum <= MAX_RANGE;
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
            this.builder = new StringBuilder(sb);
          
            if(op == 0){
                this.builder.append("*");
            }else if(op == 1){
                this.builder.append("+");
            }else if(op == 2){
                this.builder.append("=");
            }else if(op == 3){
                this.builder.append("/");
            }
        }
    }
}
