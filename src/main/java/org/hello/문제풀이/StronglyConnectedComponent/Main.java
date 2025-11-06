package org.hello.문제풀이.StronglyConnectedComponent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int V;
    private static int E;

    private static List<List<Integer>> graph = new ArrayList<>(); // 방향 그래프

    private static int idSeq = 0;
    private static int[] id; // 각 노드의 방문 ID
    private static int[] low; // 각 노드의 low-link 값 (자신 혹은 하위 트리에서 가장 작은 ID)
    private static boolean[] onStack; // 현재 스택에 존재하는지 여부 체크
    private static Deque<Integer> stack = new ArrayDeque<>(); // DFS 탐색 경로 스택
    private static List<List<Integer>> sccList = new ArrayList<>(); // SCC(강연결요소) 결과 저장 리스트

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        for(int i = 0; i <= V; i++) {
            graph.add(new ArrayList<>());
        }

        for(int i = 0; i < E; i++) {
            st = new  StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph.get(a).add(b); // a -> b
        }

        id = new int[V + 1];
        low = new int[V + 1];
        onStack = new boolean[V + 1];

        // 모든 정점에 대해 DFS - 방문하지 않은 노드만 탐색
        for(int v = 1; v <= V; v++) {
            if(id[v] == 0) dfs(v);
        }

        StringBuilder sb = new StringBuilder();
        sb.append(sccList.size()).append("\n");


        for(List<Integer> comp: sccList) Collections.sort(comp);
        sccList.sort((a,b) -> {
            return a.get(0) - b.get(0);
        });

        for(List<Integer> comp: sccList) {
            for(int v: comp) sb.append(v).append(" ");
            sb.append("-1").append("\n");
        }

        System.out.println(sb);
    }

    private static void dfs(int v) {
        // 현재 방문 노드 처리
        id[v]= low[v] = ++idSeq; // id와 low초기화
        stack.push(v); // 스택에 현재 노드 추가
        onStack[v] = true; // 스택에 추가된 노드 true

        // 인접 노드 순회
        for(int w : graph.get(v)) { // 다음 그래프
            if(id[w] == 0) { // 아직 방문하지 않은 노드 -> 재귀 탐색
                dfs(w);
                // 하위 노드를 통해 더 작은 id로 갈 수 있는 경우 low값 갱신
                low[v] = Math.min(low[v], low[w]);
            } else if (onStack[w]) { // 이미 방문했고 스택에 존재한다면 -> 역방향 간선
                // 즉, SCC 내부에서 되돌아갈 수 있는 경로가 존재
                low[v] = Math.min(low[v], id[w]);
            }
        }

        // 자신(v)의 low == id 이면, 자신이 하나의 SCC 루트임
        if(low[v] == id[v]) {
            List<Integer> comp = new ArrayList<>();

            // 스택에서 자신(v) 까지의 모든 노드를 꺼내서 하나의 SCC로 구성
            while(true) {
                int x = stack.pop();
                onStack[x] = false;
                comp.add(x);
                if (x == v) break;
            }
            sccList.add(comp);
        }
    }
}
