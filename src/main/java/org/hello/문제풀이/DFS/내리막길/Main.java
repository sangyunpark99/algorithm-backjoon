package org.hello.문제풀이.DFS.내리막길;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static int[] dy = {-1,0,1,0};
    private static int[] dx = {0,1,0,-1};
    private static int M;
    private static int N;
    private static int[][] map;
    private static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken()); // 세로
        N = Integer.parseInt(st.nextToken()); // 가로

        map = new int[M][N];

        dp = new int[M][N];
        for(int i = 0; i < M; i++) {
            Arrays.fill(dp[i], -1);
        }

        for(int i = 0; i < M; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        System.out.println(dfs(0, 0));
    }

    // 그래프 사이클이 존재하지 않는다.
    private static int dfs(int y, int x) {
        if(y == M - 1 && x == N - 1) return 1;
        if(dp[y][x] != -1) return dp[y][x];

        dp[y][x] = 0;
        for(int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];

            if(ny < 0 || ny >= M || nx < 0 || nx >= N) continue;
            if(map[y][x] > map[ny][nx]) {
                dp[y][x] += dfs(ny,nx); // 현재 칸에서 도착점까지 가는 경로의 수 + 다음칸에서 도착점까지 가는 경로의 수
            }
        }

        return dp[y][x];
    }
}
