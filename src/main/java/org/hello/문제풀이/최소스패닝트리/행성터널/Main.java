package org.hello.문제풀이.최소스패닝트리.행성터널;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    // 1. 간선 가중치 구하기
    private static List<int[]> edges;
    private static int N;
    private static List<int[]> nodes = new ArrayList<>();

    private static int[] parent;
    private static int[] rank; // 더 높이가 높은

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        edges = new ArrayList<>(3 * (N - 1));

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());

            nodes.add(new int[]{i, x, y, z});
        }

        nodes.sort((a,b) -> a[1] - b[1]);
        for(int i = 0; i < N - 1; i++) {
            int[] a = nodes.get(i);
            int[] b = nodes.get(i + 1);
            edges.add(new int[]{a[0], b[0], Math.abs(a[1] - b[1])});
        }

        nodes.sort((a,b) -> a[2] - b[2]);
        for(int i = 0; i < N - 1; i++) {
            int[] a = nodes.get(i);
            int[] b = nodes.get(i + 1);
            edges.add(new int[]{a[0], b[0], Math.abs(a[2] - b[2])});
        }

        nodes.sort((a,b) -> a[3] - b[3]);
        for(int i = 0; i < N - 1; i++) {
            int[] a = nodes.get(i);
            int[] b = nodes.get(i + 1);
            edges.add(new int[]{a[0], b[0], Math.abs(a[3] - b[3])});
        }

        edges.sort((a,b) -> {
            return a[2] - b[2];
        });

        parent = new int[N];
        rank = new int[N];

        for(int i = 0; i < N; i++) {
            parent[i] = i;
        }

        int totalWeight = 0;
        int connectCnt = 0;

        for(int i = 0; i < edges.size(); i++) { // N - 1개 간선

            if(connectCnt == N - 1) {
                break;
            }

            int[] edge = edges.get(i);
            int a = edge[0];
            int b = edge[1];
            int c = edge[2];

            int parentA = find(a);
            int parentB = find(b);

            if(parentA != parentB) { // 연결이 안된 경우
                union(parentA, parentB);
                totalWeight += c;
                connectCnt++;
            }
        }

        System.out.println(totalWeight);
    }

    private static int find(int a) {
        if(parent[a] == a) {
            return a;
        }

        return parent[a] = find(parent[a]);
    }

    private static void union(int parentA, int parentB) {

        if(rank[parentA] < rank[parentB]) {
            parent[parentA] = parentB;
        }else if(rank[parentA] > rank[parentB]){
            parent[parentB] = parentA;
        }else {
            parent[parentA] = parentB;
            rank[parentB]++;
        }
    }
}