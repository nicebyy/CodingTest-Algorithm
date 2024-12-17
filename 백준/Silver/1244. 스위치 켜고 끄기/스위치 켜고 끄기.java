
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.util.Arrays.stream;

public class Main {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {

        int n = Integer.parseInt(br.readLine());
        int[] arr = stream(("0 "+br.readLine()).split(" "))
                 .mapToInt(Integer::parseInt).toArray();
        int k = Integer.parseInt(br.readLine());
        int[][] students = new int[k][2]; // {성별, 위치}
        for(int i=0; i<k; i++){
            students[i] = stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();
        }
        // end init

        for (int[] student : students) {

            int curIndex = student[1];
            if(student[0] == 1){
                for(int i=curIndex; i<=n; i++){
                    if(i%curIndex == 0){
                        arr[i] = arr[i] == 0 ? 1 : 0;
                    }
                }
            }else { // 2
                int nextIndex = curIndex;
                int prevIndex = curIndex;
                arr[curIndex] = arr[curIndex] == 0 ? 1 : 0;
                while ( nextIndex<=n && prevIndex > 0 && arr[nextIndex] == arr[prevIndex]){
                    arr[nextIndex] = arr[nextIndex] == 0 ? 1 : 0;
                    arr[prevIndex] = arr[prevIndex] == 0 ? 1 : 0;
                    nextIndex++;
                    prevIndex--;
                }
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        for(int i=1; i<=n; i++){
            stringBuilder.append(arr[i]+" ");
            if(i % 20 == 0){
                stringBuilder.append("\n");
            }
        }
        System.out.println(stringBuilder.toString().trim());
    }
}
