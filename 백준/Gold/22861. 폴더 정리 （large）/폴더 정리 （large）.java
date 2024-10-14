import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static Map<String, HashSet<String>> folderContents = new HashMap<>();
    static Map<String, HashSet<String>> fileContents = new HashMap<>();
    static StringBuilder result = new StringBuilder();
    static Set<String> uniqueFiles;
    static int totalFiles;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N + M; i++) {
            st = new StringTokenizer(br.readLine());
            String P = st.nextToken();
            String F = st.nextToken();
            int C = Integer.parseInt(st.nextToken());

            makeDirectory(P, F, C);
        }

        int K = Integer.parseInt(br.readLine());

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            String[] fromPath = st.nextToken().split("/");
            String[] toPath = st.nextToken().split("/");

            moveFolder(fromPath[fromPath.length - 1], toPath[toPath.length - 1]);
        }

        int Q = Integer.parseInt(br.readLine());

        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            String[] queryPath = st.nextToken().split("/");

            queryFolder(queryPath[queryPath.length - 1]);
        }

        System.out.print(result.toString());
        br.close();
    }

    static void makeDirectory(String parentFolder, String name, int isFolder) {
        if (isFolder == 1) {
            folderContents.putIfAbsent(parentFolder, new HashSet<>());
            folderContents.get(parentFolder).add(name);
        } else {
            fileContents.putIfAbsent(parentFolder, new HashSet<>());
            fileContents.get(parentFolder).add(name);
        }
    }

    static void moveFolder(String fromFolder, String toFolder) {
        if (folderContents.containsKey(fromFolder)) {
            folderContents.putIfAbsent(toFolder, new HashSet<>());
            folderContents.get(toFolder).addAll(folderContents.get(fromFolder));
            folderContents.remove(fromFolder);
        }

        if (fileContents.containsKey(fromFolder)) {
            fileContents.putIfAbsent(toFolder, new HashSet<>());
            fileContents.get(toFolder).addAll(fileContents.get(fromFolder));
            fileContents.remove(fromFolder);
        }
    }

    static void queryFolder(String folder) {
        totalFiles = 0;
        uniqueFiles = new HashSet<>();
        findFiles(folder);
        result.append(uniqueFiles.size()).append(" ").append(totalFiles).append("\n");
    }

    static void findFiles(String folder) {
        if (folderContents.containsKey(folder)) {
            for (String subFolder : folderContents.get(folder)) {
                findFiles(subFolder);
            }
        }

        if (fileContents.containsKey(folder)) {
            for (String file : fileContents.get(folder)) {
                uniqueFiles.add(file);
                totalFiles++;
            }
        }
    }
}