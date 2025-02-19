

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.util.Arrays.stream;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static ArrayList<ArrayList<Integer>> list = new ArrayList<>();
    static int[] level;
    static int[] parent;

    public static void main(String[] args) throws IOException {

        int n = Integer.parseInt(br.readLine());
        for(int i=0;i<=n;i++){
            list.add(new ArrayList<>());
        }

        for(int i=0;i<n-1;i++){
            int[] input = stream(br.readLine().split(" "))
                                .mapToInt(Integer::parseInt)
                                .toArray();
            int from = input[0];
            int to = input[1];

            list.get(from).add(to);
            list.get(to).add(from);
        }

        level = new int[n+1];
        parent = new int[n+1];

        setInit(1,0);

        int queryCount = Integer.parseInt(br.readLine());
        for(int i=0; i<queryCount; i++){
            int[] query = stream(br.readLine().split(" "))
                                .mapToInt(Integer::parseInt)
                                .toArray();
            int left = query[0];
            int right = query[1];

            while (level[left] != level[right]){

                if(level[left] > level[right]){
                    left = parent[left];
                }else{
                    right = parent[right];
                }
            }

            // 만약 두 노드의 레벨이 같은 경우
            while (left != right){
                left = parent[left];
                right = parent[right];
            }
            System.out.println(left);
        }
    }

    public static void setInit(int num,int depth){

        ArrayList<Integer> children = list.get(num);

        for (Integer child : children) {
            if(parent[child]!=0 || child == 1){
                continue;
            }
            parent[child] = num; // 부모 노드 지정
            level[child] = depth+1;
            setInit(child,depth+1);
        }
    }
}

/**
 * 15
 * 1 2
 * 1 3
 * 2 4
 * 3 7
 * 6 2
 * 3 8
 * 4 9
 * 2 5
 * 5 11
 * 7 13
 * 10 4
 * 11 15
 * 12 5
 * 14 7
 * 6
 * 6 11
 * 10 9
 * 2 6
 * 7 6
 * 8 13
 * 8 15
 *
 * =======
 *
 * 15
 * 1 2
 * 1 3
 * 2 4
 * 3 7
 * 6 2
 * 3 8
 * 4 9
 * 2 5
 * 5 11
 * 7 13
 * 10 4
 * 11 15
 * 12 5
 * 14 7
 * 1
 * 2 6
 */