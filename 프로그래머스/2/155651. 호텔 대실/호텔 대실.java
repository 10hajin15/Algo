import java.util.*;

class Solution {
        public int solution(String[][] book_time) {
            PriorityQueue<Time> start = new PriorityQueue<>();
            PriorityQueue<Integer> end = new PriorityQueue<>();

            for (int i = 0; i < book_time.length; i++) {
                String[] book = book_time[i];

                int a = Integer.parseInt(book[0].replace(":", ""));
                int b = Integer.parseInt(book[1].replace(":", ""));

                start.add(new Time(a, b));
            }

            int answer = 0;

            while (!start.isEmpty()) {
                Time now = start.poll();

                if(!end.isEmpty()) {
                    int nxt = end.peek();
                    if(now.start >= nxt) end.poll();

                }
                int eTime = now.end + 10;
                int hour = eTime / 100;
                int minute = eTime % 100;

                if (minute >= 60) {
                    hour += 1;
                    minute -= 60;
                }

                eTime = hour * 100 + minute;
                end.add(eTime);

                answer = Math.max(answer, end.size());
            }

            return answer;
        }
    }

    class Time implements Comparable<Time> {
        int start;
        int end;
        
        Time(int start, int end) {
            this.start = start;
            this.end = end;
        }
        
        public int compareTo(Time t) {
            return Integer.compare(this.start, t.start);
        }
    }