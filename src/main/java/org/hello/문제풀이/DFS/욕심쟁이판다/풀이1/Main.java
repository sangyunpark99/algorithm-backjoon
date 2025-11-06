package org.hello.문제풀이.DFS.욕심쟁이판다.풀이1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    private static int N;
    private static int[][] map;

    private static int[] dy = {-1,0,1,0};
    private static int[] dx = {0,1,0,-1};

    // 시간 초과
    private static int[][] dp;
    private static int answer = 0;

    public static void main(String[] args) throws IOException {
        // 판다가 최대한 많은 칸을 방문할 수 있는가?
        // 시작 지점에 따라서 달라지나?
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        dp = new int[N][N];

        for(int i = 0; i < N; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                answer = Math.max(answer, dfs(i, j));
            }
        }

        System.out.println(answer + 1);
    }

    private static int dfs(int y, int x) {

        if(dp[y][x] != 0) return dp[y][x]; // 이미 최적해 누적

        for(int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];

            if(ny < 0 || ny >= N || nx < 0 || nx >= N) continue;
            if(map[ny][nx] > map[y][x]) {
                dp[y][x] = Math.max(dp[y][x], 1 + dfs(ny,nx));
            }
        }

        return dp[y][x];
    }
}
