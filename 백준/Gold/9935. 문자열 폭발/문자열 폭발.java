import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        String bomb = br.readLine();
        int bombLen = bomb.length();

        Stack<Character> stack = new Stack<>();

        for (char c : str.toCharArray()) {
            stack.push(c);

            if(stack.size() >= bombLen) {
                boolean isBomb = true;

                for (int i = 0; i < bombLen; i++) {
                    if(stack.get(stack.size() - bombLen + i) != bomb.charAt(i)) {
                        isBomb = false;
                        break;
                    }
                }

                if(isBomb) {
                    for (int i = 0; i < bombLen; i++) {
                        stack.pop();
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (char c: stack) {
            sb.append(c);
        }

        System.out.println(sb.length() == 0 ? "FRULA" : sb);
    }
}