package org.hello.문제풀이.트리.트리의지름;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    private static int N;
    private static List<List<int[]>> graph = new ArrayList<>();
    private static int farValue = Integer.MIN_VALUE;
    private static int farNumber = 0;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        // 루트 노드에서 제일 먼 지점을 찾는다.
        // 이 지점에서 제일 먼 지점까지의 거리를 지름으로 한다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        for(int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for(int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph.get(a).add(new int[]{b, w});
            graph.get(b).add(new int[]{a, w});
        }

        visited = new boolean[N + 1];
        visited[1] = true;
        find(1,0);
        farValue = Integer.MIN_VALUE;

        visited = new boolean[N + 1];
        visited[farNumber] = true;
        find(farNumber,0);

        System.out.println(farValue);
    }

    private static void find(int cur, int weight) {

        if(farValue < weight) {
            farValue = weight;
            farNumber = cur;
        }

        for(int i = 0; i < graph.get(cur).size(); i++) {
            int next = graph.get(cur).get(i)[0];
            int nextWeight = graph.get(cur).get(i)[1];

            if(visited[next]) continue;

            visited[next] = true;
            find(next, weight + nextWeight);
        }
    }
}
