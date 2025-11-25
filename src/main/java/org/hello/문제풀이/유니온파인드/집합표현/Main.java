package org.hello.문제풀이.유니온파인드.집합표현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static int n;
    private static int m;
    private static int[] parent;
    private static int[] rank;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        parent = new int[n + 1];
        rank = new int[n + 1];
        for(int i = 0; i <= n; i++) {
            parent[i] = i;
            rank[i] = 0;
        }

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int order = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(order == 0) {
                union(a, b);
            }else if(order == 1) {
                int parentA = find(a);
                int parentB = find(b);
                if(parentA == parentB) {
                    sb.append("YES").append("\n");
                }else {
                    sb.append("NO").append("\n");
                }
            }
        }

        System.out.println(sb);
    }

    private static int find(int value) {
        if(parent[value] == value) {
            return value;
        }
        return parent[value] = find(parent[value]);
    }

    private static void union(int a, int b) {
        int parentA = find(a);
        int parentB = find(b);

        if(rank[parentA] < rank[parentB]) {
            parent[parentA] = parentB;
        }else if(rank[parentA] > rank[parentB]) {
            parent[parentB] = parentA;
        }else {
            parent[parentB] = parentA;
            rank[parentA]++;
        }
    }
}
