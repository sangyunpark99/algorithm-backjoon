package org.hello.문제풀이.냅색.기업투자;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static int M;
    private static int N;
    private static int[][] profits;
    private static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 기업별 투자 액수에 따른 수익
        profits = new int[M + 1][N + 1];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int money = Integer.parseInt(st.nextToken());
            for(int j = 1; j <= M; j++) {
                int value = Integer.parseInt(st.nextToken());
                profits[j][money] = value;
            }
        } // 기업별 정해진 액수 투자시 얻을 수 있는 수익

        dp = new int[M + 1][N + 1];

        int[][] choice = new int[M + 1][N + 1]; // dp[i][j]가 최대일 때 i번 기업에 투자한 금액 k

        for(int i = 1; i <= M; i++) {
            for(int j = 1; j <= N; j++) {
                dp[i][j] = 0;
                for(int k = 0; k <= j; k++) {
                    int value = dp[i - 1][j - k] + profits[i][k];
                    if(value > dp[i][j]) { // 최대인 경우
                        dp[i][j] = value;
                        choice[i][j] = k; // i번 기업에 준 돈 K
                    }
                }
            }
        }

        System.out.println(dp[M][N]);

        int[] invest = new int[M + 1];

        int money = N;
        for(int i = M; i >= 1; i--) {
            int k = choice[i][money];
            invest[i] = k;
            money -= k;
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= M; i++) sb.append(invest[i]).append(" ");
        System.out.println(sb);
    }
}