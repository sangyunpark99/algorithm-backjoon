package org.hello.문제풀이.냅색.Coins;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    private static int T;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while(T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            int[] coins = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int target = Integer.parseInt(br.readLine());
            int[] dp = new int[target + 1];
            dp[0] = 1;
            for(int coin: coins) { // 1원, 5원, 10원
                for(int next = coin; next <= target; next++) {
                    dp[next] += dp[next - coin]; // 경우의 수
                }
            }
            sb.append(dp[target]).append("\n");
        }

        System.out.println(sb);
    }
}