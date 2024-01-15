
import java.io.*;
import java.util.*;

import static java.util.Arrays.stream;

public class Main {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static ArrayDeque<String> st = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {

        String[] input = br.readLine().split("");

        for(int i=0;i<input.length;i++){
            String e = input[i];

            if(input[i].equals(")")){

                int cnt = 0;
                while (!st.isEmpty() && !st.peekFirst().equals("(")){

                    String poll = st.pollFirst();

                    if(poll.equals("*")){
                        int len = Integer.parseInt(st.pollFirst());
                        cnt += len;
                    }else{
                        cnt ++;
                    }
                }

                st.pollFirst();
                cnt = cnt * Integer.parseInt(st.pollFirst());
                st.addFirst(String.valueOf(cnt));
                st.addFirst("*");
            }else{
                st.addFirst(input[i]);
            }


        }
        int ans = 0;
        while(!st.isEmpty()) {
            if(st.peekFirst().equals("*")) {
                st.pollFirst();
                ans += Integer.parseInt(st.pollFirst());
            }else {
                st.pollFirst();
                ans += 1;
            }
        }
        System.out.println(ans);
    }

}
/**
 * len : 1 2 1
 * op : 3 2 1
 * cnt : 1
 */