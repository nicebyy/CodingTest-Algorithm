
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.util.Arrays.stream;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int tc;
    static int h;
    static int w;
    static char[][] map;
    static boolean[][] visit;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {

        tc = Integer.parseInt(br.readLine());
        while (tc-- > 0) {
            int[] input = stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            w = input[0];
            h = input[1];
            map = new char[h][w];
            visit = new boolean[h][w];

            LinkedList<Node> fireQueue = new LinkedList<>();
            LinkedList<Node> moveQueue = new LinkedList<>();

            for (int i = 0; i < h; i++) {
                map[i] = br.readLine().toCharArray();
                for (int j = 0; j < w; j++) {
                    if(map[i][j] == '*'){
                        fireQueue.add(new Node(j,i,1));
                    }else if(map[i][j] == '@'){
                        moveQueue.add(new Node(j,i,1));
                        visit[i][j] = true;
                    }
                }
            }
            int minTurn = Integer.MAX_VALUE;

            boolean cond = false;
            while (!cond && !moveQueue.isEmpty()){

                LinkedList<Node> nextFires = new LinkedList<>();
                LinkedList<Node> nextMoves = new LinkedList<>();

                while (!fireQueue.isEmpty()){
                    Node curFire = fireQueue.poll();

                    for(int i=0; i<dx.length;i++){
                        int nextX = curFire.x + dx[i];
                        int nextY = curFire.y + dy[i];

                        if(!isRange(nextX,nextY)){
                            continue;
                        }

                        if(map[nextY][nextX] == '@' || map[nextY][nextX] == '.'){
                            map[nextY][nextX] = '*';
                            nextFires.add(new Node(nextX,nextY, curFire.turn+1));
                        }
                    }
                }

                while (!moveQueue.isEmpty()){
                    Node curMove = moveQueue.poll();

                    if(isEdge(curMove.x, curMove.y)){
                        cond = true;
                        minTurn = curMove.turn;
                        break;
                    }

                    for(int i=0; i<dx.length;i++){
                        int nextX = curMove.x + dx[i];
                        int nextY = curMove.y + dy[i];

                        if(!isRange(nextX,nextY) || !isEmptySpace(nextX,nextY) || visit[nextY][nextX]){
                            continue;
                        }
                        visit[nextY][nextX] = true;
                        nextMoves.add(new Node(nextX,nextY, curMove.turn+1));
                    }
                }
                moveQueue = nextMoves;
                fireQueue = nextFires;
            }

            if(minTurn == Integer.MAX_VALUE){
                System.out.println("IMPOSSIBLE");
            }else{
                System.out.println(minTurn);
            }
        }
    }

    static class Node {
        int x, y;
        int turn;

        public Node(int x, int y , int turn) {
            this.x = x;
            this.y = y;
            this.turn = turn;
        }
    }

    static boolean isRange(int x, int y) {
        return x >= 0 && y >= 0 && x < w && y < h;
    }

    static boolean isEmptySpace(int x, int y) {
        return map[y][x] == '.';
    }

    static boolean isEdge(int x, int y) {
        return (x == 0 || x == w - 1 || y == 0 || y == h - 1);
    }

}

/**
 *
 * *.@
 */