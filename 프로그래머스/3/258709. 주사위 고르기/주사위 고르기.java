
import java.util.*;

class Solution {

    boolean[] visit;
    Set<int[]> diceSet = new HashSet<>();

    public int[] solution(int[][] dice) {
        int[] answer = {};
        visit = new boolean[dice.length];
        getDiceComb(0,new int[dice.length/2],0,dice);

        int maxWinCount = 0;
        for (int[] curDice : diceSet) {

            visit = new boolean[dice.length];

            for (int index : curDice) {
                visit[index] = true;
            }

            int[] otherDice = new int[dice.length/2];

            int otherDiceIndex = 0;
            for(int i=0;i< dice.length;i++){
                if(!visit[i]){
                    otherDice[otherDiceIndex] = i;
                    otherDiceIndex++;
                }
            }

            ArrayList<Integer> scoreA = new ArrayList<>();
            ArrayList<Integer> scoreB = new ArrayList<>();

            getScore(0,curDice,0,dice,scoreA);
            getScore(0,otherDice,0,dice,scoreB);

            Collections.sort(scoreA);
            Collections.sort(scoreB);
            
            int winSum = 0;

            for (Integer score : scoreA) {

                int left = 0;
                int right = scoreB.size();

                while (left+1 < right){

                    int mid = (left+right)/2;

                    if(score > scoreB.get(mid)){
                        left = mid;
                    }else{
                        right = mid;
                    }
                }

                winSum += left;
            }

            if(winSum > maxWinCount){
                maxWinCount = winSum;
                answer = curDice;
            }

        }

        for(int i=0;i<answer.length;i++){
            answer[i]++;
        }
        return answer;
    }

    public void getDiceComb(int cur,int[] diceList,int depth,int[][] dice){

        if(depth == dice.length/2){
            diceSet.add(diceList.clone());
            return;
        }

        for(int next=cur;next<dice.length;next++){

            if(!visit[next]){
                visit[next]=true;
                diceList[depth] = next;
                getDiceComb(next,diceList,depth+1,dice);
                visit[next]=false;
            }
        }
    }

    public void getScore(int curScore,int[] diceList,int depth,int[][] dice,List<Integer> diceScore){

        if(depth == diceList.length){
            diceScore.add(curScore);
            return;
        }

        for(int i=0;i<dice[0].length;i++){
            getScore(curScore+dice[diceList[depth]][i],diceList,depth+1,dice,diceScore);
        }
    }
}

/**
 * 완전탐색문제;
 * 주사위 조합 .. nCn/2
 * 각 주사위에서 나올 수 있는 모든 경우의 수 합 6^(n/2)
 */