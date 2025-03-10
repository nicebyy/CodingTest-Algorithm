class Solution {
    public int solution(int[] players, int m, int k) { // m: 증설 기준 ,,, k: 시간 (duration)
        int answer = 0;

        int count = 0;
        int[] servers = new int[players.length];
        for (int i = 0; i < players.length; i++) {
            int player = players[i];

            int capacity = player / m;

            if (capacity > 0) {

                if (servers[i] < capacity) {
                    int diff = capacity - servers[i];
//                    System.out.println(diff);
                    count += diff;
                    for (int j = 0; j < k && i + j < servers.length; j++) {
                        servers[i + j] += diff;
                    }
                }
            }
        }
//        System.out.println(count);
        return count;
    }
}