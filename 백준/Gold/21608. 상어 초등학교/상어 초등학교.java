
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.List;

import static java.util.Arrays.stream;

public class Main {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int[][] favorites;
    static int n;
    static int[][] seats;
    static LinkedList<Integer> seq = new LinkedList<>();
    static int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {

        n = Integer.parseInt(br.readLine());
        favorites = new int[n * n + 1][n * n + 1];
        seats = new int[n + 1][n + 1];

        for (int i = 1; i <= n * n; i++) {
            int[] input = stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            seq.add(input[0]);
            for (int j = 1; j < input.length; j++) {
                favorites[input[0]][input[j]] = 1;
            }
        }

        while (!seq.isEmpty()) {

            Integer cur = seq.poll();

            int[][] score = new int[n + 1][n + 1];
            int[][] emptyScore = new int[n + 1][n + 1];
            int maxScore = 0;

            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {

                    for (int dir = 0; dir < dx.length; dir++) {
                        int nearX = j + dx[dir];
                        int nearY = i + dy[dir];

                        if (!isRange(nearX, nearY))
                            continue;

                        if (favorites[cur][seats[nearY][nearX]] == 1) {
                            score[i][j]++;
                            if (score[i][j] > maxScore && seats[i][j] == 0) {
                                maxScore = score[i][j];
                            }

                        } else if (seats[nearY][nearX] == 0) {
                            emptyScore[i][j]++;
                        }
                    }
                }
            }

            int maxEmptySeats = 0;
            int dedicatedX = 0;
            int dedicatedY = 0;

            for (int i = n; i > 0; i--) {
                for (int j = n; j > 0; j--) {

                    if (seats[i][j] != 0)
                        continue;

                    if (score[i][j] == maxScore && maxEmptySeats <= emptyScore[i][j]) {
                        dedicatedX = j;
                        dedicatedY = i;
                        maxEmptySeats = emptyScore[i][j];
                    }
                }
            }
            seats[dedicatedY][dedicatedX] = cur;
        }

        int result = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {

                int count = 0;
                for(int dir=0;dir<dx.length;dir++){

                    int nearX = j+dx[dir];
                    int nearY = i+dy[dir];

                    if(!isRange(nearX,nearY))
                        continue;

                    if(favorites[seats[i][j]][seats[nearY][nearX]] == 1){
                        count++;
                    }
                }

                if(count == 0)
                    continue;

                result += (int)Math.pow(10,count-1);
            }
        }

        System.out.println(result);
    }

    private static boolean isRange(int nearX, int nearY) {
        return nearX > 0 && nearY > 0 && nearX <= n && nearY <= n;
    }

}


