package org.hello.문제풀이.그리디.로프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] ropes = new int[N];

        for(int i = 0; i < N; i++) {
            ropes[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(ropes);

        int answer = Integer.MIN_VALUE;

        for(int i = 0; i < ropes.length; i++) {
            answer = Math.max(answer, ropes[i] * (ropes.length - i));
        }

        System.out.println(answer);
    }
}