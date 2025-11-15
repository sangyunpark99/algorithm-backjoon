package org.hello.문제풀이.트리.트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    private static List<List<Integer>> graph = new ArrayList<>();
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int t = 1;
        for(int i = 1; ; i++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            if (N == 0 && M == 0) break;

            graph = new ArrayList<>();
            for (int k = 0; k <= N; k++) {
                graph.add(new ArrayList<>());
            }

            for (int j = 0; j < M; j++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                graph.get(a).add(b);
                graph.get(b).add(a);
            }

            visited = new boolean[N + 1];
            int cnt = 0;

            for (int s = 1; s <= N; s++) {
                if (!visited[s]) {
                    if (dfs(s, 0)) cnt++;
                }
            }

            System.out.printf("Case %d: ", i);
            if (cnt == 0) System.out.println("No trees.");
            else if (cnt == 1) System.out.println("There is one tree.");
            else System.out.printf("A forest of %d trees.\n", cnt);
        }
    }

    private static boolean dfs(int node, int parent) {
        visited[node] = true;
        for(int next : graph.get(node)) {
            if(!visited[next]) {
                if(!dfs(next, node)) return false;
            }else if(next != parent) { // 이미 방문한 노드 중 부모를 제외한 노드를 만난 경우
                return false;
            }
        }

        return true;
    }
}