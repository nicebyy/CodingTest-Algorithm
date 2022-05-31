import java.util.*;
import java.io.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());

        // 테스트 케이스
        while (tc-->0){
            //명령어 집합
            String[] commands = br.readLine().split("");

            // 원소들 수
            int size = Integer.parseInt(br.readLine());

            // 배열 추출
            DirectDeque<Integer> deque = getList(br);


            // 명령 수 만큼 순회
            for (String command : commands) {

                // 만약 덱에 에러가 발생했다면
                if(deque.isError) break;

                deque.executeCommand(command);
            }

            System.out.println(deque.toString());
        }
    }

    private static DirectDeque<Integer> getList(BufferedReader br) throws IOException {
        return Arrays.stream(br.readLine().replaceAll("\\D", " ").split(" "))
                .filter(e -> !e.equals("")).mapToInt(Integer::parseInt)
                .boxed().collect(Collectors.toCollection(DirectDeque::new));
    }


    static class DirectDeque<Integer> extends ArrayDeque<Integer>{
        private int direction =1;
        private boolean isError=false;

        @Override
        public String toString() {

            if(isError) return "error";

            if(this.direction==-1){
                // 역 방향일때

                ArrayList<Integer> reverseList= new ArrayList<>(this);

                Collections.reverse(reverseList);
                return reverseList.toString().replaceAll(" ","");
            }
            else // 정뱡향일때
              return new ArrayList<>(this).toString().replaceAll(" ","");
        }

        private void reverse(){
            direction = direction*-1;
        }
        private void delete(){
            if(direction==1) this.removeFirst();
            else this.removeLast();
        }

        public void executeCommand(String command){
            // 명령 수행
            if(command.equals("D") && this.isEmpty()) { // 에러를 포함한다면 에러 상태 바꾼 후 종료
                this.isError = true;
                return;
            }

            if(command.equals("D")) this.delete();
            else this.reverse();

        }
    }
}

