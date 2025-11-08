package org.hello.문제풀이.LCS.LCS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;;

public class Main {

    private static char[] a;
    private static char[] b;
    private static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        a = br.readLine().toCharArray();
        b = br.readLine().toCharArray();

        dp = new int[a.length + 1][b.length + 1];

        for(int i = 1; i <= a.length; i++) {
            for(int j = 1; j <= b.length; j++) {
                if(a[i - 1] == b[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        // 맨 아랫줄
        System.out.println(dp[a.length][b.length]);
    }
}
