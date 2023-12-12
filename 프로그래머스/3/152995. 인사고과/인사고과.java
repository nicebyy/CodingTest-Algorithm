
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

class Solution {
    public int solution(int[][] scores) {

        final int[] target = scores[0];

        int rank = 1;
        for(int i=1;i<scores.length;i++) {

//            boolean failCond = false;
            if(scores[i][0] + scores[i][1] > target[0] + target[1]){


                if(checkCond(scores, i)){
                    rank++;
                }
            }

            if(scores[i][0] > target[0] && scores[i][1] > target[1])
                return -1;
        }
        return rank;
    }

    private static boolean checkCond(int[][] scores,int i) {

        for(int j = 0; j< scores.length; j++) {
            if( scores[j][0]  > scores[i][0] && scores[j][1] > scores[i][1]){
                return false;
            }
        }
        return true;
    }
}