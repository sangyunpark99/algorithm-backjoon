package org.hello.문제풀이.최소스패닝트리.네트워크연결;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main { // 크루스칼

    private static int N;
    private static int M;
    private static List<int[]> edges = new ArrayList<>();
    private static int[] parent;
    private static int[] rank;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        edges = new ArrayList<>();

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            edges.add(new int[]{a,b,c});
        }

        parent = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        rank = new int[N + 1];

        edges.sort((a, b) -> { // 가중치 기반 정렬
            return a[2] - b[2];
        });

        int answer = 0;
        int edgeCnt = 0;

        for(int[] edge : edges) {
            if(union(edge[0], edge[1])) {
                answer += edge[2];
                if(++edgeCnt == N - 1) break;
            }
        }

        System.out.println(answer);
    }

    private static int find(int n) {
        if(parent[n] == n) {
            return n;
        }

        return parent[n] = find(parent[n]);
    }

    private static boolean union(int a, int b) {

        int parentA = find(a);
        int parentB = find(b);

        if(parentA == parentB) {
            return false;
        }

        if(rank[parentA] < rank[parentB]) {
            int t = parentA;
            parentA = parentB;
            parentB = t;
        }

        parent[parentB] = parentA;

        if(rank[parentA] == rank[parentB]) {
            rank[parentA]++;
        }

        return true;
    }
}