package org.hello.문제풀이.LCS.공통부분문자열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] a = br.readLine().toCharArray();
        char[] b = br.readLine().toCharArray();

        int[][] dp = new int[a.length + 1][b.length + 1];
        int answer = 0;
        for(int i = 1; i <= a.length; i++) {
            for(int j = 1; j <= b.length; j++) {
                if(a[i - 1] == b[j - 1]) { // 연속으로 같은 경우만
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    answer = Math.max(answer, dp[i][j]);
                }
            }
        }

        System.out.println(answer);
    }
}