package org.hello.문제풀이.DP.내려가기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    private static int N;
    private static int[][] value;
    private static int[][] maxDp;
    private static int[][] minDp;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        value = new int[N][3];
        maxDp = new int[2][3];
        minDp = new int[2][3];

        for(int i = 0; i < N; i++) { // 100,000
            value[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        if(N == 1) {
            int maxValue = Math.max(value[0][0], Math.max(value[0][1], value[0][2]));
            int minValue = Math.min(value[0][0], java.lang.Math.min(value[0][1], value[0][2]));
            sb.append(maxValue).append(" ").append(minValue);
            System.out.println(sb.toString());
            return;
        }

        for(int i = 0; i < 3; i++) { // 3
            maxDp[0][i] = value[0][i];
            minDp[0][i] = value[0][i];
        }

        for(int i = 1; i < N; i++) { // 100,000
            int left = Math.max(maxDp[0][0] + value[i][0], maxDp[0][1] + value[i][0]); // 맨 왼쪽에서 나올 수 있는 가장 큰 수
            int mid = Math.max(maxDp[0][0] + value[i][1], Math.max(maxDp[0][1] + value[i][1], maxDp[0][2] + value[i][1])); // 중간에서 나올 수 있는 가장 큰 수
            int right = Math.max(maxDp[0][1] + value[i][2], maxDp[0][2] + value[i][2]); // 맨 오른쪽에서 나올 수 있는 가장 큰 수

            maxDp[0][0] = maxDp[1][0] = left;
            maxDp[0][1] = maxDp[1][1] = mid;
            maxDp[0][2] = maxDp[1][2] = right;
        }

        for(int i = 1; i < N; i++) { // 100,000
            int left = Math.min(minDp[0][0] + value[i][0], minDp[0][1] + value[i][0]); // 맨 왼쪽에서 나올 수 있는 가장 큰 수
            int mid = Math.min(minDp[0][0]  + value[i][1], Math.min(minDp[0][1] + value[i][1], minDp[0][2] + value[i][1])); // 중간에서 나올 수 있는 가장 큰 수
            int right = Math.min(minDp[0][1] + value[i][2], minDp[0][2] + value[i][2]); // 맨 오른쪽에서 나올 수 있는 가장 큰 수

            minDp[0][0] = minDp[1][0] = left;
            minDp[0][1] = minDp[1][1] = mid;
            minDp[0][2] = minDp[1][2] = right;
        }

        int maxValue = Math.max(maxDp[1][0], Math.max(maxDp[1][1], maxDp[1][2]));
        int minValue = Math.min(minDp[1][0], Math.min(minDp[1][1], minDp[1][2]));
        sb.append(maxValue).append(" ").append(minValue);

        System.out.println(sb.toString());
    }
}