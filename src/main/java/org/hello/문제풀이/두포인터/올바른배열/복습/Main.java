package org.hello.문제풀이.두포인터.올바른배열.복습;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    private static int N;
    private static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = new int[N];

        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);

        int left = 0;
        int answer = 0;
        int cnt = 0;
        for(int right = 0; right < N; right++) {
            if(arr[right] - arr[left] > 4) { // 범위를 벗어난 경우
                left++;
                right = left;
                cnt = 1;
            }else {
                cnt++;
                answer = Math.max(answer, cnt);
            }
        }

        System.out.println(5 - answer);
    }
}
