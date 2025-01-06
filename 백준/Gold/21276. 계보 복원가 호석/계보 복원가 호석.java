import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        Map<String, Integer> indegree = new HashMap<>();
        Map<String, ArrayList<String>> graph = new HashMap<>();
        String[] names = new String[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            String name = st.nextToken();
            indegree.put(name, 0);
            graph.put(name, new ArrayList<>());
            names[i] = name;
        }

        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            String a = st.nextToken();
            String b = st.nextToken();

            indegree.put(a, indegree.get(a)+1);
            graph.get(b).add(a);
        }

        Arrays.sort(names);

        Queue<String> q = new LinkedList<>();

        int cnt = 0;
        ArrayList<String> tmp = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            if(indegree.get(names[i]) == 0) {
                cnt++;
                q.add(names[i]);
                tmp.add(names[i]);
            }
        }

        sb.append(cnt).append("\n");
        for(String t: tmp) {
            sb.append(t).append(" ");
        }
        if(!tmp.isEmpty()) sb.append("\n");

        Map<String, ArrayList<String>> family = new HashMap<>();
        while(!q.isEmpty()) {
            String now = q.poll();
            family.put(now, new ArrayList<>());

            for(String g: graph.get(now)) {
                indegree.put(g, indegree.get(g)-1);
                if(indegree.get(g) == 0) {
                    q.add(g);
                    family.get(now).add(g);
                }
            }
        }

        for (int i = 0; i < N; i++) {
            String name = names[i];
            ArrayList<String> tlst = family.get(name);
            sb.append(name).append(" ");
            if(tlst == null) continue;
            sb.append(tlst.size()).append(" ");
            Collections.sort(tlst);
            for (String s: tlst) {
                sb.append(s).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}
