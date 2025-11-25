package org.hello.문제풀이.DFS.깊이우선탐색2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    private static int N;
    private static int M;
    private static int R;
    private static List<List<Integer>> graph = new ArrayList<>();
    private static int[] sequence;
    private static int order = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        for(int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        sequence = new int[N + 1];

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        for(int i = 1; i <= N; i++) {
            graph.get(i).sort((a,b) -> b - a);
        }

        dfs(new boolean[N + 1], R);

        for(int i = 1; i <= N; i++) {
            if(sequence[i] > 0) {
                sb.append(sequence[i]).append("\n");
            }else {
                sb.append(0).append("\n");
            }
        }

        System.out.println(sb);
    }

    private static void dfs(boolean[] visited, int cur) {
        visited[cur] = true;
        sequence[cur] = order++;

        for(int next: graph.get(cur)) {
            if(!visited[next]) {
                dfs(visited, next);
            }
        }
    }
}