import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


class Solution {
    public int[][] solution(int[][] data, String ext, int val_ext, String sort_by) {

        MyData.setSortCriteria(sort_by);
        MyData.setGlobalCriteria(ext,val_ext);

        List<int[]> resultList = Arrays.stream(data)
                .map(MyData::new)
                .filter(MyData::checkGlobalCriteria)
                .sorted(MyData::compareTo)
                .map(MyData::toArray)
                .collect(Collectors.toList());

        return resultList.toArray(new int[resultList.size()][data[0].length]);
    }
}

class MyData implements Comparable<MyData>{
    @Override
    public String toString() {
        return "MyData{" +
                "code=" + code +
                ", date=" + date +
                ", maximum=" + maximum +
                ", remain=" + remain +
                '}';
    }

    private static String sortCriteria;
    private static String globalCriteria;
    private static int globalCriteriaValue;

    int code;
    int date;
    int maximum;
    int remain;

    public MyData(int[] input) {
        this.code = input[0];
        this.date = input[1];
        this.maximum = input[2];
        this.remain = input[3];
    }
    public static void setSortCriteria(String sort_by) {
        sortCriteria = sort_by;
    }

    public static void setGlobalCriteria(String ext,int value){
        globalCriteria = ext;
        globalCriteriaValue = value;
    }

    public boolean checkGlobalCriteria(){

        if(globalCriteria.equals("code")){
            return this.code < globalCriteriaValue;
        }else if(globalCriteria.equals("date")){
            return this.date < globalCriteriaValue;
        }else if(globalCriteria.equals("maximum")){
            return this.maximum < globalCriteriaValue;
        }else{
            return this.remain < globalCriteriaValue;
        }
    }

    public int[] toArray(){

        int[] array = new int[4];
        array[0] = this.code;
        array[1] = this.date;
        array[2] = this.maximum;
        array[3] = this.remain;
        return array;
    }

    @Override
    public int compareTo(MyData o) {

        if(sortCriteria.equals("code")){
            return this.code - o.code;
        }else if(sortCriteria.equals("date")){
            return this.date - o.date;
        }else if(sortCriteria.equals("maximum")){
            return this.maximum - o.maximum;
        }else{
            return this.remain - o.remain;
        }
    }
}