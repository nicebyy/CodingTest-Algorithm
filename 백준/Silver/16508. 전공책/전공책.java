import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static String word;
    static int N, min = Integer.MAX_VALUE;
    static int[] cnt, selectCount = new int[26];
    static ArrayList<Book> books = new ArrayList<>();
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        word = br.readLine();
        cnt = new int[26];
        for(int i = 0; i < word.length(); i++) {
            cnt[word.charAt(i) - 'A']++;
        }
        N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            books.add(new Book(Integer.parseInt(st.nextToken()), st.nextToken()));
        }

        dfs(0, 0);
        if(min == Integer.MAX_VALUE) min = -1;
        System.out.println(min);

    }

    static void dfs(int idx, int total){
        if(idx == N) {
            if(check()) {
                min = Math.min(min, total);
            }
            return;
        }
        for(int i = 0; i < books.get(idx).title.length(); i++) {
            selectCount[books.get(idx).title.charAt(i) - 'A']++;
        }
        dfs(idx + 1, total + books.get(idx).price);
        for(int i = 0; i < books.get(idx).title.length(); i++) {
            selectCount[books.get(idx).title.charAt(i) - 'A']--;
        }
        dfs(idx + 1, total);
    }

    static boolean check() {
        for(int i = 0; i < 26; i++) {
            if(cnt[i] > selectCount[i]) {
                return false;
            }
        }
        return true;
    }
}

class Book{
    int price;
    String title;

    Book(int price, String title) {
        this.price = price;
        this.title = title;
    }
}