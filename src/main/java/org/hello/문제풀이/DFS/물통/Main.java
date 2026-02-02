package org.hello.문제풀이.DFS.물통;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {

    private static int A;
    private static int B;
    private static int C;

    private static int[] capacity;
    private static boolean[][] visited;
    private static TreeSet<Integer> answer = new TreeSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        capacity = new int[]{A,B,C};
        visited = new boolean[A + 1][B + 1];

        StringBuilder sb = new StringBuilder();

        dfs(new int[]{0,0,C});

        while(!answer.isEmpty()) {
            sb.append(answer.pollFirst()).append(" ");
        }

        System.out.println(sb);
    }

    private static void dfs(int[] s) {
        int a = s[0];
        int b = s[1];
        int c = s[2];

        if(visited[a][b]) return;
        visited[a][b] = true;

        if(a == 0) answer.add(c);

        for(int from = 0; from < 3; from++) { // from ~ to 모든 경우
            for(int to = 0; to < 3; to++) {
                if(from == to) continue;
                if(s[from] == 0) continue;
                if(s[to] == capacity[to]) continue;

                int[] next = s.clone();
                int water = Math.min(next[from], capacity[to] - next[to]);
                next[from] -= water;
                next[to] += water;

                dfs(next);
            }
        }
    }
}