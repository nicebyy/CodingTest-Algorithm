import java.util.*;
import java.io.*;

class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {

        String[] answer = new String[n];

        for(int i=0;i<n;i++){
            String bin = Integer.toBinaryString(arr1[i] | arr2[i]);

            int diff = n - bin.length();
            if(diff>0){
                for(int j=0;j<diff;j++){
                    bin = "0"+bin;
                }
            }
            answer[i] = bin
                    .replaceAll("1","#")
                    .replaceAll("0"," ");
        }
        return answer;
    }
}