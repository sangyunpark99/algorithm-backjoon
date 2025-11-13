package org.hello.문제풀이.DP.돌게임2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static int N;
    private static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        dp = new int[N + 1];

        for(int value : new int[]{1, 3}) {
            for(int i = value; i <= N; i++) {
                dp[i] = dp[i - value] + 1;
            }
        }

        if(dp[N] % 2 == 0) {
            System.out.println("SK");
        }else {
            System.out.println("CY");
        }
    }
}
