
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

class Solution {

    public int[][] map;
    public boolean[][] visit;
    public int current = 0;
    public ArrayList<Integer> result = new ArrayList<Integer>();

    public int[] solution(String[] maps) {
        int[] answer = {};
        map = new int[maps.length][maps[0].length()];
        visit = new boolean[maps.length][maps[0].length()];

        for(int i=0;i<maps.length;i++){
            char[] charArray = maps[i].toCharArray();
            for(int j=0;j<charArray.length;j++){
                map[i][j] = charArray[j]-'0';
            }
        }

        for(int i=0;i<map.length;i++){
            for(int j=0;j<map[0].length;j++){

                if(!visit[i][j] && !isWater(map[i][j])){
                    current = 0;
                    visit[i][j] = true;
                    dfs(j,i);
                    result.add(current);
                }
            }
        }

        return result.isEmpty() ? new int[]{-1}:
                result.stream()
                .sorted()
                .mapToInt(i->i)
                .toArray();
    }

    public boolean isWater(int num){
        return num == 40;
    }

    public boolean isRange(int x,int y){
        return x>=0 && y>=0 && x<map[0].length && y<map.length
        && !isWater(map[y][x]);
    }

    public void dfs(int x,int y){

        current += map[y][x];

        int[] dx = {0,0,1,-1};
        int[] dy = {1,-1,0,0};

        for(int i=0;i<dx.length;i++){

            int nextX = dx[i] + x;
            int nextY = dy[i] + y;

            if(isRange(nextX,nextY) && !visit[nextY][nextX]){
                visit[nextY][nextX] = true;
                dfs(nextX,nextY);
            }
        }
    }
}