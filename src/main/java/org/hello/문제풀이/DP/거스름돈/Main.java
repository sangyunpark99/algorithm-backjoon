package org.hello.문제풀이.DP.거스름돈;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    private static int N;
    private static int[] dp; // X원을 만드는데 필요한 동전의 최솟값
    private static int[] coins;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new int[N + 1];

        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        coins = new int[]{2,5};

        for(int i = 1; i <= N; i++) {
            for(int coin: coins) { // 마지막 동전을 무엇을 쓸 것인가?
                if(coin <= i && dp[i - coin] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }

        System.out.println(dp[N] == Integer.MAX_VALUE ? -1 : dp[N]);
    }
}
