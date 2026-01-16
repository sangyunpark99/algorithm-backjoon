package org.hello.문제풀이.트리.LCA2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int N;
    private static int M;
    private static int LOG;

    private static List<List<Integer>> graph = new ArrayList<>();
    private static int[] depth;
    private static int[][] up;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        for(int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for(int i = 0; i < N - 1; i++) { // O(100,000)
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        LOG = 0;

        while((1 << LOG) <= N) LOG++;

        up = new int[LOG][N + 1];
        depth = new int[N + 1];

        bfs(1);
        fillUp();

        M = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            sb.append(lca(a,b)).append("\n");
        }

        System.out.println(sb);
    }

    private static void bfs(int root) {
        boolean[] visited = new boolean[N + 1];
        Queue<Integer> queue = new ArrayDeque<>();

        queue.add(root);
        visited[root] = true;
        up[0][root] = 0;
        depth[root] = 0;

        while(!queue.isEmpty()) {
            int cur = queue.poll();
            for(int next : graph.get(cur)) {
                if(!visited[next]) {
                    visited[next] = true;
                    up[0][next] = cur;
                    depth[next] = depth[cur] + 1;
                    queue.add(next);
                }
            }
        }
    }

    private static void fillUp() {
        for(int k = 1; k < LOG; k++) {
            for(int v = 1; v <= N; v++) {
                int mid = up[k - 1][v];
                up[k][v] = (mid == 0) ? 0 : up[k - 1][mid];
            }
        }
    }

    private static int lca(int a, int b) {
        if(depth[a] < depth[b]) {
            int t = a;
            a = b;
            b = t;
        }

        int diff = depth[a] - depth[b];

        for(int k = 0; k < LOG; k++) {
            if(((diff >> k) & 1) == 1) a = up[k][a];
        }

        if(a == b) return a;

        for(int k = LOG - 1; k >= 0; k--) {
            if(up[k][a] != up[k][b]) {
                a = up[k][a];
                b = up[k][b];
            }
        }

        return up[0][a];
    }
}
