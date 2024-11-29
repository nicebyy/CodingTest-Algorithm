
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

class Solution {
    public int solution(String[][] book_time) {
        int answer = 0;

        PriorityQueue<Book> bookQueue = new PriorityQueue<>((o1, o2) -> o1.start.compareTo(o2.start));

        Arrays.stream(book_time)
                .map(Book::createBook)
                .forEach(bookQueue::add);
        PriorityQueue<Book> onGoingQueue = new PriorityQueue<>((o1, o2) -> o1.end.compareTo(o2.end));

        int count = 0;

        while (!bookQueue.isEmpty()) {
            Book curBook = bookQueue.poll();
            System.out.println(curBook);

            onGoingQueue.add(curBook);
            count++;

            while (!onGoingQueue.isEmpty() && (onGoingQueue.peek().end.isBefore(curBook.start) || onGoingQueue.peek().end.equals(curBook.start))) {
                onGoingQueue.poll();
                count--;
            }
            answer = Math.max(answer, count);
        }
//        System.out.println(count);
        return answer;
    }

    /**
     * count
     * 3
     *
     * cur
     * Book{start=14:10, end=19:30}
     *
     * ongoing
     * Book{start=15:00, end=17:10} Book{start=16:40, end=18:30}
     */

    static class Book{

        static LocalTime endLimit = LocalTime.of(23,59);
        LocalTime start;
        LocalTime end;

        @Override
        public String toString() {
            return "Book{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }

        public Book(String start, String end) {
            DateTimeFormatter formatter = bookTimeFormatter();
            this.start = LocalTime.parse(start, formatter);
            this.end = LocalTime.parse(end, formatter);

            if(this.end.isAfter(endLimit.minusMinutes(10))){
                this.end = endLimit;
            }else{
                this.end = this.end.plusMinutes(10);
            }
        }

        public static DateTimeFormatter bookTimeFormatter() {
            return DateTimeFormatter.ofPattern("HH:mm");
        }

        public static Book createBook(String[] input) {
            return new Book(input[0], input[1]);
        }
    }
}
