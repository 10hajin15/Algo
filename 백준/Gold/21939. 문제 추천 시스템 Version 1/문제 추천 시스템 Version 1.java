import java.io.*;
import java.util.*;

public class Main {
    static TreeSet<Integer>[] problems;
    static TreeSet<Integer> infos;
    static Map<Integer, Integer> levels;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        problems = new TreeSet[101];
        for (int i = 1; i < 101; i++) {
            problems[i] = new TreeSet<>();
        }

        levels = new HashMap<>();
        infos = new TreeSet<>();

        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int P = Integer.parseInt(st.nextToken());
            int L = Integer.parseInt(st.nextToken());
            problems[L].add(P);
            infos.add(L);
            levels.put(P, L);
        }

        int M = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String query = st.nextToken();

            if(query.equals("add")) {
                int P = Integer.parseInt(st.nextToken());
                int L = Integer.parseInt(st.nextToken());
                problems[L].add(P);
                infos.add(L);
                levels.put(P, L);
            } else if (query.equals("solved")) {
                int nums = Integer.parseInt(st.nextToken());
                int lev = levels.get(nums);
                problems[lev].remove(nums);
                if (problems[lev].isEmpty()) {
                    infos.remove(lev);
                }
            } else {
                int x = Integer.parseInt(st.nextToken());
                if(x == -1) {
                    sb.append(problems[infos.first()].first());
                } else {
                    sb.append(problems[infos.last()].last());
                }
                sb.append("\n");
            }
        }

        System.out.println(sb.toString());
    }

}