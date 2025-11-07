package org.hello.문제풀이.이분탐색.공유기설치;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    // 가장 인접한 두 공유기의 최대 거리
    // 최소 중 최대 -> 가장 인접한 두 공유기의 최댓값
    // 가장 인접하다 = 거리가 가장 가까이 위치한다.

    private static int N;
    private static int C;
    private static int[] home;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        home = new int[N];
        for(int i = 0; i < N; i++) {
            home[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(home);

        if(N == 2 && C == 2) {
            System.out.println(home[1] - home[0]);
            return;
        }

        // NlogN -> 200,000 x log200,000
        // 가장 인접한 두 공유기 사이의 최대 거리를 이분 탐색 해서 -> 나누면됨 그 갯수가 와이파이 갯수
        int start = 1;
        int end = 1_000_000_001; // 나올 수 있는 최대 거리
        int answer = Integer.MIN_VALUE;
        while(start <= end) {
            int mid = (start + end) / 2;
            if(check(mid)) { // 범위를 더 늘린다
                start = mid + 1;
                answer = Math.max(answer, mid);
            }else { // 범위를 더 좁힌다.
                end = mid - 1;
            }
        }

        System.out.println(answer);
    }

    private static boolean check(int gap) {

        int left = 0;
        int right = 1;
        boolean check = false;
        while(left < right && right < N) {
            int value = home[right] - home[left];
            if(value < gap) {
                right++;
            }else { // 만족하는 경우
                check = true;
                break;
            }
        }

        if(!check) return false;

        int cnt = 0;
        left = right;
        right = left + 1;
        while(left < right && right < N) {
            int value = home[right] - home[left];
            if(value < gap) {
                right++;
            }else {
                cnt++;
                left = right;
                right = left + 1;
            }
        }

        return cnt >= C - 2;
    }
}
