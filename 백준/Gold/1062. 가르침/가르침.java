import java.util.*;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.*;

import static java.util.Arrays.stream;

public class Main {


    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n,k;
    static int ans=0;
    static String[] words;
    static boolean[] visit = new boolean[26];
    public static void main(String[] args) throws IOException {
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        k = Integer.parseInt(input[1]);

        visit['a'-'a']=true;
        visit['n'-'a']=true;
        visit['t'-'a']=true;
        visit['i'-'a']=true;
        visit['c'-'a']=true;

        if(k<5){
            System.out.println(ans);
            return;
        }
        words = new String[n];
        k = k-5;
        Pattern pattern = Pattern.compile("anta(.*)tica");
        for(int i=0;i<n;i++){
            Matcher matcher = pattern.matcher(br.readLine());
            if(matcher.find())
                words[i] = matcher.group(1);
        }

        dfs(0,0);
        System.out.println(ans);
    }

    static void dfs(int cur,int len){

        if(len == k){
            int cnt = 0;

            for(int i=0;i<n;i++){
                boolean read = true;

                for(int j = 0; j< words[i].length(); j++){

                    if (!visit[words[i].charAt(j) - 'a']) {
                        read = false;
                        break;
                    }
                }
                if(read)
                    cnt++;
            }

            ans = Math.max(ans,cnt);
            return;
        }

        for(int i=cur;i<26;i++){
            if(!visit[i]){
                visit[i]=true;
                dfs(i,len+1);
                visit[i]=false;
            }
        }
    }
}