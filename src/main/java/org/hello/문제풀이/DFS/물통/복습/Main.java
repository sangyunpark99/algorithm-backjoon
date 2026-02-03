package org.hello.문제풀이.DFS.물통.복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {

    private static int A;
    private static int B;
    private static int C;

    private static int[] capacity;
    private static TreeSet<Integer> answer = new TreeSet<>();
    private static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        capacity = new int[]{A,B,C};
        visited = new boolean[A + 1][B + 1]; // 물 최대 양

        dfs(new int[]{0,0,C});

        StringBuilder sb = new StringBuilder();

        while(!answer.isEmpty()) {
            sb.append(answer.pollFirst()).append(" ");
        }

        System.out.println(sb);
    }

    private static void dfs(int[] water) {

        int a = water[0];
        int b = water[1];
        int c = water[2];

        if(visited[a][b]) {
            return;
        }

        visited[a][b] = true;

        if(a == 0) {
            answer.add(c);
        }

        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(i == j) continue;
                if(water[i] == 0) continue;
                if(water[j] == capacity[j]) continue;

                int[] waterClone = water.clone();
                int w = Math.min(water[i], capacity[j] - water[j]);

                waterClone[i] -= w;
                waterClone[j] += w;

                dfs(waterClone);
            }
        }
    }
}
