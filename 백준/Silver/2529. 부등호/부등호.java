
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n;
    static boolean[] visit;
    static String[] signs;
    static String max = "";
    static String min = "";

    public static void main(String[] args) throws Exception {
        n = Integer.parseInt(br.readLine());
        visit = new boolean[10]; // 0: min, 1:max
        signs = br.readLine().split(" ");
        int[] numbers = new int[n + 1];

        for(int i=0; i<n;i++){
            max = max+"0";
            min = min+"9";
        }

        for (int i = 0; i < 10; i++) {
            visit[i] = true;
            numbers[0] = i;
            dfs(1, numbers);
            visit[i] = false;
        }

        System.out.println(max);
        System.out.println(min);
    }

    public static void dfs(int index, int[] numbers) {

        if (index == numbers.length) {
//            System.out.println(Arrays.toString(numbers));
            String num = Arrays.stream(numbers)
                    .mapToObj(Integer::toString)
                    .collect(Collectors.joining(""));
            if(num.compareTo(min) <0){
                min = num;
            }

            if(num.compareTo(max) > 0){
                max = num;
            }
            return;
        }

        for (int i = 0; i <= 9; i++) {

            if (visit[i]) {
                continue;
            }

            if (
                    (signs[index - 1].equals(">") && numbers[index - 1] > i) ||
                    (signs[index - 1].equals("<") && numbers[index - 1] < i))
            {
                visit[i] = true;
                numbers[index] = i;
                dfs(index + 1, numbers);
                visit[i] = false;
            }
        }
    }
}

/**
 * 2
 * < >
 */

/**
 * 1
 * <
 */