
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static String[][] map;
    static int[][][] dp;
    static int[] dx = {1,0};
    static int[] dy = {0,1};
    static int n;

    public static void main(String[] args) throws IOException {

        n = Integer.parseInt(br.readLine());
        map = new String[n][n];
        for(int i=0;i<n;i++){
            map[i] = br.readLine().split(" ");
        }

        dp = new int[n][n][2];

        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                dp[i][j][0] = Integer.MAX_VALUE;
            }
        }

        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                dp[i][j][1] = Integer.MIN_VALUE;
            }
        }


        dfs(new Point(0,0),Integer.parseInt(map[0][0]));

        System.out.println(dp[n-1][n-1][1]+" "+dp[n-1][n-1][0]);
    }

    private static void dfs(Point cur, int curValue) {

        dp[cur.y][cur.x] = new int[]{
                Math.min(curValue,dp[cur.y][cur.x][0]),
                Math.max(curValue,dp[cur.y][cur.x][1])
        };

         for(int i=0;i<dx.length;i++){
             int operatorX = cur.x + dx[i];
             int operatorY = cur.y + dy[i];

             if(!isRange(operatorX,operatorY)){
                 continue;
             }

             String operator = map[operatorY][operatorX];

             for(int j=0; j<dx.length;j++){

                 int operandX = operatorX + dx[j];
                 int operandY = operatorY + dy[j];

                 if(!isRange(operandX,operandY)){
                     continue;
                 }

                 int operand = Integer.parseInt(map[operandY][operandX]);

                 int nextValue = operate(operator, curValue, operand);

                 dfs(new Point(operandX,operandY),nextValue);
             }
         }
    }

    static int operate(String operator,int curValue,int operand){

        if(operator.equals("+")){
            return curValue+operand;
        }else if(operator.equals("-")){
            return curValue-operand;
        }else{
            return curValue*operand;
        }
    }

    static boolean isRange(int x,int y){
        return x>=0 && y>=0 && x<n&&y<n;
    }
    static class Point{
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
