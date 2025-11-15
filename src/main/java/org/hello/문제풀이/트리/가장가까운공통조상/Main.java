package org.hello.문제풀이.트리.가장가까운공통조상;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    private static int T;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        while(T-- > 0) {
            int n = Integer.parseInt(br.readLine());
            StringTokenizer st;
            List<List<Integer>> graph = new ArrayList<>();
            int[] parent = new int[n + 1];
            int[] depth = new int[n + 1];

            for(int i = 0; i <= n; i++) {
                graph.add(new ArrayList<>());
            }

            for(int i = 0; i < n - 1; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                parent[b] = a;
                graph.get(a).add(b);
            }

            // 루트 노드 찾기

            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int root = 0;
            // 트리의 루트 번호 찾기
            for(int i = 1; i <= n; i++) {
                if(parent[i] == 0) {
                    root = i;
                    break;
                }
            }

            findDepth(root, 0, depth, graph);

            int aDepth = depth[a];
            int bDepth = depth[b];

            if(aDepth < bDepth) {
                while(aDepth != bDepth) {
                    b = parent[b];
                    bDepth--;
                }
            } else if(aDepth > bDepth){
                while(aDepth > bDepth) {
                    a = parent[a];
                    aDepth--;
                }
            }

            System.out.println(findSameParent(a, b, parent));
        }
    }

    private static int findSameParent(int a, int b, int[] parent) {
        while(a != b) {
            a = parent[a];
            b = parent[b];
        }

        return a;
    }

    private static void findDepth(int cur, int d, int[] depth, List<List<Integer>> graph) {
        depth[cur] = d;
        for(int next : graph.get(cur)) {
            findDepth(next, d + 1, depth, graph);
        }
    }
}