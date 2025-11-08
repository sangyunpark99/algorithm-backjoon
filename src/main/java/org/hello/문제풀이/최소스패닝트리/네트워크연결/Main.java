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
            edges.add(new int[]{b,a,c});
        }

        parent = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        edges.sort((a, b) -> { // 가중치 기반 정렬
            return a[2] - b[2];
        });

        int answer = 0;
        int edgeCnt = 0;

        for(int[] edge : edges) {
            int a = edge[0];
            int b = edge[1];
            int weight = edge[2];

            if(edgeCnt == N - 1) {
                break;
            }

            // 부모 확인
            int parentA = find(a);
            int parentB = find(b);
            if(parentA != parentB) { // 같은 부모
                answer += weight; // 가중치 더하고
                union(a,b); // 부모 통합하고
                edgeCnt++;
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

    private static void union(int a, int b) {

        int parentA = find(a);
        int parentB = find(b);

        if(parentA == parentB) {
            return;
        }

        if(parentA < parentB) {
            parent[parentB] = parentA;
        }else {
            parent[parentA] = parentB;
        }
    }
}