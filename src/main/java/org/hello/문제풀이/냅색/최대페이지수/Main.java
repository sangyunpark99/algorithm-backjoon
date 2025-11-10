package org.hello.문제풀이.냅색.최대페이지수;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] dp = new int[N + 1];

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int days = Integer.parseInt(st.nextToken());
            int pages = Integer.parseInt(st.nextToken());
            for(int d = N; d >= days; d--) {
                dp[d] = Math.max(dp[d], dp[d - days] + pages);
            }
        }

        System.out.println(dp[N]);
    }
}