import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;


public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int tc = Integer.parseInt(br.readLine());
        while (tc-->0){ br.readLine();

            long sum = 0L;

            ArrayList<Integer> listA = stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .boxed()
                    .collect(Collectors.toCollection(ArrayList::new));

            TreeSet<Integer> setB = stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .boxed()
                    .collect(Collectors.toCollection(TreeSet::new)); // 기준 Set

            for (Integer e : listA) {

                int result=e; // 기준 Set 에 이미 있는 요소이면 바로 출력

                if(!setB.contains(e)){ // 없는 요소이면 위치 찾기

                    // 추가한 요소의 앞 뒤 확인
                    setB.add(e);
                    Integer lower = setB.lower(e);
                    Integer higher = setB.higher(e);

                    if(lower == null) // 맨 앞자리일 경우
                        result = higher;
                    else if(higher==null)  // 맨 뒷자리일 경우
                        result = lower;
                    else result = Math.abs(e - lower) <= Math.abs(e - higher) ?
                                lower : higher; // lower-e-higher >> 이 형태로 있으므로 앞 뒤 차이 계산해서 결정

                    setB.remove(e); // 다시 제거
                }
                sum+=(long)result;
            }
            System.out.println(sum);
        }
    }
}

/**

 *
 */