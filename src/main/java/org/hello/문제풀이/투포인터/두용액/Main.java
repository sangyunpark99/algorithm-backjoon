package org.hello.문제풀이.투포인터.두용액;

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
        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int left = 0;
        int right = N - 1;
        int dist = Integer.MAX_VALUE;
        int answer = 0;

        while(left < right) {
            int gap = arr[right] + arr[left];
            if(Math.abs(gap) < dist) {
                dist = Math.abs(gap);
                answer = gap;
            }

            if(gap > 0) { // 부호를 기준으로 움직인다.
                right--;
            }else if(gap < 0) { // 음수인 경우
                left++;
            }else {
                break;
            }
        }

        System.out.println(answer);
    }
}
