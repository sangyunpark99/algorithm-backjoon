package org.hello.문제풀이.최소스패닝트리.전력난;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    private static int m;
    private static int n;

    private static int[] parent;
    private static int[] rank;
    private static List<Edge> edges;

    private static boolean[] check;

    public static class Edge {
        int from;
        int to;
        int weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while(true) {

            st = new StringTokenizer(br.readLine());

            m = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());

            if(m == 0 && n == 0) break;

            edges = new ArrayList<>(n);
            parent = new int[m];
            rank = new int[m];

            for(int i = 0; i < m; i++) {
                parent[i] = i;
            }

            for(int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());
                edges.add(new Edge(from, to, weight));
            }

            edges.sort((a,b) -> {
                return a.weight - b.weight;
            });

            int cnt = 0;

            check = new boolean[n];

            for(int i = 0; i < edges.size(); i++) {
                Edge edge = edges.get(i);
                int from = edge.from;
                int to = edge.to;

                if(cnt == m - 1) break;

                int parentA = find(from);
                int parentB = find(to);

                if(parentA != parentB) {
                    check[i] = true;
                    union(parentA, parentB);
                    cnt++;
                }
            }

            int answer = 0;

            for(int i = 0; i < check.length; i++) {
                if(!check[i]) {
                    answer += edges.get(i).weight;
                }
            }

            System.out.println(answer);
        }

    }

    private static int find(int a) {
        if(parent[a] == a) {
            return a;
        }

        return parent[a] = find(parent[a]);
    }

    private static void union(int parentA, int parentB) {

        if(parentA == parentB) return;

        int parentADepth = rank[parentA];
        int parentBDepth = rank[parentB];

        if(parentADepth < parentBDepth) { // 더 큰곳에 붙인다.
            parent[parentA] = parentB;
        }else if(parentADepth > parentBDepth) {
            parent[parentB] = parentA;
        }else {
            parent[parentB] = parentA;
            rank[parentA]++;
        }
    }
}