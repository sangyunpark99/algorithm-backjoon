package org.hello.문제풀이.DP.다리놓기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static int T;
    private static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        dp = new int[31][31];

        for(int i = 1; i <= 30; i++) {
            dp[i][0] = 1;
            dp[i][i] = 1; // 초기화
        }

        T = Integer.parseInt(br.readLine());

        for(int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            System.out.println(dfs(m,n));
        }
    }

    private static int dfs(int m, int n) {
        if(dp[m][n] > 0) return dp[m][n]; // 이미 값을 찾은 경우 빨리 return 불필요한 계산 방지
        if(m == 0 || n == m) return dp[m][n] = 1;
        return dp[m][n] = dfs(m - 1, n - 1) + dfs(m - 1, n);
    }
}
