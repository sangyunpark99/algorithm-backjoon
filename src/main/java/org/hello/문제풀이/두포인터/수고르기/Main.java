package org.hello.문제풀이.두포인터.수고르기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    // N개로 이루어진 정수
    // 두 수를 골랐을때, 그 차이가 M 이상이면서 제일 작은 경우의 수를 구하라

    private static int N;
    private static int M;
    private static int[] arr;
    private static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];

        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        // 정렬 필수
        Arrays.sort(arr); // nlogn

        int r = 0;

        for(int l = 0; l < N; l++) {
            while(r < N && arr[r] - arr[l] < M) r++;
            if(r == N) break;
            answer = Math.min(answer,arr[r] - arr[l]);
        }

       System.out.println(answer);
    }
}