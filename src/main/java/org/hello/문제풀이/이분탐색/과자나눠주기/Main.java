package org.hello.문제풀이.이분탐색.과자나눠주기;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static int M;
    private static int N;
    private static int[] cookies;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        cookies = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(cookies);

        System.out.println(binarySearch());
    }

    private static int binarySearch() {
        int left = 1;
        int right = cookies[cookies.length - 1];

        int answer = 0;

        while(left <= right) {
            int mid = (left + right) / 2;

            if(check(mid)) { // 더 늘려도 된다.
                answer = Math.max(answer, mid);
                left = mid + 1;
            }else { // 더 줄여야 한다.
                right = mid - 1;
            }
        }

        return answer;
    }

    private static boolean check(int value) { // lower-bound

        int idx = cookies.length;
        int left = 0;
        int right = cookies.length - 1;

        while(left <= right) {
            int mid = (left + right) / 2;
            if(value <= cookies[mid]) {
                right = mid - 1;
                idx = Math.min(idx, mid);
            }else {
                left = mid + 1;
            }
        }

        int cnt = 0;
        for(int i = idx; i < cookies.length; i++) {
            cnt += cookies[i] / value;
            if(cnt >= M) {
                return true;
            }
        }

        return false;
    }
}
