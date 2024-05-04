import java.util.PriorityQueue;

class Solution {
    public int solution(int n, int k, int[] enemies) {

        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2-o1);
        int defendedTotalEnemy = 0;
        int round = 0;
        for (int i = 0; i < enemies.length; i++) {
            int enemy = enemies[i];

            defendedTotalEnemy += enemy;
            pq.add(enemy);

            if (defendedTotalEnemy <= n ) {
                round++;
            }else if(k>0){
                round++;
                k--;
                defendedTotalEnemy-=pq.poll();
            }else{
                return round;
            }
        }
        return enemies.length;
    }
}


