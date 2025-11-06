package org.hello.문제풀이.DP.피보나치수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static long[] dp= new long[46];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        dp[0] = 0; dp[1] = 1; dp[2] = 1;

        System.out.println(go(n));
    }

    private static long go(int cur) {
        if(dp[cur] > 0) return dp[cur];
        if(cur == 1 || cur == 2) return 1;
        return dp[cur] = go(cur - 1) + go(cur - 2);
    }
}
