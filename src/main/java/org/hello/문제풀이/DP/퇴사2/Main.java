package org.hello.문제풀이.DP.퇴사2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static int N;
    private static int[] T;
    private static int[] P;
    private static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        T = new int[N + 1];
        P = new int[N + 1];
        dp = new int[N + 2];

        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            T[i] = a;
            P[i] = b;
        }

        for(int i = 1; i <= N; i++) {
            dp[i] = Math.max(dp[i], dp[i - 1]); // 상담을 안하는 경우
            int end = i + T[i];
            if(end <= N + 1) { // 상담을 한 경우
                dp[end] = Math.max(dp[end], dp[i] + P[i]);
            }
        }

        dp[N + 1] = Math.max(dp[N + 1], dp[N]);

        System.out.println(dp[N + 1]);
    }
}
