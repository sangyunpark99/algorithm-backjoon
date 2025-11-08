package org.hello.문제풀이.백트래킹.N과M4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static int N;
    private static int M;
    private static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];

        for(int i = 0; i < N; i++) {
            arr[i] = i + 1;
        }

        combi(0, new int[M], 1);
    }

    private static void combi(int depth, int[] value, int start) {

        if(depth == M) {
           for(int i = 0; i < value.length; i++) {
               System.out.print(value[i] + " ");
           }

           System.out.println();
           return;
        }

        for(int i = start; i <= N; i++) { // 들어갈 숫자
            value[depth] = i;
            combi(depth + 1, value, i); // i 이후 부터
        }
    }
}
