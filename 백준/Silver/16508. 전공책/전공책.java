
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static String word;
    static int n, min = Integer.MAX_VALUE;
    static int[] cnt;
    static ArrayList<Book> books = new ArrayList<>();
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        word = br.readLine();
        cnt = new int[26];
        for(int i = 0; i < word.length(); i++) {
            cnt[word.charAt(i) - 'A']++;
        }
        n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            books.add(new Book(Integer.parseInt(st.nextToken()), st.nextToken()));
        }

        int[] wordCount = new int['Z'-'A'+1];
        dfs(0, 0,wordCount);
        
        if(min == Integer.MAX_VALUE) {
            min = -1;
        }
        System.out.println(min);

    }

    static void dfs(int cur, int total, int[] wordCount){
        if(cur == n) {
            if(check(wordCount)) {
                min = Math.min(min, total);
            }
            return;
        }

        Book curBook = books.get(cur);

        for(int i = 0; i < books.get(cur).title.length(); i++) {
            wordCount[curBook.title.charAt(i) - 'A']++;
        }
        dfs(cur + 1, total + curBook.price,wordCount);
        for(int i = 0; i < books.get(cur).title.length(); i++) {
            wordCount[curBook.title.charAt(i) - 'A']--;
        }
        dfs(cur + 1, total,wordCount);
    }

    static boolean check(int[] wordCount) {
        for(int i = 0; i<wordCount.length; i++) {
            if(cnt[i] > wordCount[i]) {
                return false;
            }
        }
        return true;
    }

    static class Book{
        int price;
        String title;

        Book(int price, String title) {
            this.price = price;
            this.title = title;
        }
    }
}
