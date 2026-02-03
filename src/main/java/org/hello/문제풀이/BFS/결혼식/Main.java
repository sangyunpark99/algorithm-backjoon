package org.hello.문제풀이.BFS.결혼식;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int n;
    private static int m;

    private static List<List<Integer>> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        StringTokenizer st;

        for(int i = 0; i <=n; i++) { // O(n)
            graph.add(new ArrayList<>());
        }

        for(int i = 0; i < m; i++) { // O(m)
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        System.out.println(bfs());
    }

    private static int bfs() {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{1, 0});

        boolean[] visited = new boolean[n + 1];
        visited[1] = true;

        int answer = 0;

        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            int number = cur[0];
            int depth = cur[1];

            if(depth == 2) continue;

            for(int next : graph.get(number)) {
                if(!visited[next]) {
                    visited[next] = true;
                    answer++;
                    queue.offer(new int[]{next, depth + 1});
                }
            }
        }

        return answer;
    }
}
