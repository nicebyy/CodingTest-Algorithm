import java.util.*;
import java.io.*;

class Solution {

    boolean[] visit;
    boolean[] isPrime = new boolean[3000];
    int n,ans=0;
    public int solution(int[] nums) {

        n = nums.length;
        visit = new boolean[n];
        Arrays.fill(isPrime,true);
        setPrime();

        for(int i=0;i<nums.length;i++){
            for(int j=i+1;j< nums.length;j++){
                for(int k=j+1;k< nums.length;k++){
                    if(isPrime[nums[i]+nums[j]+nums[k]])
                        ans++;
                }
            }
        }

        return ans;
    }

    void setPrime(){

        for(int i=2;i<Math.sqrt(3000);i++){

            if(!isPrime[i])
                continue;

            for(int j=i*i;j<3000;j+=i){
                isPrime[j]=false;
            }
        }
    }
}