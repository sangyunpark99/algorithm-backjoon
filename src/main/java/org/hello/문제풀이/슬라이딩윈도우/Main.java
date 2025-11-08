package org.hello.문제풀이.슬라이딩윈도우;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

    private static int N;
    private static int X;
    private static int[] arr;
    private static HashMap<Integer, Integer> cnt = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int maxVisitor = 0;

        int sum = 0;
        for(int i = 0; i < X; i++) {
            sum += arr[i];
        }

        maxVisitor = Math.max(maxVisitor, sum);
        cnt.put(sum, cnt.getOrDefault(sum, 0) + 1);

        for(int i = X; i < N; i++) { // O(N)
            sum -= arr[i - X]; // 구간 이전 만큼 제거
            sum += arr[i];
            maxVisitor = Math.max(maxVisitor, sum);
            cnt.put(sum, cnt.getOrDefault(sum, 0) + 1);
        }

        if(maxVisitor == 0) {
            System.out.println("SAD");
            return;
        }
        System.out.println(maxVisitor);
        System.out.println(cnt.get(maxVisitor));
    }
}
