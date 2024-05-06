
class Solution {

    public char[][] boards = new char[3][3];
    public int solution(String[] board) {

        int oCount=0;
        int xCount=0;
        for (int i = 0; i < board.length; i++) {
            boards[i] = board[i].toCharArray();
            for(int j=0;j<boards[i].length;j++){

                if(boards[i][j] == 'O') oCount++;
                else if(boards[i][j] == 'X') xCount++;
            }
        }

        if(oCount < xCount || oCount > xCount+1){
            return 0;
        }

        if(findStream(oCount,xCount)){
            return 1;
        }
        return 0;
    }

    public boolean findStream(int oCount, int xCount){

        boolean xCheck = false;
        boolean oCheck = false;

        boolean checkCross1 = boards[0][0] == boards[1][1] && boards[1][1] == boards[2][2];
        boolean checkCross2 = boards[0][2] == boards[1][1] && boards[1][1] == boards[2][0];

        if(checkCross1 || checkCross2){
            if(boards[1][1] == 'X'){
                xCheck = true;
            }else if(boards[1][1] == 'O'){
                oCheck = true;
            }
        }

        for(int i=0;i<boards.length;i++){

            char startChar = boards[i][0];
            boolean check = true;
            for(int j=0;j<boards[i].length;j++){

                if(startChar != boards[i][j]){
                    check = false;
                }
            }

            if(check){

                if(startChar == 'X'){
                    xCheck = true;
                }else if(startChar == 'O'){
                    oCheck = true;
                }
            }
        }

        for(int i=2;i>=0;i--){

            char startChar = boards[0][i];
            boolean check = true;
            for(int j=2;j>=0;j--){

                if(startChar != boards[j][i]){
                    check = false;
                }
            }

            if(check){

                if(startChar == 'X'){
                    xCheck = true;
                }else if(startChar == 'O'){
                    oCheck = true;
                }
            }
        }

        if((xCheck == oCheck && oCheck) || (xCheck && xCount != oCount) || (oCheck && xCount == oCount)){
            return false;
        }
        return true;
    }
}