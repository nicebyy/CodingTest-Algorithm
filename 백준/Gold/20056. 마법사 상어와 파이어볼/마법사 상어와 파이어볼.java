
import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.IntStream;

import static java.util.Arrays.stream;

public class Main {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static int n;
    public static int commandCount;
    public static int ballCount;

    public static void main(String[] args) throws IOException {
        int[] input = stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        n = input[0];
        ballCount = input[1];
        commandCount = input[2];

        Command.map = new LinkedList[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Command.map[i][j] = new LinkedList<>();
            }
        }
        LinkedList<Command> commandQueue = new LinkedList<>();

        for (int i = 0; i < ballCount; i++) { // ri, ci, mi, si, di
            input = stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            commandQueue.add(new Command(
                    new Point(input[1] - 1, input[0] - 1), // pos
                    input[2], // mass
                    input[3], // velocity
                    input[4] // dir
            ));
        }

        while (commandCount-- > 0) {

            while (!commandQueue.isEmpty()) {
                Command command = commandQueue.poll();
                command.executeCommand();
            }
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (!Command.map[i][j].isEmpty()) {

                        if (Command.map[i][j].size() == 1) {
                            commandQueue.add(Command.map[i][j].poll());
                        } else {

                            int massSum = 0;
                            int velocitySum = 0;
                            int size = Command.map[i][j].size();
                            int tempDirFlag = Command.map[i][j].peek().dirFlag;
                            boolean isSame = true; //true: even ,,, false: odd

                            while (!Command.map[i][j].isEmpty()) {
                                Command poll = Command.map[i][j].poll();
                                massSum += poll.mass;
                                velocitySum += poll.velocity;

                                if (isSame) {
                                    isSame = poll.dirFlag == tempDirFlag;
                                }
                            }

                            int nextMass = massSum / 5;
                            int nextVelocity = velocitySum / size;
                            int[] nextDir = isSame ? new int[]{0, 2, 4, 6} : new int[]{1, 3, 5, 7};

                            for (int dir : nextDir) {
                                if (nextMass > 0) {
                                    Command nextCommand = new Command(
                                            new Point(j, i),
                                            nextMass,
                                            nextVelocity,
                                            dir);
                                    commandQueue.add(nextCommand);
                                }
                            }
                        }
                    }
                }
            }
        }
        int result = 0;
        while (!commandQueue.isEmpty()){
            result+= commandQueue.poll().mass;
        }
        System.out.println(result);
    }

    public static class Command {

        static LinkedList<Command>[][] map;
        Point point;
        int mass;
        int velocity;
        int dirFlag;
        int dir;

        public Command(Point point, int mass, int velocity, int dir) {
            this.point = point;
            this.mass = mass;
            this.velocity = velocity;
            this.dir = dir;
            this.dirFlag = dir % 2;
        }

        public void executeCommand() {
            Point next = this.point.go(this.dir, this.velocity);
            next.x = next.x >= 0 ? next.x % n : n - Math.abs(next.x) % n;
            next.y = next.y >= 0 ? next.y % n : n - Math.abs(next.y) % n;
            next.x %= n;
            next.y %= n;
            this.point = next;
            map[next.y][next.x].add(this);
        }

        @Override
        public String toString() {
            return "Command{" +
                    "point=" + point +
                    ", mass=" + mass +
                    ", velocity=" + velocity +
                    ", dir=" + dir +
                    '}';
        }
    }

    public static class Point {
        static int[][] dir = new int[][]{{0, -1}, {1, -1}, {1, 0}, {1, 1}, {0, 1}, {-1, 1}, {-1, 0}, {-1, -1}};
        int x;
        int y;

        public Point() {
            this.x = 0;
            this.y = 0;
        }

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Point go(int nextDir, int velocity) {
            this.x += dir[nextDir][0] * velocity;
            this.y += dir[nextDir][1] * velocity;
            return this;
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






