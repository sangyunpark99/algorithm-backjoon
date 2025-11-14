package org.hello.문제풀이.DP.이동하기.풀이1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static int N;
    private static int M;

    private static int[][] map;
    private static int[][] dp; // 해당 위치까지 얻을 수 있는 최대의 사탕 갯수

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

        dp[0][0] = map[0][0];

        for(int i = 1; i < M; i++) {
            dp[0][i] = dp[0][i-1] + map[0][i];
        }

        for(int i = 1; i < N; i++) {
            dp[i][0] = dp[i-1][0] + map[i][0];
        }

        for(int i = 1; i < N; i++) { // O(n) = 1,000,000
            for(int j = 1; j < M; j++) {
                dp[i][j] = Math.max(dp[i-1][j], Math.max(dp[i][j-1], dp[i-1][j-1])) + map[i][j];
            }
        }

        System.out.println(dp[N - 1][M - 1]);
    }
}