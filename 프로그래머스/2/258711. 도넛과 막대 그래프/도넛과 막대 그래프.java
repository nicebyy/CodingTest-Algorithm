
import java.util.*;
import java.util.List;

class Solution {

    int[] inDegree = new int[1000001];
    int[] outDegree = new int[1000001];
    public int[] solution(int[][] edges) {
        int[] answer = {};
        int maxRange = 0;
        int[] result = new int[4];
        for (int[] edge : edges) {
            inDegree[edge[1]]++;
            outDegree[edge[0]]++;
            maxRange = Math.max(maxRange,Math.max(edge[1],edge[0]));
        }
        for(int i=1;i<=maxRange;i++){

            if(inDegree[i] == 0 && outDegree[i] >=2){ // start
                result[0] = i;
            }else if(inDegree[i] >= 2 && outDegree[i] == 2){ // 8
                result[3]++;
            }else if(outDegree[i] == 0){ // bar
                result[2]++;
            }
        }
        result[1] = outDegree[result[0]]-(result[2]+result[3]);
        return result;
    }
}