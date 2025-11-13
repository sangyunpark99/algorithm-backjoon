package org.hello.문제풀이.DP.타일채우기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static int N;
    private static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        dp = new int[N + 1]; // 짝수만 채울 수 있다.

        if(N % 2 == 1) {
            System.out.println(0);
            return;
        }

        dp[0] = 1;
        dp[2] = 3;

        for(int n = 4; n <= N; n += 2) {
            dp[n] = dp[n - 2] * 3;
            for(int k = 4; k <= n; k += 2) {
                dp[n] += dp[n - k] * 2;
            }
        }

        System.out.println(dp[N]);
    }
}
