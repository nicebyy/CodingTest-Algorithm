import java.util.*;
import java.io.*;
import java.util.stream.Collectors;

public class Main{

    static int N,cnt=0;
    static int[] map;
    static int[] dx = {1,1,-1,-1},dy = {1,-1,-1,1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N];
        dfs(0);
        System.out.println(cnt);
    }

    private static void dfs(int depth) {
        if(depth==N){
            cnt++;
            return;
        }

        for(int i=0;i<N;i++){
            map[depth]=i;

            if(isRange(depth)) dfs(depth+1);
        }
    }

    private static boolean isRange(int depth) {

        for(int i=0;i<depth;i++){
            if(map[depth] == map[i]) return false;
            else if(Math.abs(depth-i)==Math.abs(map[depth]-map[i])) return false;
        }
        return true;
    }


}





