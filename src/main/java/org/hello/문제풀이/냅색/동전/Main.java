package org.hello.문제풀이.냅색.동전;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    private static int T;
    private static int N;
    private static int[] coins;
    private static int[] dp;
    private static int M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while(T-- > 0) {
            N = Integer.parseInt(br.readLine());
            coins = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            M = Integer.parseInt(br.readLine());
            dp = new int[M + 1];

            dp[0] = 1;

            for(int coin: coins) { // 갯수 무한
                for(int i = coin; i <= M; i++) {
                    dp[i] += dp[i - coin];
                }
            }

            sb.append(dp[M]).append("\n");
        }

        System.out.println(sb);
    }
}
