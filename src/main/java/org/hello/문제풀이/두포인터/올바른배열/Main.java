package org.hello.문제풀이.두포인터.올바른배열;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];

        for(int i = 0; i < N; i++) {
            int value = Integer.parseInt(br.readLine());
            arr[i] = value;
        }

        Arrays.sort(arr);

        int left = 0;
        int maxCount = 0;

        for(int right = 0; right < N; right++) {
            while(arr[right] - arr[left] > 4) { // 범위가 넓으면
                left++;
            }
            int count = right - left + 1;
            if(count > maxCount) maxCount = count;
        }

        System.out.println(5 - maxCount);
    }
}