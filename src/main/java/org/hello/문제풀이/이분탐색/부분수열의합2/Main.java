package org.hello.문제풀이.이분탐색.부분수열의합2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int N;
    private static int S;
    private static int[] arr;

    private static final HashMap<Long, long[]> leftSumCnt = new HashMap<>();
    private static final HashMap<Long, long[]> rightSumCnt = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        int mid = N / 2;

        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        generateSum(0, mid, 0, 0, leftSumCnt, mid);
        generateSum(mid, N, 0, 0, rightSumCnt, N - mid);

        HashMap<Long, Long> left = convertMap(leftSumCnt);
        HashMap<Long, Long> right = convertMap(rightSumCnt);

        long answer = 0;

        for(Map.Entry<Long,Long> entry: left.entrySet()) {
            long leftSum = entry.getKey();
            long leftCnt = entry.getValue();
            long rightCnt = right.getOrDefault(S-leftSum, 0L);
            answer += leftCnt * rightCnt;
        }

        if(S == 0) answer -= 1; // 공집합 제거

        System.out.println(answer);
    }

    private static void generateSum(int idx, long end, long sum, int size, Map<Long,long[]> map, int maxSize) {
        long[] counts = map.computeIfAbsent(sum, k -> new long[maxSize + 1]);
        counts[size]++;

        for(int i = idx; i < end; i++) {
            generateSum(i + 1, end, sum + arr[i], size + 1, map, maxSize);
        }
    }

    private static HashMap<Long,Long> convertMap(HashMap<Long, long[]> map) {
        HashMap<Long, Long> convertMap = new HashMap<>();
        for(Map.Entry<Long, long[]> e : map.entrySet()) {
            long total = 0;
            for(long c : e.getValue()) total += c;
            convertMap.put(e.getKey(), total);
        }

        return convertMap;
    }
}
