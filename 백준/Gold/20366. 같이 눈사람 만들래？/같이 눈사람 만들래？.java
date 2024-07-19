
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import static java.util.Arrays.stream;

public class Main {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static ArrayList<Snowman> snowmanList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        int[] arr = stream(br.readLine().split(" "))
                            .mapToInt(Integer::parseInt)
                            .toArray();

        for(int i=0;i<arr.length-1;i++){
            for(int j=i+1;j<arr.length;j++){
                Snowman snowman = new Snowman(i, j,arr[i]+arr[j]);
                snowmanList.add(snowman);
            }
        }

        snowmanList.sort((o1, o2) -> o1.size-o2.size);
        
        int min = Integer.MAX_VALUE;
        
        for(int i=0;i<snowmanList.size()-2;i++){

            Snowman s1 = snowmanList.get(i);
            Snowman s2 = snowmanList.get(i+1);
            
            if(!s1.isDuplicate(s2)){
                min = Math.min(min,s1.getDiff(s2));
            }
        }

        System.out.println(min);
    }

    static class Snowman{
        int index1,index2;
        int size;

        public Snowman(int index1, int index2,int size) {
            this.index1 = index1;
            this.index2 = index2;
            this.size = size;
        }

        public int getDiff(Snowman s){
            return Math.abs(this.size-s.size);
        }

        public boolean isDuplicate(Snowman s){
            return this.index1 == s.index1  || this.index1 == s.index2 || this.index2 == s.index1 || this.index2 == s.index2;
        }
    }
}
