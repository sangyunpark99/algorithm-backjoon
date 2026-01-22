package org.hello.문제풀이.분할정복.행렬제곱;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

    private static int N;
    private static long B;

    private static long[][] arr;
    private static HashMap<Long, long[][]> memo = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        B = Long.parseLong(st.nextToken());

        arr = new long[N][N];

        for(int i = 0; i < N; i++) {
            arr[i] = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong)
                    .map(x -> x % 1000)
                    .toArray();
        }

        memo.put(1L, arr);

        long[][] answer = divideConquer(B);

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < answer.length; i++) {
            for(int j = 0; j < answer[i].length; j++) {
                sb.append(answer[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    private static long[][] divideConquer(long n) {

        if(memo.containsKey(n)) { // n == 1인 경우
            return memo.get(n);
        }

        long[][] half = divideConquer(n / 2);
        long[][] res;
        if(n % 2 == 0) { // 짝수
            res = multiple(half, half, N);
        }else { // 홀수
            res = multiple(multiple(half, half, N), arr, N);
        }

        memo.put(n, res);
        return res;
    }

    private static long[][] multiple(long[][] arr1, long[][] arr2, int n) {

        long[][] result = new long[n][n];

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                // i랑 j는 행과 열을 나타낸다
                long value = 0;
                for(int k = 0; k < n; k++) {
                    value += arr1[i][k] * arr2[k][j];
                }
                result[i][j] = value % 1000;
            }
        }

        return result;
    }
}