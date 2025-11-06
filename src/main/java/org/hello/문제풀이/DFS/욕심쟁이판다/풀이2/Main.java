package org.hello.문제풀이.DFS.욕심쟁이판다.풀이2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    private static int N;
    private static int[][] map;
    private static int[][] dp;

    private static int[] dy = {-1,0,1,0};
    private static int[] dx = {0,1,0,-1};
    private static int answer = Integer.MIN_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];

        for(int i = 0; i < N; i++){
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        // O(n) = 4^250000 -> 완탐은 안된다.
        // dp로 ?
        dp = new int[N][N]; // 최소

        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                answer = Math.max(answer, dfs(i,j));
            }
        }

        System.out.println(answer + 1);
    }

    private static int dfs(int y, int x) {
        if(dp[y][x] != 0) return  dp[y][x];

        for(int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            if(ny < 0 || ny >= N || nx < 0 || nx >= N) continue;
            if(map[y][x] < map[ny][nx]) { // 더 큰 지역을 만난 경
                int next = 1 + dfs(ny, nx);
                dp[y][x] = Math.max(dp[y][x], next); // 더 큰 경우
            }
        }

        return dp[y][x];
    }
}
