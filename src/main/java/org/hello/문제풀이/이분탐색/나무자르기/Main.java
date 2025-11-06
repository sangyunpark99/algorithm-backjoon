package org.hello.문제풀이.이분탐색.나무자르기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static long N;
    private static long M;
    private static long[] trees;

    // 높이의 최댓값
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        N = Long.parseLong(stringTokenizer.nextToken());
        M = Long.parseLong(stringTokenizer.nextToken());

        trees = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();

        long start = 0L;
        long end = 0L;
        long answer = 0;

        for(long tree : trees) {
            end = Math.max(end, tree);
        }

        while(start <= end) {
            long mid = (start + end) / 2; // 절단기의 높이

            if(check(mid, M)) { // 가능하다면, 절단기의 크기를 늘린다.
                start = mid + 1;
                answer = mid;
            }else {
                end = mid - 1;
            }
        }

        System.out.println(answer);
    }

    private static boolean check(long height, long target) {
        long getTreeCnt = 0;

        for(long tree : trees) {
            getTreeCnt += Math.max(0,tree - height);
        }

        return getTreeCnt >= target; // 더 많이 가져갈 수 있다면
    }
}
