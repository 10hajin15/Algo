import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        for(int tc=1; tc<=10; tc++) {
            List<Integer> list = new LinkedList<>();

            int n = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<n; i++) {
                list.add(Integer.parseInt(st.nextToken()));
            }

            int m = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<m; i++) {
                String cmd = st.nextToken();
                if(cmd.equals("I")) {
                    int x = Integer.parseInt(st.nextToken());
                    int y = Integer.parseInt(st.nextToken());
                    for(int j=0, idx = x; j<y; j++, idx++) {
                        list.add(idx, Integer.parseInt(st.nextToken()));
                    }
                }
                else if(cmd.equals("D")) {
                    int x = Integer.parseInt(st.nextToken());
                    int y = Integer.parseInt(st.nextToken());
                    for(int j=0; j<y; j++) {
                        list.remove(x);
                    }
                }
                else if(cmd.equals("A")) {
                    int y = Integer.parseInt(st.nextToken());
                    for(int j=0; j<y; j++) {
                        list.add(Integer.parseInt(st.nextToken()));
                    }
                }

            }

            sb.append("#").append(tc).append(" ");
            for(int i=0; i<10; i++) {
                sb.append(list.get(i)).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}