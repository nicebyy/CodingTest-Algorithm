import java.util.*;
import java.io.*;
import java.util.stream.Collectors;

class Solution {
    public int solution(int[][] sizes) {
        int maxH=0;
        int maxW=0;
        for(int i=0;i<sizes.length;i++){
            maxH = Math.max(Math.min(sizes[i][0],sizes[i][1]),maxH);
            maxW = Math.max(Math.max(sizes[i][0],sizes[i][1]),maxW);
        }
        return maxH*maxW;
    }
}