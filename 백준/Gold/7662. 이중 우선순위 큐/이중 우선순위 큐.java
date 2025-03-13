
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.TreeMap;

import static java.util.Arrays.stream;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int n;
    static int tc;
//    static PriorityQueue<Integer> minQ;
//    static PriorityQueue<Integer> maxQ;
    static TreeMap<Integer,Integer> map;
    public static void main(String[] args) throws IOException {
        tc = Integer.parseInt(br.readLine());
        while (tc --> 0){
            n = Integer.parseInt(br.readLine());

//            minQ = new PriorityQueue<>();
//            maxQ = new PriorityQueue<>();
            map = new TreeMap<>();

            for(int i =0; i<n; i++){

                String[] query = br.readLine().split(" ");
                int num = Integer.parseInt(query[1]);
                if(query[0].equals("I")){
                    if(map.containsKey(num)){
                        map.put(num,map.get(num)+1);
                    }else{
                        map.put(num,1);
                    }
                }else if(query[0].equals("D")){

                    Integer key = null;
                    if(map.isEmpty()){
                        continue;
                    }

                    if(num == 1){
                        key = map.lastKey();
                    }else{
                        key = map.firstKey();
                    }
                    Integer value = map.get(key);
                    if(value == 1){
                        map.remove(key);
                    }else if(value > 1){
                        map.put(key, value-1);
                    }
                }
            }

            if(map.isEmpty()){
                System.out.println("EMPTY");
            }else{
                int max = map.lastKey();
                int min = map.firstKey();

                System.out.println(max+" "+min);
            }
        }
    }
}
