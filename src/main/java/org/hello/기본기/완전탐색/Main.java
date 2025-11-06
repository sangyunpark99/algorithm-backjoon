package org.hello.기본기.완전탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    private static int[] arr;
    private static int answer = Integer.MIN_VALUE;
    private static int n;
    private static final int MOD = 11;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        n = arr.length;

        dfs(0, 0,0);
        System.out.println(answer);
    }

    private static void dfs(int idx, int cur, int cnt) {

        if(answer == 10) {
            System.out.println(cnt);
            return;
        }

        if(idx == n) {
            answer = Math.max(answer, cur % MOD);
            return;
        }

        cnt++;
        dfs(idx + 1, cur + arr[idx], cnt);
        dfs(idx + 1, cur, cnt);
    }
}
