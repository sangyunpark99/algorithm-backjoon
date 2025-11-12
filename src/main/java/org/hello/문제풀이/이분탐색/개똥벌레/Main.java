package org.hello.문제풀이.이분탐색.개똥벌레;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static int N;
    private static int H;
    private static int[] ceil;
    private static int[] floor;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        int bottomCnt = N / 2;
        int topCnt = N / 2;
        ceil = new int[bottomCnt];
        floor = new int[topCnt];

        int cIdx = 0;
        int fIdx = 0;
        for(int i = 0; i < N; i++) {
            int value = Integer.parseInt(br.readLine());
            if((i & 1) == 0) { // 짝수인 경우, 바닥
                floor[fIdx++] = value;
            }else { // 홀수인 경우, 천장
                ceil[cIdx++] = value;
            }
        }

        Arrays.sort(ceil); //
        Arrays.sort(floor);
        int min = Integer.MAX_VALUE;
        int answer = 0;
        for(int h = 1; h <= H; h++) { // 아래가 기준
            int bottomHit = bottomCnt - lowerBound(floor, h); // 바닥에서 부딪히는 수
            int topHit = topCnt - lowerBound(ceil, H - h + 1); // 천장에서 부딪히는 수
            int total = bottomHit + topHit;
            if(total < min) { // 최솟값 갱신
                min = total;
                answer = 1;
            }else if(total == min) { // 동일 구간 추가
                answer++;
            }
        }

        System.out.println(min + " " + answer);
    }

    private static int lowerBound(int[] arr, int value) { // h가 가장 먼저 등장하는 값
        int l = 0;
        int r = arr.length;

        while(l < r) {
            int mid = (l + r) >>> 1;
            if(arr[mid] >= value) r = mid;
            else l = mid + 1;
        }
        return l;
    }
}
