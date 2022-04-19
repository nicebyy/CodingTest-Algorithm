import static java.util.Arrays.*;

import java.io.*;

public class Main {

    static int[][] map;
    static int h,w,b;
    static int fromHeight=Integer.MAX_VALUE, toHeight=0;
    static int minTime=Integer.MAX_VALUE,maxHeight;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] input = stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
        h = input[0];w=input[1];b=input[2];
        map = new int[h][w];
        for(int y=0;y<h;y++){
            map[y] = stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();
            fromHeight = Math.min(stream(map[y]).min().getAsInt(), fromHeight);
            toHeight = Math.max(stream(map[y]).max().getAsInt(), toHeight);
        }

        for(int height = fromHeight; height<= toHeight; height++){

            int time=0;
            int inventory = b;

            for(int y=0;y<h;y++){
                for(int x=0;x<w;x++){

                    if(time>minTime)
                        continue;

                    int diff = map[y][x]-height;
                    if(diff>0){
                        time+=diff*2;
                        inventory += diff;
                    }else if(diff<0){
                        time-=diff;
                        inventory +=diff;
                    }
                }
            }
            if(inventory<0)
                continue;

            if(time==minTime){
                maxHeight = Math.max(maxHeight,height);
            }else if(time<minTime){
                maxHeight = height;
                minTime = time;
            }
        }

        System.out.println(minTime+" "+maxHeight);
    }
}

