
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.stream.Collectors;

import static java.lang.System.*;
import static java.util.Arrays.stream;

public class Main {

    static int[][] map;
    static int[][] virusMap;
    static boolean[][] visit;
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};
    static int n;
    static int virusCount;
    static int minTime = Integer.MAX_VALUE;
    static ArrayList<Virus> viruses = new ArrayList<>();
    static BufferedReader br = new BufferedReader(new InputStreamReader(in));

    public static void main(String[] args) throws IOException {

        int[] input = stream(br.readLine().split(" "))
                            .mapToInt(Integer::parseInt)
                            .toArray();
        n = input[0];
        virusCount = input[1];
        map = new int[n][n];
        visit = new boolean[n][n];

        for(int i=0; i<n; i++){
            int[] rows = stream(br.readLine().split(" "))
                                .mapToInt(Integer::parseInt)
                                .toArray();
            map[i] = rows;
            for(int j=0; j<n; j++){
                if(map[i][j] == 2){
                    viruses.add(new Virus(j,i));
                }
            }
        }

        boolean[] virusVisit = new boolean[viruses.size()];
        backtracking(0,new ArrayList<>(), virusVisit);
        if(minTime == Integer.MAX_VALUE){
            minTime = -1;
        }
        out.println(minTime);
    }

    static void backtracking(int index,ArrayList<Integer> virusComb, boolean[] virusVisit){

        if(virusCount == virusComb.size()){
            int virusSpreadTime = getVirusSpreadTime(virusComb);
            if(virusSpreadTime != -1){
                minTime = Math.min(minTime, virusSpreadTime);
            }
            return;
        }

        for(int i=index; i<viruses.size(); i++){

            if(!virusVisit[i]){
                virusVisit[i] = true;
                virusComb.add(i);
                backtracking(i, virusComb, virusVisit);
                virusComb.remove((Integer) i);
                virusVisit[i] = false;
            }
        }
    }

    static int getVirusSpreadTime(ArrayList<Integer> virusComb){

        LinkedList<Virus> queue = virusComb.stream()
                .map(index -> viruses.get(index))
                .collect(Collectors.toCollection(LinkedList::new));
        visit = new boolean[n][n];

        for (Virus virus : queue) {
            visit[virus.y][virus.x] = true;
        }
        int max = -1;
        while (!queue.isEmpty()){

            Virus poll = queue.poll();

            if(max < poll.count){
                max = poll.count;
            }

            for(int i=0; i<dx.length; i++){
                int nextX = dx[i] + poll.x;
                int nextY = dy[i] + poll.y;

                if(isRange(nextX, nextY) && map[nextY][nextX] != 1 && !visit[nextY][nextX]){
                    visit[nextY][nextX] = true;
                    queue.add(new Virus(nextX, nextY,poll.count+1));
                }
            }
        }

        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(!visit[i][j] && map[i][j] != 1){
                    max = -1;
                }
            }
        }
        return max;
    }

    static boolean isRange(int x, int y){
        return x>=0 && y>=0 && x<n && y<n;
    }

    static class Virus{
        int x;
        int y;
        int count;

        public Virus(int x, int y) {
            this.x = x;
            this.y = y;
            this.count = 0;
        }

        public Virus(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }

        @Override
        public String toString() {
            return "Virus{" +
                    "x=" + x +
                    ", y=" + y +
                    ", count=" + count +
                    '}';
        }
    }
}
