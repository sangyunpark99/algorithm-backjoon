package org.hello.문제풀이.두포인터.게으른백곰;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static int N;
    private static int K;

    private static int[] arr = new int[1000001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int rightMaxValue = 0;

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int g = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            rightMaxValue = Math.max(rightMaxValue, x);
            arr[x] = g;
        }

        int max = Math.min(1_000_000, rightMaxValue + K);
        int window = 2 * K + 1;

        int left = 0;
        int right = Math.min(max, window - 1);

        long sum = 0;
        for (int i = left; i <= right; i++) sum += arr[i];

        long answer = sum;
        while (right < max) {
            sum += arr[++right];
            sum -= arr[left++];
            answer = Math.max(answer, sum);
        }
        System.out.println(answer);
    }
}
