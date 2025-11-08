package org.hello.문제풀이.유니온파인드.여행가자;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    private static int N;
    private static int M;
    private static int[][] travel;
    private static int[] plan;
    private static int[] parent;
    private static int[] rank;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        travel = new int[N][N];

        for(int i = 0; i < N; i++) {
            travel[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        plan = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        parent = new int[N];
        rank = new int[N];

        for(int i = 0; i < N; i++) {
            parent[i] = i;
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(travel[i][j] == 1) { // 연결되어 있다면
                    union(i,j);
                }
            }
        }

        int value = parent[plan[0] - 1];
        for(int i = 0; i < plan.length; i++) {
            if(parent[plan[i] - 1] != value) {
                System.out.println("NO");
                return;
            }
        }

        System.out.println("YES");
    }

    private static int find(int value) {
        if(parent[value] == value) {
            return value; // 루트
        }

        return parent[value] = find(parent[value]);
    }

    private static void union(int a, int b) { // A를 기준으로
        int parentA = find(a);
        int parentB = find(b);

        if(parentA == parentB) return; // 부모 같은 경우 반환

        if(rank[parentA] < rank[parentB]) { // rank가 큰 쪽에 rank가 짝은 서브 트리 붙이기
            int t = parentA;
            parentA = parentB;
            parentB = t;
        }

        parent[parentB] = parentA;

        if(rank[parentA] == rank[parentB]) {
            rank[parentA]++;
        }
    }
}