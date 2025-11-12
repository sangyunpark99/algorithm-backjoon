package org.hello.문제풀이.이분탐색.가장긴증가하는부분수열3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    private static int N;
    private static int[] arr;
    private static int[] tails;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        tails = new int[N]; // 배열의 해당 길이에 올 수 있는 값

        int len = 0;
        for(int i = 0; i < arr.length; i++) {
            int value = arr[i];
            int idx = lowerBound(0, len, value);
            tails[idx] = value;
            if(idx == len) len++;
        }

        System.out.println(len);
    }

    private static int lowerBound(int from, int to, int value) {
        // value가 들어가야하는 처음 위치가 어디인가?
        int left = from;
        int right = to;

        while(left < right) {
            int mid = (left + right) >>> 1;
            if(tails[mid] >= value) {
                right = mid;
            }else {
                left = mid + 1;
            }
        }

        return left;
    }
}
