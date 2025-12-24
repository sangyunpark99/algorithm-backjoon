package org.hello.문제풀이.비트마스킹.도영이가만든맛있는음식;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    private static int N;
    private static List<int[]> value = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        for(int i = 0; i < N; i++) {
            int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            value.add(arr);
        }

        int answer = Integer.MAX_VALUE;

        for(int mask = 1; mask < (1 << N); mask++) {
            int a = 1;
            int b = 0;
            for(int i = 0; i < N; i++) { // 각 재료를 넣나요?
                if((mask & (1 << i)) != 0) {
                    a *= value.get(i)[0];
                    b += value.get(i)[1];
                }
            }

            answer = Math.min(answer, Math.abs(a - b));
        }

        System.out.println(answer);
    }
}
