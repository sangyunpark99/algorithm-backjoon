package org.hello.문제풀이.그래프탐색.A에서B;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static long A;
    private static long B;
    private static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException { // long 자료현
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        A = Long.parseLong(st.nextToken());
        B = Long.parseLong(st.nextToken());
        dfs(A, B, 0);

        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer + 1);
    }

    private static void dfs(long cur, long target, int cnt) {

        if(cur == target) {
            answer = Math.min(answer, cnt);
            return;
        }

        if(cur > target) {
            return;
        }

        // 2를 곱한다. 먼저 진행
        dfs(cur * 2, target, cnt + 1);

        // 1을 더한다.
        long next = Long.parseLong(cur + "1");
        dfs(next, target, cnt + 1);
    }
}
