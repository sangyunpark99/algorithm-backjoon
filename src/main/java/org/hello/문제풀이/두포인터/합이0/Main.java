package org.hello.문제풀이.두포인터.합이0;

import java.io.*;
import java.util.*;

public class Main {

    private static int N;
    private static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(arr);

        long answer = 0;

        for(int i = 0; i < N; i++) {
            int value = arr[i];
            int left = i + 1;
            int right = N - 1;

            while(left < right) {
                long sum = value + arr[left] + arr[right];

                if(sum < 0) {
                    left++;
                }else if(sum > 0) {
                    right--;
                }else {
                    if(arr[left] == arr[right]) {
                        int cnt = right - left + 1;
                        answer += (long) cnt * (cnt - 1) / 2;
                        break;
                    }

                    int leftValue = arr[left];
                    int rightValue = arr[right];

                    int leftCnt = 0;

                    while(left <= right && arr[left] == leftValue) {
                        leftCnt++;
                        left++;
                    }

                    int rightCnt = 0;
                    while(left <= right && arr[right] == rightValue) {
                        rightCnt++;
                        right--;
                    }

                    answer += (long) leftCnt * rightCnt;
                }
            }
        }

        System.out.println(answer);
    }
}