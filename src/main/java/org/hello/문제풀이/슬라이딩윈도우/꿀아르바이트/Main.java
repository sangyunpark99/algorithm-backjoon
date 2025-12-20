package org.hello.문제풀이.슬라이딩윈도우.꿀아르바이트;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static int N;
    private static int M;
    private static long answer;
    private static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        long windowAmount = 0L;
        for(int i = 0; i < M; i++) {
            windowAmount += arr[i];
        }

        answer = windowAmount;

        for(int i = M; i < N; i++) { // O(n)
            long value = windowAmount + arr[i] - arr[i - M];
            answer = Math.max(answer, value);
            windowAmount = value;
        }

        System.out.println(answer);

    }
}