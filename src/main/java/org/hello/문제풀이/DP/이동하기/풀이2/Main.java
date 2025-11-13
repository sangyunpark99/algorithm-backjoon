package org.hello.문제풀이.DP.이동하기.풀이2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static int N;
    private static int M;

    private static int[][] map;
    private static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        dp = new int[N][M];

        for(int i = 0; i < N; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        for(int i = 0; i < N; i++) {
            Arrays.fill(dp[i], -1);
        }

        dfs(0,0);

        System.out.println(dp[0][0]);
    }

    private static int dfs(int y, int x) {

        if(y >= N || x >= M) return 0;

        if(dp[y][x] != -1) {
            return dp[y][x];
        }

        int nCandy = Math.max(dfs(y + 1,x), Math.max(dfs(y, x + 1), dfs(y + 1, x + 1)));
        return dp[y][x] = map[y][x] + nCandy; // 마지막에는 0이 된다.
    }
}
