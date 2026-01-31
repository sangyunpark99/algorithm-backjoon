package org.hello.문제풀이.냅색.기업투자;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static int M;
    private static int N;
    private static int[][] profits;

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

        int[][] choice = new int[M + 1][N + 1];
        int[][] dp = new int[M + 1][N + 1];

        for(int i = 1; i <= M; i++) { // 기업 선택
            for(int j = 1; j <= N; j++) { // 투자할 총 금액
                dp[i][j] = 0;
                for(int k = 0; k <= j; k++) { // i번째 기업에 K원 투자
                    int value = dp[i - 1][j - k] + profits[i][k]; // 1 ~ i - 1
                    if(value > dp[i][j]) {
                        dp[i][j] = value;
                        choice[i][j] = k; // 총 금액 j를 갖고 1~i 번째 기업까지 고려했을때, i번째 기업에 투자한 금액
                     }
                }
            }
        }

        System.out.println(dp[M][N]);

        int money = N;
        int[] invest = new int[M + 1];

        for(int i = M; i >= 1; i--) {
            int value = choice[i][money];
            invest[i] = value;
            money -= value;
        }

        StringBuilder sb = new StringBuilder();

        for(int i = 1; i <= M; i++) {
            sb.append(invest[i]).append(" ");
        }

        System.out.println(sb);
    }
}