package org.hello.문제풀이.DP.FourSquars;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static int n;
    private static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        dp = new int[n + 1]; // 해당 수를 만드는데 드는 최소 갯수

        for(int i = 1; i <= n; i++) {
            dp[i] = i; //
            for(int j = 1; j * j <= i; j++) {
                int v = j * j;
                dp[i] = Math.min(dp[i], dp[i - v] + 1);
            }
        }

        System.out.println(dp[n]);
    }
}
