
import java.io.*;
import java.util.*;

public class Main {
    static int n, root;
    static int[] weight;
    static ArrayList<Integer>[] tree;
    // dp[v][last] : v번 노드의 서브트리 최적 비용, 최근의 특별 노드가 last번 노드일 때.
    static long[][] dp;
    static int rootID;

    // DFS 재귀함수: v번 노드, 부모 노드 parent, 현재 가장 가까운 특별 노드의 번호 last
    static long dfs(int v, int last, int parent) {
        if(dp[v][last] != -1) return dp[v][last];

        // Option 1: v를 특별 노드로 선택 -> 비용: w[v] + 자식 서브트리 비용(자식들에 대해 부모 특별노드를 v로 넘김)
        long costSpecial = weight[v];
        for (int child : tree[v]) {
            if (child == parent) continue;
            costSpecial += dfs(child, v, v);
        }

        long best;
        // 루트는 항상 특별 노드이므로, v가 루트인 경우에는 costSpecial만 고려
        if(v == rootID) {
            best = costSpecial;
        } else {
            // Option 2: v를 일반 노드로 두기 -> 비용: (w[v] - w[last]) + 자식 서브트리 비용(부모의 특별 노드 정보는 그대로 last)
            long costNotSpecial = weight[v] - weight[last];
            for (int child : tree[v]) {
                if (child == parent) continue;
                costNotSpecial += dfs(child, last, v);
            }
            best = Math.min(costSpecial, costNotSpecial);
        }
        dp[v][last] = best;
        return best;
    }

    public static void main(String[] args) throws Exception {
        // 입력 처리
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        root = Integer.parseInt(st.nextToken());
        rootID = root;

        weight = new int[n+1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++){
            weight[i] = Integer.parseInt(st.nextToken());
        }

        // 트리 인접 리스트 생성 (1-indexed)
        tree = new ArrayList[n+1];
        for(int i = 1; i <= n; i++){
            tree[i] = new ArrayList<>();
        }
        for(int i = 1; i < n; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            tree[u].add(v);
            tree[v].add(u);
        }

        // dp 배열 초기화 (-1로 초기화; dp[v][last]의 v, last는 1~n)
        dp = new long[n+1][n+1];
        for(int i = 0; i <= n; i++){
            Arrays.fill(dp[i], -1);
        }

        // 루트는 항상 특별노드이므로, 초기 호출 시 last를 root로 설정한다.
        long answer = dfs(root, root, -1);
        System.out.println(answer);
    }
}

/**
 * 7 1
 * 2 4 5 3 6 7 8
 * 1 2
 * 1 3
 * 1 4
 * 2 5
 * 2 6
 * 6 7
 */