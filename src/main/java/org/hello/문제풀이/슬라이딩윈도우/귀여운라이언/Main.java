package org.hello.문제풀이.슬라이딩윈도우.귀여운라이언;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    private static int N;
    private static int K;
    private static int[] arr;
    private static List<Integer> lionIdx = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        for(int i = 0; i < arr.length; i++) {
            if(arr[i] == 1) {
                lionIdx.add(i);
            }
        }

        if(lionIdx.size() < K) {
            System.out.println(-1);
            return;
        }

        int answer = 1_000_001;

        for(int start = 0; start <= lionIdx.size() - K; start++) {
            int endIdx = start + K - 1; // start 갯수 제외
            answer = Math.min(answer, lionIdx.get(endIdx) - lionIdx.get(start) + 1);
        }

        System.out.println(answer);
    }
}
