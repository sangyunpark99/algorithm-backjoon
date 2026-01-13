package org.hello.문제풀이.DP.파일합치기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    private static int T;
    private static int[][] dp;
    private static int[] pSum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());

        while(T-- > 0) {
            int K = Integer.parseInt(br.readLine());
            int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            dp = new int[K][K]; // 최댓값
            for(int i = 0; i < K; i++) {
                Arrays.fill(dp[i], Integer.MAX_VALUE);
            }

            for(int i = 0; i < K; i++) {
                dp[i][i] = 0;
            }

            pSum = new int[K];

            pSum[0] = arr[0];

            for(int i = 1; i < K; i++) {
                pSum[i] = pSum[i - 1] + arr[i];
            }

            sb.append(dfs(0, K - 1)).append("\n");
        }

        System.out.println(sb);
    }

    private static int dfs(int start, int end) {

        if(start == end) {
            return 0;
        }

        if(dp[start][end] != Integer.MAX_VALUE) { // 한번 갱신된 값은 최소를 만족
            return dp[start][end];
        }

        int sum = pSum[end] - (start == 0 ? 0 : pSum[start - 1]);
        int total = Integer.MAX_VALUE;

        for(int k = start; k < end; k++) {
            int left = dfs(start, k);
            int right = dfs(k + 1,end);

            total = Math.min(total, left + right + sum);
        }

        return dp[start][end] = total;
    }
}