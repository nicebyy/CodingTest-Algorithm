
import java.awt.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Solution {

    ArrayList<String> formatList;
    ArrayList<String> printList;
    String[][] cellMap = new String[51][51];
    Point[][] parent = new Point[51][51];
    public String[] solution(String[] commands) {

        init();
        for (String cmd : commands) {
            for (String format : formatList) {

                Pattern compile = Pattern.compile(format);
                Matcher matcher = compile.matcher(cmd);
                if(!matcher.find())
                    continue;

                String command = matcher.group(1);

                if(command.equals("UPDATE") && matcher.groupCount() == 4){ // update
                    updateCell(matcher);
                }else if(command.equals("UPDATE") && matcher.groupCount() == 3){ //replaceAll
                    replaceAll(matcher);
                }else if(command.equals("MERGE")){
                    mergeCell(matcher);
                }else if(command.equals("UNMERGE")){
                    unmergeCell(matcher);
                }else if(command.equals("PRINT")){
                    printCell(matcher);
                }
            }
        }
        return printList.toArray(new String[0]);
    }

    private void unmergeCell(Matcher matcher) {

        int h = Integer.parseInt(matcher.group(2));
        int w = Integer.parseInt(matcher.group(3));

        int unmergeX = parent[h][w].x;
        int unmergeY = parent[h][w].y;

        for(int y=1;y<=50;y++){
            for(int x=1;x<=50;x++){

                
                if(parent[y][x].x == unmergeX && parent[y][x].y == unmergeY){
                    parent[y][x] = new Point(x,y);

                    if(y == h && x == w)
                        continue;

                    cellMap[y][x] = null;
                }
            }
        }

    }

    private void replaceAll(Matcher matcher) {
        String oldValue = matcher.group(2);
        String newValue = matcher.group(3);

        for(int y=1;y<=50;y++){
            for(int x=1;x<=50;x++){

                if(!hasText(cellMap[y][x]))
                    continue;

                if(cellMap[y][x].equals(oldValue)){
                    cellMap[y][x] = newValue;
                }
            }
        }
    }
    private void updateCell(Matcher matcher) {

        int h = Integer.parseInt(matcher.group(2));
        int w = Integer.parseInt(matcher.group(3));
        String target = matcher.group(4);
        cellMap[h][w] = target;
        
        for(int y=1;y<=50;y++){
            for(int x=1;x<=50;x++){
                find(x, y);

                if(parent[y][x].y == parent[h][w].y && parent[y][x].x == parent[h][w].x){
                    cellMap[y][x] = target;
                }
            }
        }
    }

    private void mergeCell(Matcher matcher){

        int h1 = Integer.parseInt(matcher.group(2));
        int w1 = Integer.parseInt(matcher.group(3));
        String cell1= cellMap[h1][w1];

        int h2 = Integer.parseInt(matcher.group(4));
        int w2 = Integer.parseInt(matcher.group(5));
        String cell2= cellMap[h2][w2];

        if(h1 == h2 && w1 == w2)
            return;
        Point parentPoint = null;
        Point target = null;
        String changeCell = null;

        if(hasText(cell1) || !hasText(cell1) && !hasText(cell2)){

            parentPoint = find(w1,h1);
            target = find(w2, h2);
            changeCell = cell1;

        }else if(!hasText(cell1) && hasText(cell2)){

            parentPoint = find(w2, h2);
            target = find(w1,h1);
            changeCell = cell2;
        }

        for(int y=1;y<=50;y++){
            for(int x=1;x<=50;x++){
                if(find(parent[y][x].x,parent[y][x].y).isSameParent(target)){
                    parent[y][x].x = parentPoint.x;
                    parent[y][x].y = parentPoint.y;
                    cellMap[y][x] = changeCell;
                }
            }
        }
    }

    private void printCell(Matcher matcher){

        int h = Integer.parseInt(matcher.group(2));
        int w = Integer.parseInt(matcher.group(3));

        String target = "EMPTY";
        if(hasText(cellMap[h][w]))
            target = cellMap[h][w];

        printList.add(target);
    }

    public void init(){
        formatList = new ArrayList<>();
        formatList.add("^(UPDATE) ([0-9]*) ([0-9]*) ([a-zA-Z0-9]*)$");
        formatList.add("^(UPDATE) ([a-z0-9]*) ([a-zA-Z0-9]*)$");
        formatList.add("^(MERGE) ([0-9]*) ([0-9]*) ([0-9]*) ([0-9]*)$");
        formatList.add("^(UNMERGE) ([0-9]*) ([0-9]*)$");
        formatList.add("^(PRINT) ([0-9]*) ([0-9]*)$");

        printList = new ArrayList<>();

        for(int y=0;y<=50;y++){
            for(int x=0;x<=50;x++){
                parent[y][x] = new Point(x,y);
            }
        }
    }
    public Point find(int x,int y){

        boolean isRoot = parent[y][x].x == x && parent[y][x].y == y;

        if(isRoot){
            return parent[y][x];
        }

        parent[y][x] = find(parent[y][x].x,parent[y][x].y);
        return parent[y][x];
    }

    public boolean hasText(String text){
        return text != null && !text.equals("");
    }

    static class Point{
        int x;
        int y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void changeParent(Point p){
            this.x = p.x;
            this.y = p.y;
        }

        public boolean isSameParent(Point point){
            return this.x == point.x && this.y==point.y;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
}