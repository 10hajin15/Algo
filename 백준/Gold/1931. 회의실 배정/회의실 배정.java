import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        List<Meeting> meetings = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long start = Long.parseLong(st.nextToken());
            long end = Long.parseLong(st.nextToken());
            meetings.add(new Meeting(start, end));
        }

        Collections.sort(meetings, (a, b) -> {
            if (a.end == b.end) return Long.compare(a.start, b.start);
            return Long.compare(a.end, b.end);
        });

        int count = 0;
        long lastEnd = 0;

        for (Meeting m : meetings) {
            if (m.start >= lastEnd) {
                count++;
                lastEnd = m.end;
            }
        }

        System.out.println(count);
    }

    static class Meeting {
        long start, end;

        Meeting(long start, long end) {
            this.start = start;
            this.end = end;
        }
    }
}