
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import static java.util.Arrays.stream;

public class Main {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {

        int[] input = stream(br.readLine().split(" "))
                            .mapToInt(Integer::parseInt)
                            .toArray();

        int n = input[0];
        int c = input[1];
        int[] arr = new int[n];

        for(int i=0;i<n;i++){
            arr[i] = Integer.parseInt(br.readLine())-1;
        }

        Arrays.sort(arr);

        int start = 0;
        int end = arr[n-1]-arr[0]+1;

        while (start+1 < end){

            int mid = (start+end)/2;

            if(check(arr,mid,c)){ // 간격이 넓어서 좁혀야됨
                end = mid;
            }else{ // 간격이 좁아서 넓혀야됨 : start 가 mid 쪽으로 움직이기때문에 최종 lo hi 가 결정되었을때 hi-1
                start = mid;
            }
        }

        System.out.println(end-1);
    }

    public static boolean check(int[] arr, int mid,int c){

        int count = 1;
        int prev = arr[0];

        for(int i=1;i<arr.length;i++){

            if(arr[i] - prev >= mid){ // 거리조건을 만족하면
                count++; // 공유기 설치
                prev = arr[i]; // 이전 위치값 save
            }
        }

        return count<c; // 주어진 공유기보다 적게 설치한다 ? => 간격이 너무 넓음
    }
}
