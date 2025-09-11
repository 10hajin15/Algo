import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int M, t;
    static int time;
    static String dock;
    static int[] answer;
    static Queue<Person> left;
    static Queue<Person> right;

    static void move(String dir) {
        Queue<Person> q = new LinkedList<>();

        if(dir.equals("left")) {
            while (!left.isEmpty() && left.peek().time <= time && q.size() < M) {
                q.add(left.poll());
            }
        } else {
            while (!right.isEmpty() && right.peek().time <= time && q.size() < M) {
                q.add(right.poll());
            }
        }

        time += t;
        while (!q.isEmpty()) {
            Person p = q.poll();
            answer[p.idx] = time;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        left = new LinkedList<>();
        right = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            String dock = st.nextToken();
            if (dock.equals("left")) {
                left.add(new Person(i, time, dock));
            } else {
                right.add(new Person(i, time, dock));
            }
        }

        answer = new int[N];
        time = 0;
        dock = "left";

        while (!left.isEmpty() || !right.isEmpty()) {
            int nextLeftTime = left.isEmpty() ? Integer.MAX_VALUE : left.peek().time;
            int nextRightTime = right.isEmpty() ? Integer.MAX_VALUE : right.peek().time;

            if (dock.equals("left") && nextLeftTime <= time) {
                move("left");

                dock = "right";
            } else if (dock.equals("right") && nextRightTime <= time) {
                move("right");

                dock = "left";
            } else {
                if (nextLeftTime < nextRightTime) {
                    time = Math.max(time, nextLeftTime);
                    if (!dock.equals("left")) {
                        time += t;
                    }
                    move("left");

                    dock = "right";
                } else {
                    time = Math.max(time, nextRightTime);
                    if (!dock.equals("right")) {
                        time += t;
                    }
                    move("right");

                    dock = "left";
                }
            }


        }

        for (int i = 0; i < N; i++) {
            System.out.println(answer[i]);
        }
    }

    static class Person {
        int idx, time;
        String dock;

        Person(int idx, int time, String dock) {
            this.idx = idx;
            this.time = time;
            this.dock = dock;
        }
    }
}