package org.hello.문제풀이.DFS.게리맨더링;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int N;
    private static int[] person;
    private static List<Set<Integer>> graph = new ArrayList<>();
    private static boolean[] visited;
    private static int answer = Integer.MAX_VALUE;
    private static char[] color;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        person = new int[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            person[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i <= N; i++) {
            graph.add(new HashSet<>());
        }

        color = new char[N + 1];

        for (int i = 0; i < N; i++) {
            int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < arr[0]; j++) {
                int next = arr[j + 1];
                graph.get(i + 1).add(next);
                graph.get(next).add(i + 1);
            }
        }

        visited = new boolean[N + 1];
        paint(1, 1);
        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }

    private static void paint(int cur, int depth) {

        if (depth == N + 1) {
            List<Integer> partValue = new ArrayList<>();

            // Connected Component 확인
            visited = new boolean[N + 1];
            for (int i = 1; i <= N; i++) {
                if (!visited[i]) {
                    visited[i] = true;
                    partValue.add(check(i, visited, color[i]));
                }
            }

            if (partValue.size() == 2) {
                answer = Math.min(answer, Math.abs(partValue.get(0) - partValue.get(1)));
            }
        }

        for (int i = cur; i <= N; i++) { // 2^10
            color[i] = 'R';
            paint(i + 1, depth + 1);
            color[i] = 'B';
            paint(i + 1, depth + 1);
        }
    }

    private static int check(int cur, boolean[] visited, char c) {

        int total = person[cur];

        for (int next : graph.get(cur)) {
            if (!visited[next] && c == color[next]) { // 색이 같은 경우
                visited[next] = true;
                total += check(next, visited, c);
            }
        }

        return total;
    }
}