
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int n;
    public static void main(String[] args) throws IOException {

        n = Integer.parseInt(br.readLine());
        Node root = new Node();
        for(int i=0; i<n; i++){
            ArrayDeque<String> list = Arrays.stream(br.readLine().split("\\\\"))
                    .collect(Collectors.toCollection(ArrayDeque::new));
            root.addFile(list);
        }

        root.print("");
    }

    static class Node{

        TreeMap<String,Node> map;

        public Node() {
            this.map = new TreeMap<>();
        }

        public void addFile(ArrayDeque<String> dir){

            if(dir.isEmpty()){
                return;
            }
            String first = dir.removeFirst();
            this.map.putIfAbsent(first, new Node());
            this.map.get(first).addFile(dir);
        }

        public void print(String prefix){
            for (var entry : map.entrySet()) {
                System.out.println(prefix+entry.getKey());
                entry.getValue().print(prefix+" ");
            }
        }
    }
}

/**
 * 7
 * WINNT\SYSTEM32\CONFIG
 * GAMES
 * WINNT\DRIVERS
 * HOME
 * WIN\SOFT
 * GAMES\DRIVERS
 * WINNT\SYSTEM32\CERTSRV\CERTCO~1\X86
 */