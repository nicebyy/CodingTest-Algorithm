import java.util.*;
import java.io.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = input[0],K = input[1];
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        LinkedList<Integer> plug = new LinkedList<>();
        int cnt=0;

        for(int i=0;i<K;i++){
            int cur = arr[i];
            int index = plug.indexOf(cur);
            if(index!=-1) continue;

            if( plug.size()<N ) plug.add(cur);
            else{
                LinkedList<Integer> list = new LinkedList<>(plug);
                int next = i+1;
                while (list.size()>1 && next < K) list.remove((Integer)arr[next++]);
                index = plug.indexOf(list.getFirst());
                plug.set(index,cur);
                cnt++;
            }
        }
        System.out.println(cnt);
    }
}

