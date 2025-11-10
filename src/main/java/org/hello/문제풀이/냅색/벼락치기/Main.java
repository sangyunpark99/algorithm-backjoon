package org.hello.문제풀이.냅색.벼락치기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static int N;
    private static int T;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        int[] dp = new int[10001];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int study = Integer.parseInt(st.nextToken());
            int score = Integer.parseInt(st.nextToken());
            for(int t = T; t >= study; t--) {
                dp[t] = Math.max(dp[t], dp[t - study] + score);
            }
        }

        System.out.println(dp[T]);
    }
}