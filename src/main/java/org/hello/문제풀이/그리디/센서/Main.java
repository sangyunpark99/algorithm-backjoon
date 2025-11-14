package org.hello.문제풀이.그리디.센서;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    private static int N;
    private static int K;
    private static int[] arr;
    private static int[] gap;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());

        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(arr);

        gap = new int[N - 1];
        for(int i = 1; i < N; i++) {
            gap[i - 1] = arr[i] - arr[i - 1];
        }

        Arrays.sort(gap);

        int answer = 0;

        for(int i = 0; i < N - K; i++) { // 가장 큰 K개 제거
            answer += gap[i];
        }

        System.out.println(answer);
    }
}