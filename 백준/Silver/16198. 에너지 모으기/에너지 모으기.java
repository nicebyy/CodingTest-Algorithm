import static java.util.Arrays.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;
import java.util.stream.Collectors;

public class Main {

    static int maxEnergy = Integer.MIN_VALUE;
    static int n;
    static int[] energy;
    static boolean[] visit;
    static int[] next;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        energy = stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
        visit = new boolean[n];
        next = new int[n];
        for (int i = 0; i < n; i++)
            next[i] = i; // 현재 위치는 자기 자신

        getEnergy(0, 0);
        System.out.println(maxEnergy);
    }

    public static void getEnergy(int depth, int totalEnergy) {

        if (depth == n - 2) { // 최대값 갱신
            maxEnergy = Math.max(totalEnergy, maxEnergy);
        }

        for (int i = 1; i < n - 1; i++) {

            if (!visit[i]) { // 만약 방문 안한 곳이면
                visit[i] = true;
                getEnergy(depth + 1, totalEnergy +
                        energy[findRight(i)] * energy[findLeft(i)]); // 좌우로 방문된 곳 제외하고 새로 찾은 값
                visit[i] = false;
                next[i] = i;
            }
        }
    }

    public static int findRight(int x) {

        if (!visit[x]) // 현재 위치가 방문안한 곳이면 해당 인덱스 return
            return x;
        return next[x] = findRight(x + 1); // 다음 위치 return
    }

    public static int findLeft(int x) {

        if (!visit[x])
            return x;
        return next[x] = findLeft(x - 1);
    }
}