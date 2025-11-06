package org.hello.기본기.DFS;

import java.util.ArrayList;
import java.util.List;

public class Main {

    private static List<List<Integer>> graph = new ArrayList<>();
    private static int n = 5;
    private static boolean[] visited = new boolean[n + 1];

    public static void main(String[] args) {

        for(int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        graph.get(1).add(2);
        graph.get(2).add(4);
        graph.get(2).add(5);
        graph.get(1).add(3);

        dfs(1);
    }

    private static void dfs(int v) {

        visited[v] = true;
        System.out.println(v); // 방문 직후 출력(전위 순회)
        for(int w : graph.get(v)) {
            if(!visited[w]) {
                dfs(w);
            }
        }

        System.out.println(v + "로부터 시작된 함수가 종료되었습니다."); // 모든 자식 방문 후 출력(후위 순회)
    }
}
