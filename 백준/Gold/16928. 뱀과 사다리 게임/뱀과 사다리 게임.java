
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

public class Main {

    static int[] map;
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {

        int[] input = stream(br.readLine().split(" "))
                            .mapToInt(Integer::parseInt)
                            .toArray();
        map = new int[101];

        for(int i=0;i<map.length;i++){
            map[i] = i;
        }

        for(int i=0;i<input[0] + input[1];i++) {
            int[] value = stream(br.readLine().split(" "))
                                .mapToInt(Integer::parseInt)
                                .toArray();

            map[value[0]] = value[1];
        }

        LinkedList<int[]> q = new LinkedList<>();
        boolean[] visit = new boolean[101];
        q.add(new int[]{1,0});

        int minDice = Integer.MAX_VALUE;
        while (!q.isEmpty()){

            int[] cur = q.poll();

            if(cur[0] == 100){
                minDice = Math.min(minDice,cur[1]);
            }

            for(int dice=1;dice<=6;dice++){

                int next = cur[0] + dice;

                if(next > 100 || visit[next]){
                    continue;
                }

                visit[next] = true;

                if(map[next] != next){
                    next = map[next];
                    visit[map[next]] = true;
                }
                
                q.add(new int[]{next,cur[1]+1});
            }
        }

        System.out.println(minDice);
    }
}


