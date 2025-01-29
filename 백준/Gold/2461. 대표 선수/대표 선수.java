import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<Student> students = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                long stat = Long.parseLong(st.nextToken());
                students.add(new Student(stat, i));
            }
        }

        Collections.sort(students);

        long ans = Long.MAX_VALUE;

        int s = 0;
        int[] classes = new int[N];
        for (int e=0; e<students.size();e++) {
            Student student = students.get(e);
            classes[student.idx]++;

            boolean flag = true;
            for (int i = 0; i < N; i++) {
                if(classes[i] == 0) {
                    flag = false;
                    break;
                }
            }
            if(!flag) continue;

            while(true) {
                Student minStu = students.get(s);
                ans = Math.min(ans, student.stat - minStu.stat);
                s++;
                classes[minStu.idx]--;
                if(classes[minStu.idx] == 0) break;
            }
        }

        System.out.println(ans);
    }

    static class Student implements Comparable<Student> {
        long stat;
        int idx;

        public Student(long stat, int idx) {
            this.stat = stat;
            this.idx = idx;
        }

        public int compareTo(Student s) {
            return Long.compare(this.stat, s.stat);
        }
    }
}