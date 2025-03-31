
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.IntStream;

import static java.lang.System.*;
import static java.util.Arrays.stream;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(in));

    public static void main(String[] args) throws IOException {

        int n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());

        int[] arr = stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .sorted()
                .toArray();
        int result = IntStream.range(1, n)
                .map(index -> arr[index] - arr[index - 1])
                .boxed()
                .sorted((o1, o2) -> o2 - o1)
                .skip(k - 1)
                .reduce(Integer::sum)
                .orElse(0);
        out.println(result);
    }
}