
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.util.Arrays.stream;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int n;
    static int row, col;
    static int cnt = 0;

    public static void main(String[] args) throws IOException {

        int[] input = stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        n = input[0];
        row = input[1];
        col = input[2];
        int size = (int) Math.pow(2, n);
        go(row, col, size);
        System.out.println(cnt);
    }

    public static void go(int x, int y, int size) { // ex) 1 6 8 , -3 6 4
        if (size == 1) {
            return;
        }

        int nextSize = size / 2; // 4 2

        if (x < nextSize && y < nextSize) {
            go(x, y, nextSize);
        } else if (x < nextSize) {
            cnt += size*size/4;
            go(x, y-nextSize, nextSize);
        } else if(y < nextSize){
            cnt += 2*(size*size/4);
            go(x-nextSize, y, nextSize); // go(-3,6, 4)
        }else{
            cnt += 3*(size*size/4);
            go(x-nextSize, y-nextSize, nextSize);
        }
    }
}

/**
 * 3 4 4
 *
 * 0 0 0 0 0 0 0 0
 * 0 0 0 0 0 0 0 0
 * 0 0 0 0 0 0 0 0
 * 0 0 0 0 0 0 0 0
 * 0 0 0 0 0 0 0 0
 * 0 0 0 0 0 0 0 0
 * 0 1 0 0 0 0 0 0
 * 0 0 0 0 0 0 0 0
 */