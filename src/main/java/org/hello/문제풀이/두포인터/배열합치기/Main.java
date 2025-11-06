package org.hello.문제풀이.두포인터.배열합치기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main { // 시간 초과

    private static int N;
    private static int M;
    private static final String SPACE = " ";

    // 정렬된 두 배열
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N =  Integer.parseInt(st.nextToken());
        M =  Integer.parseInt(st.nextToken());

        int[] a = Arrays.stream(br.readLine().split(SPACE)).mapToInt(Integer::parseInt).toArray();
        int[] b  = Arrays.stream(br.readLine().split(SPACE)).mapToInt(Integer::parseInt).toArray();

        int aIdx = 0;
        int bIdx = 0;

        StringBuilder sb = new StringBuilder();

        // NlogN
        while(aIdx < N || bIdx < M) { // O(N+M) = 2,000,000

            int aValue = aIdx == N ? Integer.MAX_VALUE : a[aIdx];
            int bValue = bIdx == M ? Integer.MAX_VALUE : b[bIdx];

            if(aValue < bValue) {
               sb.append(aValue).append(SPACE);
               aIdx++;
            }else {
                sb.append(bValue).append(SPACE);
                bIdx++;
            }
        }

        System.out.println(sb);
    }
}
