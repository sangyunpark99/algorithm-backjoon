package org.hello.문제풀이.그리디.보물;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    private static int N;
    private static int[] arrA;
    private static int[] arrB;
    private static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arrA = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        arrB = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        Arrays.sort(arrA);
        Arrays.sort(arrB);

        for(int i = 0; i < N; i++) {
            answer += arrA[i] * arrB[N - i - 1];
        }

        System.out.println(answer);
    }
}
