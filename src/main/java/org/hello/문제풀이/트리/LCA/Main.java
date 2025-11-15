package org.hello.문제풀이.트리.LCA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    private static int N;
    private static int M;
    private static List<List<Integer>> graph = new ArrayList<>();
    private static int[] depth; // 깊이 배열
    private static int[] parents; // 부모 노드 배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        for(int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        // 두 정점의 가장 가까운 공통 조상 -> 각 정점에서 해당 점점까지의 거리를 구하고,
        // N이 50,000개 라면..
        for(int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        M = Integer.parseInt(br.readLine());

        depth = new int[N + 1];
        parents = new int[N + 1];

        dfs(1, 0, 0);

        System.out.println(Arrays.toString(depth));
        System.out.println(Arrays.toString(parents));

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            int aDepth = depth[a];
            int bDepth = depth[b];

            if(aDepth == bDepth) { // 같은 경우, 추적 -> 부모가 같은지
                System.out.println(findSameParent(a, b));
            }else if(aDepth < bDepth) { // 깊이가 더 작은 쪽에 맞춘다
                int gap = bDepth - aDepth; //
                while(gap > 0) {
                    b = parents[b];
                    gap--;
                }
                System.out.println(findSameParent(a,b));
            }else { // 그렇지 않은 경우
                int gap = aDepth - bDepth; //
                while(gap > 0) {
                    a = parents[a];
                    gap--;
                }
                System.out.println(findSameParent(a,b));
            }
        }
    }

    private static int findSameParent(int a, int b) {
        if(a == b) {
            return a;
        }

        while(a != b) { // 두 노드가 같아지는 순간 -> 공통 부모
            a = parents[a];
            b = parents[b];
        }

        return a;
    }

    private static void dfs(int node, int parent, int d) {
        depth[node] = d;
        parents[node] = parent;

        for(int next : graph.get(node)) {
            if(next != parent) {
                dfs(next, node, d + 1);
            }
        }
    }
}