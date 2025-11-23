package org.hello.문제풀이.슬라이딩윈도우.소가길을건너간이유5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static int N;
    private static int K;
    private static int B;
    private static boolean[] circleRoad;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        circleRoad = new boolean[N + 1];
        Arrays.fill(circleRoad, true);

        for(int i = 0; i < B; i++) {
            int num = Integer.parseInt(br.readLine());
            circleRoad[num] = false;
        }

        int start = 1; // 1-based
        int end = start + K - 1;
        int brokenCnt = 0;
        for(int i = start; i <= end; i++) { // 1-based
            if(!circleRoad[i]) {
                brokenCnt++;
            }
        }

        int answer = brokenCnt;

        while(end < N) { // O(N)
            if(!circleRoad[start]) { // 부서진 경우
                brokenCnt--;
            }
            start++;
            end++;
            if(!circleRoad[end]) {
                brokenCnt++;
            }

            answer = Math.min(answer, brokenCnt);
        }

        System.out.println(answer);
    }
}