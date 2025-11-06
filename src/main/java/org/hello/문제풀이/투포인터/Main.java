package org.hello.문제풀이.투포인터;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    // 자료형 : long
    private static int N;
    private static long[] arr;

    // 항상 정렬된 값!
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N =  Integer.parseInt(br.readLine());
        arr = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();

        long gap = Long.MAX_VALUE;
        long[] answer = new long[2];

        Arrays.sort(arr);

        int left = 0;
        int right = arr.length - 1;

        while(left < right) {

            long leftValue = arr[left];
            long rightValue = arr[right];

            long curGap = leftValue + rightValue;

            if(curGap == 0) {
                answer[0] = leftValue;
                answer[1] = rightValue;
                break;
            }

            // 0에 수렴해야 한다.
            if(curGap < 0) {
                left++;
            }else {
                right--;
            }

            if(gap > Math.abs(curGap)) {
                gap = Math.abs(curGap); // gap을 절댓값
                answer[0] = leftValue;
                answer[1] = rightValue;
            }
        }

        sb.append(answer[0]).append(" ").append(answer[1]);
        System.out.println(sb);
    }
}
